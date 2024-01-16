;; Literal syntax.
{:a 1
 :b 2
 :c 3}

;; This throws becauses keys are duplicated in the map literal.
;; {a: 1
;;  a: 2}
;; Throws Duplicate key: :a

;; Takes key value pairs and returns a map.
(hash-map :a 1 :b 2)
;; {:b 2, :a 1}

(into {} 
      [[:a 1] [:b 2] [:c 3]])
;; {:a 1, :b 2, :c 3}

;; Hashmaps can be called as functions. Calling a hashmap looks up the key provided as an argument.
(def numbers {:zero 0 :one 1 :two 2})

(numbers :two)
;; 2

;; Using the get function.
(get numbers :two)
;; 2