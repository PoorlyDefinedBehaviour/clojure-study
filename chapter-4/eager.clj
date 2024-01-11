(defn square [x] (* x x))

(defn sum-squares-sq [n]
  (vec (map square (range n))))

(sum-squares-sq 5)
;; [0 1 4 9 16]

(defn sum-squares [n]
  (into [] (map square) (range n)))

(sum-squares 5)
;; [0 1 4 9 16]