(def visits (atom []))

(defn record-visit! [ip]
  (swap! visits conj ip))

(def visitors-2 (atom #{}))

(defn record-visitor! [ip]
  (swap! visitors-2 conj ip))