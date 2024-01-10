(defn indexed [coll] (map-indexed vector coll))

(indexed "abcde")
;; ([0 \a] [1 \b] [2 \c] [3 \d] [4 \e])

(defn index-filter [pred coll]
  (when pred
    (for [[idx elt] (indexed coll) :when (pred elt)] idx)))

(index-filter #{\a \b} "abcdbbb")
;; (0 1 4 5 6)

(index-filter #{\a \b} "xyz")
;; ()

(defn index-of-any [pred coll]
  (first (index-filter pred coll)))