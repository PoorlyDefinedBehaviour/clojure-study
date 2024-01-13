;; deref with a timeout and default value

(def the-answer (future (Thread/sleep 5000) 42))

;; Wait for 4000ms. Return :cheeseburger on timeout.
(deref the-answer 1000 :cheeseburger)
;; :cheeseburger