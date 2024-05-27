(def title-seq (seq ["Emma" "Oliver Twist" "Robinson Crusoe"]))

(seq {:title "Emma" :author "Austen" :published 1815})
;; ([:title "Emma"] [:author "Austen"] [:published 185])

;; seq returns nil for empty collections.
(seq [])
;; nil
(seq '())
;; nil
(seq {})
;; nil

(defn my-count [coll]
  (let [the-seq (seq coll)]
    (loop [n 0 s the-seq]
      (if (seq s)
        (recur (inc n) (rest s))
        n))))

(def titles ["Jaws" "Emma" "2001" "Dracula"])
(sort titles)
;; ("2001" "Dracula" "Emma" "Jaws")
(reverse titles)
;; ("Dracula" "2001" "Emma" "Jaws")

(def titles-and-authors ["Jaws" "Benchley" "2001" "Clarke"])

(partition 2 titles-and-authors)
;; (("Jaws" "Benchley") ("2001" "Clarke"))

(def titles ["Jaws" "2001"])
(def authors ["Benchley" "clarke"])
(interleave titles authors)
;; ("Jaws" "Benchley" "2001" "clarke")

(def scary-animals ["Lions" "Tigers" "Bears"])
(interpose "and" scary-animals)
;; ("Lions" "and" "Tigers" "and" "Bears")

(filter neg? '(1 -22 3 -99 4))
;; (-22 -99)

(def books
  [{:title "Deep Six" :price 13.99 :genre :sci-fi :rating 6}
   {:title "Dracula" :price 1.99 :genre :horror :rating 7}
   {:title "Emma" :price 7.99 :genre :comedy :rating 9}
   {:title "2001" :price 10.50 :genre :sci-fi :rating 5}])

(defn cheap? [book]
  (when (<= (:price book) 9.99)
    book))

(some cheap? books)
;; {:title "Dracula", :price 1.99, :genre :horror, :rating 7}

(filter cheap? books)
;; ({:title "Dracula", :price 1.99, :genre :horror, :rating 7} {:title "Emma", :price 7.99, :genre :comedy, :rating 9})

(def some-numbers [1 53 811])
(def doubled (map #(* 2 %) some-numbers))
;; (2 106 1622)

(map (fn [book] (:title book)) books)
;; ("Deep Six" "Dracula" "Emma" "2001")
(map :title books)
;; ("Deep Six" "Dracula" "Emma" "2001")

(require '[clojure.java.io :as io])

(defn listed-author? [author]
  (with-open [r (io/reader "authors.txt")]
    (some (partial = author) (line-seq r))))

(def re #"Pride and Prejudice.*")
(def title "Pride and Prejudice and Zombies")
(re-matches re title)
;; "Pride and Prejudice and Zombies"

(re-seq #"\w+" title)
;; ("Pride" "and" "Prejudice" "and" "Zombies")