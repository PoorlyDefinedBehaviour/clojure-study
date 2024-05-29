(def counter (atom 0))

(defn greeting-message [_req]
  (swap! counter inc)
  (if (zero? (mod @counter 100))
    (str "Congrats! You are the" @counter "visitor!")
    "Welcome"))

(def by-title (atom {}))

(defn add-book [{:keys [title] :as book}]
  (swap! by-title assoc title book))

(defn del-book [title]
  (swap! by-title dissoc title))

(defn find-book [title]
  (get @by-title title))

(def by-title (ref {}))
(def total-copies (ref 0))

(defn add-book [{:keys [title] :as book}]
  (dosync
   (alter by-title assoc title book)
   (alter total-copies + (:copies book))))

(def by-title (agent {}))

(defn add-book [{:keys [title] :as book}]
  (send by-title (fn [by-title-map] (assoc by-title-map title book))))

(shutdown-agents)