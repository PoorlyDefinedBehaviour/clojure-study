(def total-donations (ref 0))

(def count-donations (ref 0))

;; Start 9 people collecting money.
(dotimes [_ 9]
  (.start (Thread. (fn []
                     (dosync
                      (alter total-donations
                             + 10)
                      (alter count-donations inc))
                     (recur)))))

;; Pretend to tweet.
(defn tweet [msg]
  (println "tweeting: " msg))

;; Start one person tweeting the total.
(.start (Thread. (fn []
                   (Thread/sleep 10000)
                   (tweet (str "We collected $" @total-donations " total!")))))

;; Start one person tweeting the average.
(.start (Thread. (fn []
                   (Thread/sleep 10000)
                   (when (pos? @count-donations)
                     (tweet (str "Average donation: $"
                                 (double (dosync (/ @total-donations @count-donations)))))))))