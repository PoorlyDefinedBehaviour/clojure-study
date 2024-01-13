Math/PI
;; 3.141592653589793

(Math/pow 10 3)
;; 1000.0

;; I wouldn't define this, just type Math/PI, it's not that long.
(def PI Math/PI)

(defn pow [b e] (Math/pow b e))

