# boot-multiline-str

[](dependency)
```clojure
[tailrecursion/boot-multiline-str "0.1.0"] ;; latest release
```
[](/dependency)

[Boot] task for adding multiline strings to Clojure

## Example
```clojure
(def lorem-ipsum
  ;;{{
  Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eget
  eleifend metus. Curabitur vitae vestibulum ligula, vel pulvinar nisl. Sed
  cursus ipsum id enim rutrum iaculis. Mauris non ullamcorper sem. In porttitor
  mi ut massa molestie, eu pharetra turpis aliquet. Ut fermentum varius tortor,
  sit amet maximus mi. Praesent et orci orci. Nullam auctor nisi eget sagittis
  pharetra. Sed ullamcorper mauris sit amet metus pulvinar, et pretium elit
  tincidunt.
  ;;}}
  )

;; becomes...

(def lorem-ipsum "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eget\neleifend metus. Curabitur vitae vestibulum ligula, vel pulvinar nisl. Sed\ncursus ipsum id enim rutrum iaculis. Mauris non ullamcorper sem. In porttitor\nmi ut massa molestie, eu pharetra turpis aliquet. Ut fermentum varius tortor,\nsit amet maximus mi. Praesent et orci orci. Nullam auctor nisi eget sagittis\npharetra. Sed ullamcorper mauris sit amet metus pulvinar, et pretium elit\ntincidunt.")
```
## Usage

Add `boot-multiline-str` to your `build.boot` dependencies and `require` the namespace.

> **Note:** the boot-multiline-str dependency is only needed at when compiling
> source files. So you should add boot-multiline-str with the _test_ scope.

```clj
(merge-env!
  :dependencies '[[tailrecursion/boot-multiline-str "X.Y.Z" :scope "test"]])
(require '[tailrecursion.boot-multiline-str :refer [multiline-str]])
```

By default the multiline string block starts with `;;{{` and ends with `;;}}` and
it replaces those blocks on `.hl` files. Those are customizable using options.

You can see the options available on the command line:

```bash
boot multiline-str -h
```

or in the REPL:

```clj
boot.user=> (doc multiline-str)
```

## License

Copyright © 2015 Marcelo Nomoto

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

[Boot]:                https://github.com/boot-clj/boot
