;; There's no literal syntax.

(sorted-map :b 43 :a 100 :c 4)
;; {:a 100, :b 43, :c 4} -- keys are in insertion (not the insertion order)

;; Using a custom comparator function.
(sorted-map-by (comparator <) 0 :a 1 :b 2 :c)
;; {0 :a, 1 :b, 2 :c}

(sorted-map-by (comparator >) 0 :a 1 :b 2 :c)
;; {2 :c, 1 :b, 0 :a}

;; Sorted maps can be used as functions.
(def students (sorted-map 123 {:name "Eric"} 332 {:name "Mary"}))

(students 332)
;; {:name "Mary"}