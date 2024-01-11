(def current-track (atom "Venus, the Bringer of Peace"))

@current-track
;; "Venus, the Bringer of Peace"

(reset! current-track "Credo")

@current-track
;; Credo

(def current-track-2 (atom {:title "Credo" :composer "Byrd"}))

@current-track-2
;; {:title "Credo", :composer "Byrd"}

(reset! current-track-2 {:title "Spem in Alium" :composer "Tallis"})

@current-track-2
;; {:title "Spem in Alium", :composer "Tallis"}

(swap! current-track-2 assoc :title "Sancte Deus")

@current-track-2
;; {:title "Sancte Deus", :composer "Tallis"}