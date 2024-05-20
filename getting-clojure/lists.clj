'(1 2 3)
;; (1 2 3)

(list 1 2 3 "four" "five")
;; (1 2 3 "four" "five")

(def poems '("Iliad" "Odyssey" "Now We Are Six"))

(count poems)
;; 3

(first poems)
;; "Iliad"

(rest poems)
;; "Odyssey" "Now We Are Six"

(conj poems "Jabberwocky")
;; ("Jabberwocky" "Iliad" "Odyssey" "Now We Are Six")