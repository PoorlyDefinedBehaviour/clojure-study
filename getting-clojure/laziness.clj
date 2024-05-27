(def jack "All work and no play makes Jack a dull boy.")

(def text [jack jack jack jack jack jack jack jack jack jack])

(def repeated-text (repeat jack))

(first repeated-text)
;; "All work and no play makes Jack a dull boy."

(nth repeated-text 10)
;; "All work and no play makes Jack a dull boy."

(take 7 (cycle [1 2 3]))
;; (1 2 3 1 2 3 1)

(def numbers (iterate inc 1))
(take 5 numbers)
;; (1 2 3 4 5)

(def evens (map #(* 2 %) (iterate inc 1)))
(take 20 evens)
;; (2 4 6 8 10 12 14 16 18 20 22 24 26 28 30 32 34 36 38 40)

(def numbers (iterate inc 1))

(def titles (map #(str "Wheel of time, Book " %) numbers))

(def first-names ["Bob" "Jane" "Chuck" "Leo"])
(def last-names ["Jordan" "Austen" "Dickens" "Tolstoy" "Poe"])

(defn combine-names [first-name last-name]
  (str first-name " " last-name))

(def authors (map combine-names
                  (cycle first-names)
                  (cycle last-names)))

(defn make-book [title author]
  {:title title
   :author author})

(def test-books (map make-book titles authors))

(defn chatty-vector []
  (println "chatty-vector println")
  [1 2 3])

;; chatty-vector is not executed.
(def s (lazy-seq (chatty-vector)))

(first s)
;; => chatty-vector println
;; 1

(defn my-repeat [x]
  (cons x (lazy-seq (my-repeat x))))

(defn my-iterate [f x]
  (cons x (lazy-seq (my-iterate f (f x)))))

(doseq [c (lazy-seq '("a"  "b" "c"))]
  (println c))