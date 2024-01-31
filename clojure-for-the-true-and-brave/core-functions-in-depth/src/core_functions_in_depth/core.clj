(ns core-functions-in-depth.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def human-consumption   [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])

(defn unify-diet-data [human critter]
  {:human human
   :critter critter})

(map unify-diet-data human-consumption critter-consumption)
;; ({:human 8.1, :critter 0.0} {:human 7.3, :critter 0.2} {:human 6.6, :critter 0.3} {:human 5.0, :critter 1.1})

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))

(defn stats [numbers]
  (map #(% numbers) [sum count avg]))
;; (stats [3 4 10])
;; (17 3 17/3)

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

(map :real identities)
;; ("Bruce Wayne" "Peter Parker" "Your mom" "Your dad")

(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val))) {} {:max 30 :min 10})
;; {:max 31, :min 11}

(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map)) {} {:human 4.1
                          :critter 3.9})
;; {:human 4.1}

(take 3 [1 2 3 4 5 6 7 8 9 10])
;; (1 2 3)

(drop 3 [1 2 3 4 5 6 7 8 9 10])
;; (4 5 6 7 8 9 10)

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(take-while #(< (:month %) 3) food-journal)
;; ({:month 1, :day 1, :human 5.3, :critter 2.3} {:month 1, :day 2, :human 5.1, :critter 2.0} {:month 2, :day 1, :human 4.9, :critter 2.1} {:month 2, :day 2, :human 5.0, :critter 2.5})

(drop-while #(< (:month %) 3) food-journal)
;; ({:month 3, :day 1, :human 4.2, :critter 3.3} {:month 3, :day 2, :human 4.0, :critter 3.8} {:month 4, :day 1, :human 3.7, :critter 3.9} {:month 4, :day 2, :human 3.7, :critter 3.6})

(filter #(< (:human %) 5) food-journal)
;; ({:month 2, :day 1, :human 4.9, :critter 2.1} {:month 3, :day 1, :human 4.2, :critter 3.3} {:month 3, :day 2, :human 4.0, :critter 3.8} {:month 4, :day 1, :human 3.7, :critter 3.9} {:month 4, :day 2, :human 3.7, :critter 3.6})

(filter #(< (:month %) 3) food-journal)
;; ({:month 1, :day 1, :human 5.3, :critter 2.3} {:month 1, :day 2, :human 5.1, :critter 2.0} {:month 2, :day 1, :human 4.9, :critter 2.1} {:month 2, :day 2, :human 5.0, :critter 2.5})

(some #(> (:critter %) 5) food-journal)
;; nil

(some #(> (:critter %) 3) food-journal)
;; true

(sort [3 1 2])
;; (1 2 3)

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(defn vampire-related-details [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire? [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire [social-security-numbers]
  (first (filter vampire? (map vampire-related-details social-security-numbers))))

(max [0 1 2])
;; [0 1 2]

(apply max [0 1 2])
;; 2

(def add10 (partial + 10))
(add10 3)
;; 13
