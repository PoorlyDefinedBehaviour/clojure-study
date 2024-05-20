(def genres #{:sci-fi :romance :mystery})
(def authors #{"Dickens" "Austen" "King"})

(contains? authors "Austen")
;; true
(contains? genres "Austen")
;; false

(authors "Austen")
;; true

(:historical genres)
;; nil

(conj authors "Clarke")
;; #{"King" "Dickens" "Clarke" "Austen"}

(disj authors "King")
;; #{"Dickens" "Austen"}