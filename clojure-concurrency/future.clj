;; future: like a promise but the expression is evaluated in another Thread automatically. 

(def the-answer (future 
                  ;; do some work and then deliver the answer
                  42))

;; the-answer is evaluated in another thread. wait for the result by calling deref.
(println (deref the-answer))
;; 42

;; Can be dereferred many times.
(println (deref the-answer))
;; 42

;; The exception gets thrown but stored in the Future.
;; Exceptions are thrown when the future is dereferred.
(def f (future (throw (Exception. "Hello from the future!"))))


(deref f) ; this will throw the exception