;; vectors: sequential, indexed collections.
[1 2 3]
;; [1 2 3]

;; lists: sequential colelctions stored as a linked list.

(quote (1 2 3))
;; (1 2 3)

'(1 2 3)
;; (1 2 3)

;; sets: unordered collections that do not contain duplicates.
#{1 2 3 5}
;; #{1 3 2 5}

;; maps: collections of key/value pairs.
{"Lisp" "McCarthy" "Clojure" "Hickey"}
;; {"Lisp" "McCarthy" "Clojure" "Hickey"}

;; Using keywords.
{:Lisp "McCarthy" :Clojure "Hickey"}

;; Creating a record.
(defrecord Book [title author])

;; ->Book is the Book constructor function.
;; A Book behaves almost like any other map.
(->Book "title" "author")