;; ### Loop until variable is changed ###

(def keep-running? (volatile! true))

(def thread-1 (.start (Thread. (fn []
                                 (Thread/sleep 2000)
                                 (vreset! keep-running? false)))))

(def thread-2 (.start (Thread. (fn []
                                 (while @keep-running?
                                   (Thread/sleep 500)
                                   (println "Still running!"))))))

;; ### Mute switch example ###

(def mute? (volatile! false))

(defn mute! [] 
  (vreset! mute? true))

(defn unmute! [] 
  (vreset! mute? false))

(defn toggle-mute! []
  (vswap! mute? not))

(doseq [id (range 10)]
  (while true
    (Thread/sleep 1000)
    (when-not @mute?
      (println "Ping!" id))))