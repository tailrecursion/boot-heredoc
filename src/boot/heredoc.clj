(ns boot.heredoc
  {:boot/export-tasks true}
  (:require
    [boot.core       :as boot]
    [boot.pod        :as pod]
    [boot.util       :as util]
    [clojure.java.io :as io]
    [clojure.string  :as str]))

(defn inline-code [start end s]
  (let [lines (str/split s #"\n")
        start (re-pattern (str start "\\s*$"))
        end   (re-pattern (str "^\\s*" end "\\s*$"))
        pad #"^\s*"
        unpad #(str/replace %1 (re-pattern (format "^\\s{0,%d}" %2)) "")]
    (loop [txt nil, i 0, [line & lines] lines, out []]
      (if-not line
        (str/join "\n" out)
        (if-not txt
          (if (re-find start line)
            (recur [] i lines out)
            (recur txt i lines (conj out line)))
          (if (re-find end line)
            (let [line-number-equalizer (apply str (repeat (+ 3 (count txt)) "\n"))
                  s (pr-str (str/trim (str/join "\n" txt)))]
              (recur nil 0 (rest lines) (conj (pop out) (str (peek out) " " s (unpad (first lines) i) line-number-equalizer))))
            (let [i (if-not (empty? txt)
                      i
                      (count (re-find pad line)))]
              (recur (conj txt (unpad line i)) i lines out))))))))

(boot/deftask heredoc
  "Parse files replacing code between separators with a multiline string literal. Default block start is \";;{{\" and default block ending is \";;}}\""
  [s start    START    str "Start delimiter as a string. This will be converted to a pattern using re-pattern. Defaults to `;;\\{\\{"
   e end      END      str "End delimiter. This will be converted to a pattern using re-pattern. Defaults to `;;\\}\\}`"
   f file-ext FILE-EXT str "File extension to replace multiline strings. Eg.: `.hl` (the default)"]
  (let [prev-fileset (atom nil)
        tmp-files    (boot/tmp-dir!)
        start        (or start ";;\\{\\{")
        end          (or end   ";;\\}\\}")
        file-ext     (or file-ext ".hl")
        ]
    (boot/with-pre-wrap fileset
      (let [hls (->> fileset
                     (boot/fileset-diff @prev-fileset)
                     boot/input-files
                     (boot/by-ext [file-ext])
                     (map (juxt boot/tmp-path boot/tmp-file)))]
        (reset! prev-fileset fileset)
        (doseq [[p in] hls]
          (let [out (doto (io/file tmp-files p) io/make-parents)]
            (->> in slurp (inline-code start end) (spit out)))))
      (-> fileset (boot/add-source tmp-files)  boot/commit!))))
