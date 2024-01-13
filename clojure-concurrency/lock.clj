(def log-lock (Object.))

(defn log [& args]
  (locking log-lock
    (apply println args)))

(log "INFO 2017-4-29: Starting database connection.")

(log "WARNING 2017-4-29: Cannot find configuration file, using defaults.")