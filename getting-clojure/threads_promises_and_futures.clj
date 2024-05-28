(defn do-something-in-a-thread []
  (println "Hello from the thread")
  (println "Good bye from the thread"))

(def the-thread (Thread. do-something-in-a-thread))

(.start the-thread)

(def del-thread (Thread. #(.delete (java.io.File. "temp-titles.txt"))))
(.start del-thread)
(.join del-thread)

(def the-result (promise))
(deliver the-result "Emma")
(println "the value in the promise is:" (deref the-result))
;; or
(println "the value in the promise is:" @the-result)

(def inventory [{:title "Emma" :sold 51 :revenue 255}
                {:title "2001" :sold 17 :revenue 170}
                ;; Lots and lots of books...
                ])

(defn sum-copies-old [inventory]
  (apply + (map :sold inventory)))

(defn sum-revenue [inventory]
  (apply + (map :revenue inventory)))

(let [copies-promise (promise)
      revenue-promise (promise)]
  (.start (Thread. #(deliver copies-promise (sum-copies-old inventory))))
  (.start (Thread. #(deliver revenue-promise (sum-revenue inventory))))
  (println "copies:" @copies-promise)
  (println "revenue:" @revenue-promise))

(def revenue-future (future (apply + (map :revenue inventory))))
(println "revenue future:" @revenue-future)