;; promise: calculates value in another thread

;; Create a promise.
(def the-answer (promise))

;; Start working on the promise in a new thread.
(doto (Thread. (fn [] 
                ;;  Do some heavy work and then resolve the promise.
                 (deliver the-answer 42)
                 ))
  .start)

;; In the origianl thread, do your own work and then get the answer from the promise.
;; (this will block if it's not there)
(println (deref the-answer))
;; 42

;; the promise can be dereferred mnay times.
(println (deref the-answer))
;; 42