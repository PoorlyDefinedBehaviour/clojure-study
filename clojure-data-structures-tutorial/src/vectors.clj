(ns vectors)

;; Literal syntax.
[1 2 3 4]

;; Takes any number of arguments.
(vector 1 2 3 4)
;; [1 2 3 4]

;; Converts any collection to vector.
(vec '(1 2 2 4))
;; [1 2 3 4]

(def v [:a :b :c])

;; Get element at index 0. (Called like a function.)
(v 0)
;; :a

(get v 0)
;; :a