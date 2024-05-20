(def dracula {:title "Dracula"
              :author "Stoker"
              :price 1.99
              :genre :horror})

(defn cheap? [book]
  (when (<= (:price book) 9.99)
    book))

(defn pricey? [book]
  (when (> (:price book) 9.99)
    book))

(cheap? dracula)
;; true

(pricey? dracula)
;; false

(fn [n] (* n 2))

(def double-it (fn [n] (* n 2)))
(double-it 10)
;; 20