(range 5) ; end only
;; (0 1 2 3 4)

(range 1 5 1)
;; (1 2 3 4) non-inclusive

(repeat 3 5)
;; (5 5 5)

(take 10 (iterate inc 1))
;; (1 2 3 4 5 6 7 8 9 10)

(take 20 (repeat 1))
;; (1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1)

(def whole-numbers (iterate inc 1))

(take 10 (cycle (range 3)))
;; (0 1 2 0 1 2 0 1 2 0)

(interleave whole-numbers ["A" "B" "C" "D" "E"])
;; (1 "A" 2 "B" 3 "C" 4 "D" 5 "E")

(interpose "," ["apples" "bananas" "grapes"])
;; ("appleas" "," "bananas" "," "grapes")

(apply str (interpose "," ["apples" "bananas" "grapes"]))
;; "apples,bananas,grapes"

(require '[clojure.string :refer [join]])

(join \, ["apples" "bananas" "grapes"]) ; same as (apply str (interpose separator coll))
;; "apples,bananas,grapes"

;; Sequence comprehension.
(for [word ["the" "quick" "brown" "fox"]]
  (format "<p>%s</p>" word))
;; ("<p>the</p>" "<p>quick</p>" "<p>brown</p>" "<p>fox</p>")

(take 10 (for [n whole-numbers :when (even? n)] n))
;; (2 4 6 8 10 12 14 16 18 20)

(for [n whole-numbers :while (even? n)] n)
;; ()

(for [file "ABCDEFGH"
      rank (range 1 9)]
  (format "%c%d" file rank))
;; ("A1" "A2" "A3" "A4" "A5" "A6" "A7" "A8" "B1" "B2" "B3" "B4" "B5" "B6" "B7" "B8" "C1" "C2" "C3" "C4" "C5" "C6" "C7" "C8" "D1" "D2" "D3" "D4" "D5" "D6" "D7" "D8" "E1" "E2" "E3" "E4" "E5" "E6" "E7" "E8" "F1" "F2" "F3" "F4" "F5" "F6" "F7" "F8" "G1" "G2" "G3" "G4" "G5" "G6" "G7" "G8" "H1" "H2" "H3" "H4" "H5" "H6" "H7" "H8")