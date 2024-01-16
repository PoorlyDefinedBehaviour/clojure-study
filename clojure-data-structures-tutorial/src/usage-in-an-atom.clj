(def queue (atom clojure.lang.PersistentQueue/EMPTY))

(defn queue!
  "Add a value to the end of the queue."
  [value]
  (swap! queue conj value)

  nil)

(defn dequeue!
  "Remove and return the first item from the queue."
  []
  (let [[old _new] (swap-vals! queue pop)]
    (peek old)))

(queue! 1)
(queue! 2)
(queue! 3)

(println (dequeue!))
;; 1

(println (dequeue!))
;; 2

(println (dequeue!))
;; 3

(println (dequeue!))
;; nil