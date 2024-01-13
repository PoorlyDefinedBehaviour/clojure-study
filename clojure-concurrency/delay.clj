;; delay:
;; form is not evaluated until it deref is called.
;; result of form evaluation is cached and returned for subsequent calls.

;; Pretend it takes a long time.
(defn long-calculation [x] x)

;; Make a delay that does some calculation.
;; The calculation has not been done at this point.
(def the-answer (delay (* 100 (long-calculation 45))))


(def need-the-answer? true)

(when need-the-answer?
  ;; The thread will block until the answer is calculated.
  (println (deref the-answer)))

;; Pretend it does some expensive initialization.
(defn initialize-shared-resource [])

;; Do not initialize the resource yet.
(def resource (delay (initialize-shared-resource)))

;; Start 100 threads that use the resource.
(defn -main []
  (dotimes [_ 100]
    (doto (Thread. (fn []
                    ;;  initialize-shared-resource is called only once by the first thread that derefs the delay. 
                     (let [resource @resource]
                      ;;  use the resource here
                       ))
                   ) .start)))