(set-env!
  :dependencies '[[org.clojure/clojure "1.7.0"  :scope "provided"]
                  [adzerk/bootlaces    "0.1.12" :scope "test"]])

(require
  '[clojure.java.io :as io]
  '[adzerk.bootlaces :refer :all])

(def +version+ "0.1.0")

(bootlaces! +version+)

(require '[boot.heredoc :refer :all])

(task-options!
  pom  {:project     'tailrecursion/boot-heredoc
        :version     +version+
        :description "Boot task for parsing multiline strings"
        :url         "https://github.com/tailrecursion/boot-heredoc"
        :scm         {:url "https://github.com/tailrecursion/boot-heredoc"}
        :license     {"Eclipse Public License" "http://www.eclipse.org/legal/epl-v10.html"}})
