(defn greet
  ([to-whom] (println "Welcome to Blotts Books" to-whom))
  ([message to-whom] (println message to-whom)))

(greet "Dolly")
;; Welcome to Blotts Books Dolly
;; nil

(greet "Howdy" "Stranger")
;; Howdy Stranger
;; nil

(defn print-any-ags [& args]
  (println "My arguments are:" args))
;; My arguments are: (hello world)
;; nil

(defn dispatch-book-format [book]
  (cond
    (vector? book) :vector-book
    (contains? book :title) :standard-map
    (contains? book :book) :alternative-map))

(defmulti normalize-book dispatch-book-format)

(defmethod normalize-book :vector-book
  [book]
  {:title (first book)
   :author (second book)})

(defmethod normalize-book :standard-map
  [book]
  book)

(defmethod normalize-book :alternative-map
  [book]
  {:title (:book book)
   :author (:by book)})

(defn average
  "Returns the average of a and b"
  [a b]
  (/ (+ a b) 2.0))

;; Get the documentation for a function using the doc function in the REPL.
;; (doc average)
;; user/average
;; ([a b])
;; Returns the average of a and b
;; nil

;; ## Pre and post conditions

(defn publish-book [book]
  ;; Pre conditions must be in a vector of expressions.
  {:pre [(:title book) (:author book)]
  ;; % is used as a placeholder for the return value.
   :post (boolean? %)}
  (println "printing book")
  (println "shipping book"))

(defn one-two-or-more
  ([a] (println "one arg:" a))
  ([a b] (println "two args:" a b))
  ([a b & more] (println "more than two args:" a b more)))