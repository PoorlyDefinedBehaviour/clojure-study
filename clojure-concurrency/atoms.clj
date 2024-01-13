(def donation-count (atom 0))

;; Start 9 people collecting money.
(dotimes [_ 9]
  (.start (Thread. (fn []
                    ;;  Wait for three seconds.
                     (Thread/sleep 3000)
                    ;;  Go collect $1.
                     (swap! donation-count inc)
                    ;;  Do it again.
                     (recur)))))

;; Pretend to send a tweet.
(defn tweet [msg]
  (println "tweeting: " msg))

;; Start one person tweeting.
(.start (Thread. (fn []
                  ;;  Wait 10 seconds.
                   (Thread/sleep 10000)
                   (tweet (str "We collected $" @donation-count " total!")))))
