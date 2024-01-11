(def current-track (ref "Mars, the Bringer of War"))

(def current-composer (ref "Holst"))

(deref current-track)
;; "Mars, the Bringer of War"

(@current-track)
;; "Mars, the Bringer of War"

(dosync
 (ref-set current-track "Venus, the Bringer of Pace")
 (ref-set current-composer "Byrd"))

@current-track
;; "Venus, the Bringer of Pace"

@current-composer
;; "Byrd"

(defrecord Message [sender text])

(->Message "Aaron" "hello")
;; #:user.Message{:sender "Aaron" :text "Hello"}

(def messages (ref ()))

;; bad way of appending to the list
(defn naive-add-message [msg]
  (dosync (ref-set messages (cons msg @messages))))

(defn better-add-message [msg]
  (dosync (alter messages conj msg)))

(better-add-message (->Message "user 1" "Hello"))
;; (#user.Message{:sender "user 1", :text "Hello"})

(better-add-message (->Message "user 2" "Howdy"))
;; (#user.Message{:sender "user 2", :text "Howdy"} #user.Message{:sender "user 1", :text "Hello"})

(defn add-message-commute [msg]
  (dosync (commute messages conj msg)))

(def counter (ref 0))

(defn next-counter [] (dosync (alter counter inc)))

(next-counter)
;; 1

(next-counter) 2

;; ### Adding validation to Refs ###

(defn valid-message? [msg]
  (and (:sender msg) (:text msg)))

(def validate-message-list #(every? valid-message? %))

(def validated-messages (ref () :validator validate-message-list))

(defn add-message [msg]
  (dosync (alter validated-messages conj msg)))

;; message missing the text

(add-message "not a valid message")
;; Exception: Invalid reference state
