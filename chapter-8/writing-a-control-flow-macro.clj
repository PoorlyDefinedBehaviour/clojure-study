;; if is part of the language.
(if (= 1 1) (println "yep, math still works today"))
;; yep, math still works today

;; broken: clojure evaluates all the arguments before passing them to a function.
(defn unless [expr form]
  (if expr nil form))

;; not broken.
(defmacro unless-2 [expr form]
  (list 'if expr nil form))

;; from Clojure core.
(defmacro when-not [test & body]
  (list 'if test nil (cons 'do body)))

