(ns do-things.core
  (:gen-class)
  (:require [clojure.string :as str]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn example-op-1 []
  (+ 1 2 3))

(if true
  "By Zeus's hammer!"
  "By Aquaman's trident!")
;; "By Zeus's hammer!"

(if false
  "By Zeus's hammer!"
  "By Aquaman's trident!")
;; "By Aquaman's trident!"

(if false
  "By Odin's Elbow!")
;;  nil

(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))
;; Success!
;; "By Zeus's hammer!"

(when true
  (println "Success!")
  "abra cadabra")
;; Success!
;; "abra cadabra"

(nil? 1)
;; false

(nil? nil)
;; true

(= 1 1)
;; true

(= nil nil)
;; true

(= 1 2)
;; false

(or false nil :large_I_mean_venti :why_cant_I_just_say_large)
;; :large_I_mean_venti

(or (= 0 1) (= "yes" "no"))
;; false

(or nil)
;; nil

(and :free_wifi :hot_coffe)
;; :hot_coffe

(and :feeling_super_cool nil false)
;; nil

(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part [part]
  {:name (str/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining (into final-body-parts (set  [part (matching-part part)])))))))