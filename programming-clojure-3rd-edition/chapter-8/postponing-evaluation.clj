(def slow-calc (delay (Thread/sleep 5000) "done!"))

(force slow-calc)
;; done!

(force slow-calc)
;; done!