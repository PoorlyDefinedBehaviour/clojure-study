;; nil evaluates to false when used in a Boolean context.
;; other than false and nil, everything evaluates to true in a Boolean context.

;; The empty list is not false.
(if () "() is true" "() is false")
;; "() is true"

;; Zero is not false.
(if 0 "Zero is true" "Zero is false")
;; "Zero is true"

;; A predicate is a function that returns either true or false. 
;; In Clojure, it's common to name predicates with a trailing question mark.
(defn is-over-eighteen? [age] (>= 18 age))

(zero? 0.0)
;; true

(zero? (/ 22 7))
;; false