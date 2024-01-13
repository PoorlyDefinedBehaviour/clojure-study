(defn square [x] (* x x))

(defn sum-squares-sq [n]
  (vec (map square (range n))))

(sum-squares-sq 5)
;; [0 1 4 9 16]

(defn sum-squares [n]
  (into [] (map square) (range n)))

(sum-squares 5)
;; [0 1 4 9 16]

(require '[clojure.string])

(defn preds-seq []
  (->> (all-ns)
       (map ns-publics)
       (mapcat vals)
       (filter #(clojure.string/ends-with? % "?"))
       (map #(str (.-sym %)))
       vec))

(defn preds []
  (into []
        (comp (map ns-publics)
              (mapcat vals)
              (filter #(clojure.string/ends-with? % "?"))
              (map #(str (.-sym %))))
        (all-ns)))