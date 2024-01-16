;; Literal syntax.
#{1 2 3}
;; #{1 3 2}

;; Function to create a new set.
(hash-set 1 2 3)
;; #{1 3 2}

;; Converting a collection to a set.
(set [1 2 3 1])
;; #{1 3 2}

;; Sets can be called like functions. It perform a lookup.
(#{1 2 3} 3)
;; 3

(#{1 2 3} 10)
;; nil