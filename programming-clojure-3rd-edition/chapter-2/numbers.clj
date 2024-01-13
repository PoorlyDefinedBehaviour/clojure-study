(+ 2 3)
;; 5

(+ 1 2 3 4)
;; 10

(+)
;; 0

(- 10 5)
;; 5
(* 3 10 10)
;; 300

(> 5 2)
;; true

(>= 5 5)
;; true

(< 5 2)
;; false

(= 5 2)
;; false

(/ 22 7)
;; 22/7 Clojure has a built-in ratio type.

(/ 22.0 7)
;; 3.14 -- Use floating-point literal for the dividend to get a decimal value.

(quot 22 7)
;; 3

(rem 22 7)
;; 1

;; Arbitrary-precision, floating-point math.
(+ 1 (/ 0.0001 1000000000000000000))
;; 1.0 -- without arbitrary-precision

(+ 1 (/ 0.0001M 1000000000000000000))
;; 1.00000000000000000000001M -- with arbitrary-precision

;; Arbitrary-precision integers.
(* 1000N 1000 1000 1000 1000 1000)
;; 1000000000000000000000N -- Note the N suffix.