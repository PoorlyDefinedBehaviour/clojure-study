(defn count-heads-pairs [coll]
  (loop [cnt 0 coll coll]
    (if (empty? coll)
      cnt
      (recur
       (if (= :h (first coll) (second coll))
         (inc cnt)
         cnt) (rest coll)))))


(count-heads-pairs [:h :h :h :t :h])
;; 2

(count-heads-pairs [:h :t :h :t :h])
;; 0
