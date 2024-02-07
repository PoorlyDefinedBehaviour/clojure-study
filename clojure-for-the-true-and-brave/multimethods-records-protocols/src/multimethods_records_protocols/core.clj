(ns multimethods-records-protocols.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defmulti full-moon-behavior (fn [were-creature] (:were-type were-creature)))

(defmethod full-moon-behavior :wolf
  [were-creature]
  (str (:name were-creature) " will howl and muder"))

(defmethod full-moon-behavior :simons
  [were-creature]
  (str (:name were-creature) " will encourage people and sweat to the oldies"))

(defmethod full-moon-behavior nil
  [were-creature]
  (str (:name were-creature) " will stay at home and eat ice cream"))

(defmethod full-moon-behavior :default
  [were-creature]
  (str (:name were-creature) " will stay up all night fantasy footballing"))

(full-moon-behavior {:were-type :wolf
                     :name "Rachel from the next door"})
;; "Rachel from the next door will howl and muder"

(full-moon-behavior {:were-type :simons
                     :name "Andy the baker"})
;; "Andy the baker will encourage people and sweat to the oldies"

(full-moon-behavior {:were-type nil
                     :name "Martin the nurse"})
;; "Martin the nurse will stay at home and eat ice cream"

(full-moon-behavior {:were-type :other
                     :name "Foo"})
;; "Foo will stay up all night fantasy footballing"

(ns random-namespace
  (:require [multimethods-records-protocols.core]))

;; Extend the multimethod.
(defmethod multimethods-records-protocols.core/full-moon-behavior :bill-murray
  [were-creature]
  (str (:name were-creature) " will be the most likeable celebrity"))

(multimethods-records-protocols.core/full-moon-behavior {:were-type :bill-murray
                                                         :name "Laura the intern"})
;; "Laura the intern will be the most likeable celebrity"

(ns user)

(defmulti types (fn [x y] [(class x) (class y)]))

(defmethod types [java.lang.String java.lang.String]
  [_ _]
  "Two strings!")

(types "String 1" "String 2")
;; "Two strings!"

(ns data-psychology)

(defprotocol Psychodynamics
  "Plumb the inner depths of your data types"
  (thoughts [x] "The data type's innermost thoughts")
  (feelings-about [x] [x y] "Feelings about self or other"))

(extend-type java.lang.String
  Psychodynamics
  (thoughts [x] (str x " thinks, 'Truly, the character defines the data type'"))
  (feelings-about
    ([x] (str x " is longing for a simpler way of life"))
    ([x y] (str x " is envioys of " y "'simpler way of life'"))))

(thoughts "blorb")
;; "blorbthinks, 'Truly, the character defines the data type'"

(feelings-about "schmorb")
;; "schmorb is longing for a simpler way of life"

(feelings-about "schmorb" 2)
;; "schmorb is envioys of 2'simpler way of life'"

(extend-protocol Psychodynamics
  java.lang.String
  (thoughts [_] "Truly, the character defines the data type")
  (feelings-about
    ([_] "longing for a simpler way of life")
    ([x y] (str x " envious of " y "'s simpler way of life")))

  java.lang.Object
  (thoughts [_] "Maybe the Internet is just a vector for toxoplasmosis")
  (feelings-about
    ([_] "meh")
    ([_ y] (str "meh about " y))))
(ns were-records)

(defrecord WereWolf [name title])

(WereWolf. "David" "Long Tourist")

(->WereWolf "Jacob" "Lead Shirt Discarder")

(map->WereWolf {:name "Lucian" :title "Ceo of Melodrama"})

(ns monster-mash
  (:import [were_records WereWolf ->WereWolf]))
(WereWolf. "David" "Longon Tourist")

(def jacob (->WereWolf "Jacob" "Lead Shirt Discarder"))

(.name jacob)
;; "Jacob"

(:name jacob)
;; "Jacob"

(get jacob :Name)
;; "Jacob"

(= jacob (->WereWolf "Jacob" "Lead Shirt Discarder"))
;; true

(= jacob (WereWolf. "David" "London Tourist"))
;; false

(= jacob {:name "Jacob" :title "Lead Shirt discarder"})
;; false

(defprotocol WereCreature
  (full-moon-behavior [x]))

(defrecord WereWolf [name title]
  WereCreature
  (full-moon-behavior [_]
    (str name " will howl and murder")))

(full-moon-behavior (map->WereWolf {:name "Lucian" :title "CEO of Melodrama"}))