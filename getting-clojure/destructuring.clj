(def artists [:monet :austen])

(let [painter (first artists)
      novelist (second artists)]
  (println "The painter is:" painter "and the novelist is" novelist))

(let [[painter novelist] artists]
  (println "The painter is:" painter "and the novelist is" novelist))

(def pairs [[:monet :austen] [:beethoven :dickinson]])

(let [[[painter] [composer]] pairs]
  (println "The painter is" painter) (println "The composer is" composer))

(let [[a b c d] "Jane"]
  (println a b c d))
;; => J a n e
;; nil

(defn artist-description [[novelist poet]]
  (println "The novelist is" novelist "and the post is" poet))

(artist-description [:austen :dickinson])
;; => The novelist is  :austen  and the post is  :dickinson
;; nil

(defn artist-description [shout [novelist poet]]
  (let [msg (str "Novelist is" novelist "and the post is" poet)]
    (if shout (.toUpperCase msg) msg)))

(def artist-map {:painter :monet :novelist :austen})

(let [{painter :painter novelist :novelist} artist-map]
  [painter novelist])
;; [:monet :austen]

(defn character-desc [{:keys [name age gender]}]
  [name age gender])

(defn add-greeting [character]
  (let [{:keys [name age]} character]
    (assoc character :greeting (str "Hello, my name is" name "and I am" age "."))))

(defn add-greeting [{:keys [name age] :as character}]
  (assoc character :greeting (str "Hello, my name is" name "and I am" age ".")))