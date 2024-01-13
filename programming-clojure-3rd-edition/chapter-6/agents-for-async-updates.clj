(def counter (agent 0))

(send counter inc)

@counter
;; 1

(def counter-2 (agent 0 :validator number?))

(send counter-2 (fn [_] "boo"))

(defn handler [_agent err]
  (println "ERR!" (.getMessage err)))

(def counter-3 (agent 0 :validator number? :error-handler handler))

(send counter-3 (fn [_] "boo"))

(send counter-3 inc)

(await counter-3)

@counter-3
;; 1

;; ### Including agents in transactions ###

(def backup-agent (agent "./messages-backup.clj"))

(def messages (ref ()))

(defn add-message-with-backup [msg]
  (dosync (let [snapshot (commute messages conj msg)]
            (send-off backup-agent (fn [filename] (spit filename snapshot) filename))
            snapshot)))

(defrecord Message [sender text])

(add-message-with-backup (->Message "John" "Message one"))
