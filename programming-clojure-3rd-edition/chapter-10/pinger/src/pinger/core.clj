(ns pinger.core
  (:require [pinger.scheduler :as scheduler])
  (:import [java.net HttpURLConnection URL]))

(defn response-code [address]
  (let [conn ^HttpURLConnection (.openConnection (URL. address))
        code (.getResponseCode conn)]
    (when (< code 400)
      (-> conn .getInputStream .close))
    code))

(defn available? [address]
  (= 200 (response-code address)))

(available? "http://google.com")
;; true

(available? "http://google.com/badurl")
;; false

(defn check []
  (let [addresses ["https://google.com"
                   "https://clojure.org"
                   "https://google.com/badurl"]]
    (doseq [address addresses]
      (println address ":" (available? address)))))

(def immediately 0)
(def every-minute (* 60 1000))

(defn start [e]
  "REPL helper. Start pinger on executor e."
  (scheduler/periodically e check immediately every-minute))

(defn stop [e]
  "REPL helper. Stop executor e."
  (scheduler/shutdown-executor e))

(defn -main []
  (start (scheduler/schedule-executor 1)))