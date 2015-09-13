# boot-multiline-str

[](dependency)
```clojure
[tailrecursion/boot-multiline-str "0.1.0"] ;; latest release
```
[](/dependency)

[Boot] task for adding multiline strings to Clojure

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
