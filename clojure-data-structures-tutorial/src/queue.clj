;; There's no literal syntax or function to create one.

(def queue clojure.lang.PersistentQueue/EMPTY)

(conj queue 1 2 3)
;; (1 2 3)

(peek (conj queue 1 2 3))
;; 1

(let [queue (conj queue 1 2 3)
      popped-queue (pop queue)] 
  (println "first element from queue:" (peek queue)) 
  (println "first element from popped-queue:" (peek popped-queue)))
;; first element from queue: 1
;; first element from popped-queue: 2