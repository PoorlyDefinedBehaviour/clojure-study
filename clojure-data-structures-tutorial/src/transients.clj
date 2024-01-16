(time (reduce conj #{} (range 1000000)))
;; "Elapsed time: 1456.145802 msecs"

(time (persistent! (reduce conj! (transient #{}) (range 1000000))))
;; "Elapsed time: 428.903734 msecs"