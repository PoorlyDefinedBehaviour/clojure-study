(ns java-interop.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

;; Calling methods.
;; Same as "By Bluebeard's bananas!".toUpperCase()
(.toUpperCase "By Bluebeard's bananas!");
;; "BY BLUEBEARD'S BANANAS!"

(.indexOf "Let's synergize our bleeding edges" "y")
;; 7

;; Calling static methods
;; Could use (abs -3) as well.
(java.lang.Math/abs -3)
;; 3

(macroexpand-1 '(.toUpperCase "By Bluebeard's bananas!"))
;; (. "By Bluebeard's bananas!" toUpperCase)

(new String)
;; ""

;; Same as above (new String)
(String.)
;; ""

(java.util.Stack.)
;; []

(let [stack (java.util.Stack.)]
  (.push stack "Latest episode of Game of Thrones, ho!")
  stack)
;; ["Latest episode of Game of Thrones, ho!"] -- it's not a vector.

(let [stack (java.util.Stack.)]
  (.push stack "Latest episode of Game of Thrones, ho!")
  (first stack))
;; "Latest episode of Game of Thrones, ho!" -- seq functions can be used on java Stacks.

;; doto returns the object in the end.
(doto (java.util.Stack.)
  ;; Call .push.
  (.push "Latest episode of Game of Thrones, ho!")
  ;; And then call push again.
  (.push "Whoops, I meant 'Land, ho!'"))
;; ["Latest episode of Game of Thrones, ho!" "Whoops, I meant 'Land, ho!'"]

;; Importing has the same effect as it does in Java.
(import java.util.Stack)
(Stack.)
;; []

(import [java.util Date Stack]
        [java.net Proxy URI])

(Date.)
;; #inst "2024-02-07T00:08:20.242-00:00"

;; Importing in the ns macro. Same effect as importing outside the ns macro.
(ns pirate.talk
  (:import [java.util Date Stack]
           [java.net Proxy URI]))

(System/getenv)

(System/getProperty "user.dir")
;; "/home/bruno/dev/programming-clojure-3rd-edition/clojure-for-the-true-and-brave/java-interop"

;; Dates as literals
#inst "2016-09-19T20:40:02.733-00:00"

(let [file (java.io.File. "/")]
  (println (.exists file))
  (println (.canWrite file))
  (println (.getPath file)))
;; true
;; false
;; /
;; nil

(spit "/tmp/hercules-todo-list"
      "- kill dat lion brov
- chop up what nasty multi-headed snake thing")

(slurp "/tmp/hercules-todo-list")
;; "- kill dat lion brov\n- chop up what nasty multi-headed snake thing"

(let [s (java.io.StringWriter.)]
  (spit s "- capture cerynian hind like for real")
  (.toString s))
;; "- capture cerynian hind like for real"

(let [s (java.io.StringReader. "- get erymanthian pig what with the tusks")]
  (slurp s))
;; "- get erymanthian pig what with the tusks"

(require '[clojure.java.io])

(with-open [todo-list-rdr (clojure.java.io/reader "/tmp/hercules-todo-list")]
  (println (first (line-seq todo-list-rdr))))