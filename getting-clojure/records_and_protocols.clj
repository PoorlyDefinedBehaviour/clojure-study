(defrecord FictionalCharacter [name appears-in author])

(def watson (->FictionalCharacter "Watson" "Sign of the four" "Doyle"))

(def elizabeth (map->FictionalCharacter {:name "Elizabeht Bennet"
                                         :appears-in "Pride & Prejudice"
                                         :author "Austen"}))

(:appears-in watson)
;; "Sign of the four"

(:name elizabeth)
;; "Elizabeht Bennet"

(def specific-watson (assoc watson :appears-in "Sign of the four"))

;; Only the fields specified in the record get the speed boost.
(def more-about-watson (assoc watson :address "221B Baker Street"))

(class watson)
;; user.FictionalCharacter

;; Don't do this. Use protocols.
(defn process-thing [x]
  (if (instance? FictionalCharacter x)
    (println "fictional character")
    (println "not fictional character")))

(defprotocol Person
  (full-name [this])
  (greeting [this msg])
  (description [this]))

(defrecord FictionalCharacter [name appears-in author]
  Person
  (full-name [this] (:name this))
  (greeting [this msg] (str msg " " (:name this))) (description [this]
                                                     (str (:name this) " is a character in " (:appears-in this))))

(defrecord Employee [first-name last-name department]
  Person
  (full-name [this] (str first-name " " last-name))
  (greeting [this msg] (str msg " " (:first-name this)))
  (description [this] (str (:first-name this) "works in" (:department this))))

(def alice (map->Employee {:first-name "Alice" :last-name "Smith" :department "Engineering"}))

(def sofia (->Employee "Sofia" "Diego" "Finance"))
(def sam (->FictionalCharacter  "Sam Weller" "The Pickwick Papers" "Dickens"))

(full-name sofia)
(description sofia)
(greeting sofia "Hello")

(full-name sam)
(description sam)
(greeting sam "Hello")

(defprotocol Marketable
  (make-slogan [this]))

;; Extend types without modifying their definitions.
(extend-protocol Marketable
  Employee
  (make-slogan [e] (str (:first-name e) "is the best employee"))
  FictionalCharacter
  (make-slogan [c] (str (:name c) "is the best character")))

(make-slogan sofia)
(make-slogan sam)