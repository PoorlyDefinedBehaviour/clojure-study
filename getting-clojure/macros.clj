(defn print-rating [rating]
  (cond
    (pos? rating) (println "Good book!")
    (zero? rating) (println "Totally indiffrent!")
    :else (println "Run away!")))

(defn arithmetic-if [n pos zero neg]
  (cond
    (pos? n) pos
    (zero? n) zero
    (neg? n) neg))

(defn print-rating [rating]
  (arithmetic-if rating (println "Good book!") (println "Totally indifferent!") (println "Run away!")))

(print-rating  5)
;; => Good book!
;; => Totally indifferent!
;; => Run away!
;; nil

(defn arithmetic-if [n pos-fn zero-fn neg-fn]
  (cond
    (pos? n) (pos-fn)
    (zero? n) (zero-fn)
    (neg? n) (neg-fn)))

(defn print-rating [rating]
  (arithmetic-if rating #(println "Good book!") #(println "Totally indifferent!") #(println "Run away!")))

(print-rating 10)
;; => Good book!
;; nil

(defn arithmetic-if->cond [n pos zero neg]
  (list 'cond
        (list 'pos? n) pos
        (list 'zero? n) zero
        (list 'neg? n) neg))

(defn print-rating [rating]
  (eval (arithmetic-if->cond rating '(println "Good book!") '(println "Totally indifferent!") '(println "Run away!"))))
;; => Run away!
;; nil

(defmacro arithmetic-if [n pos zero neg]
  (list 'cond
        (list 'pos? n) pos
        (list 'zero? n) zero
        (list 'neg? n) neg))

(defmacro arithmetic-if [n pos zero neg]
  `(cond
     (pos? ~n) ~pos
     (zero? ~n) ~zero
     (neg? ~n) ~neg))

(defmacro when [condition & body]
  (list 'if condition (cons 'do body)))

(defmacro cond [& clauses]
  (when clauses
    (list 'if (first clauses)
          (if (next clauses)
            (second clauses)
            (throw (IllegalArgumentException "cond requires an even number of forms")))
          (cons 'cond (next (next clauses))))))

(defmacro and
  ([] true)
  ([x] x)
  ([x & xs] `(let [and# ~x]
               (if and# (and ~@xs) and#))))

(defmacro our-defn [name args & body]
  `(def ~name (fn ~args ~@body)))

(our-defn add-2 [a b] (+ a b))

(add-2 3 4)
;; 7
