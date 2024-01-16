;; There's no literal syntax.

(sorted-set 7 3 1 2 3 1)
;; #{1 2 3 7}

;; Sorted sets can be used like functions.
((sorted-set 1 2 3) 2)
;; 2

(get (sorted-set 1 2 3) 2)
;; 2