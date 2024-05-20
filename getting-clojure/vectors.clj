(vector 1 true "hello") ; same as [1 true "hello"]
;; [1 true "hello"]

(def novels ["Emma" "Coma" "War and Peace"])
(count novels)
;; 3 

(first novels)
;; "Emma"

(rest novels)
;; ("Coma" "War and Peace")

(rest ["Ready player one"])
;; ()

(rest [])
;; ()

(conj novels "Carrie")
;; "Emma" "Coma" "War and Peace" "Carrie"

(cons "Carrie" novels)
;; ("Carrie" "Emma" "Coma" "War and Piece")