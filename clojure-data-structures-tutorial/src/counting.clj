(def visits (atom {"1.1.1.1" 102
                   "2.2.2.2" 80
                   "127.0.0.1" 1008}))

(count @visits)
;; 3

;; Vector, List, Queue - Compare equality by comparing items by equality in order.
;; HashMap (and Sorted Map) - Compare equality by comparing all key-value pairs by equality, without order.
;; Set (and Sorted Set) - Compare equality by comparing all elements by equality, without order.
(= [1 2 3] '(1 2 3))
;; true -- because the elements are the same