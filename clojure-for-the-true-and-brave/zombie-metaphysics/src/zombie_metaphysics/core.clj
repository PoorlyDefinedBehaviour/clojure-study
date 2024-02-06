(ns zombie-metaphysics.core
  (:gen-class)
  (:require [clojure.string]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def fred (atom {:cuddle-hunger-level 0
                 :percent-deteriorated 0}))

@fred
{:cuddle-hunger-level 0, :percent-deteriorated 0}

(let [zombie-state @fred]
  (if (>= (:percent-deteriorated zombie-state) 50)
    (future (println (:cuddle-hunger-level zombie-state)))))

(swap! fred
       (fn [current-state]
         (merge-with + current-state {:cuddle-hunger-level 1})))
;; {:cuddle-hunger-level 1, :percent-deteriorated 0}

(defn increase-cuddle-hunger-level [zombie-state increase-by]
  (merge-with + zombie-state {:cuddle-hunger-level increase-by}))

(swap! fred increase-cuddle-hunger-level 10)
;; {:cuddle-hunger-level 21, :percent-deteriorated 0}

(update-in {:a {:b 3}} [:a :b] inc)
;; {:a {:b 4}}

(update-in {:a {:b 3}} [:a :b] + 10)
;; {:a {:b 13}}

(swap! fred update-in [:cuddle-hunger-level] + 10)
;; {:cuddle-hunger-level 31, :percent-deteriorated 0}

(let [num (atom 1)
      s1 @num]
  (swap! num inc)
  (println "State 1:" s1)
  (println "Current state:" @num))
;; State 1: 1
;; Current state: 2
;; nil

(reset! fred {:cuddle-hunger-level 0
              :percent-deteriorated 0})
;; {:cuddle-hunger-level 0, :percent-deteriorated 0}

(defn shuffle-speed [zombie]
  (* (:cuddle-hunger-level zombie)
     (- 100 (:percent-deteriorated zombie))))

(defn shuffle-alert [key _watched _old-state new-state]
  (let [sph (shuffle-speed new-state)]
    (if (> sph 5000)
      (do  (println "Run, you fool!")
           (println "The zombie's SPH is now " sph)
           (println "This message brought to your courtesy of " key))
      (do (println "All's well with " key)
          (println "Cuddle hunger: " (:cuddle-hunger-level new-state))
          (println "Percent deteriorated: " (:percent-deteriorated new-state))
          (println "SPH: " sph)))))

(add-watch fred :fred-shuffle-alert shuffle-alert)

(swap! fred update-in [:percent-deteriorated] + 1)
;; All's well with  :fred-shuffle-alert
;; Cuddle hunger:  0
;; Percent deteriorated:  1
;; SPH:  0
;; {:cuddle-hunger-level 0, :percent-deteriorated 1}

(defn percent-deteriorated-validator
  [{:keys [percent-deteriorated]}]
  (or (and (>= percent-deteriorated 0)
           (<= percent-deteriorated 100))
      (throw (IllegalStateException. "That's not mathy!"))))

(def bobby (atom {:cuddle-hunger-level 0
                  :percent-deteriorated 0}
                 :validator percent-deteriorated-validator))

(swap! bobby update-in [:percent-deteriorated] + 200)
;; Exception: That's not mathy!

(def sock-varieties
  #{"darned" "argyle" "wool" "horsehair" "mulleted"
    "passive-aggressive" "striped" "polka-dotted"
    "athletic" "business" "power" "invisible" "gollumed"})

(defn sock-count [sock-variety count]
  {:variety sock-variety
   :count count})

(defn generate-sock-gnome
  "Create an initial sock gnome state with no socks"
  [name]
  {:name name
   :socks #{}})

(def sock-gnome (ref (generate-sock-gnome "Barumpharumph")))
(def dryer (ref {:name "LG 1337"
                 :socks (set (map #(sock-count % 2) sock-varieties))}))

(defn steal-sock [gnome dryer]
  (dosync
   (when-let [pair (some #(if (= (:count %) 2) %) (:socks @dryer))]
     (let [updated-count (sock-count (:variety pair) 1)]
       (alter gnome update-in [:socks] conj updated-count)
       (alter dryer update-in [:socks] disj pair)
       (alter dryer update-in [:socks] conj updated-count)))))

(steal-sock sock-gnome dryer)

(def counter (ref 0))
(future
  (dosync
   (alter counter inc)
   (println "first println:" @counter)
   (Thread/sleep 500)
   (alter counter inc)
   (println "second println:" @counter)))
(Thread/sleep 250)
(println "println in main thread:" @counter)

(defn sleep-print-update [sleep-time thread-name update-fn]
  (fn [state]
    (Thread/sleep sleep-time)
    (println (str thread-name ": " state))
    (update-fn state)))

(def counter (ref 0))

(future (dosync (commute counter (sleep-print-update 100 "Thread A" inc))))
(future (dosync (commute counter (sleep-print-update 150 "Thread B" inc))))

(def ^:dynamic *notification-address* "bobby@elf.org")

(binding [*notification-address* "test@elf.og"]
  *notification-address*)
;; "test@elf.og"

(defn notify [message]
  (str "TO: " *notification-address* "\n"
       "MESSAGE:" message))

(notify "I fell.")
;; "TO: bobby@elf.org\nMESSAGE:I fell."

(binding [*notification-address* "test@elf.org"]
  (notify "test!"))
;; "TO: test@elf.org\nMESSAGE:test!"

(def power-source "hair")

(alter-var-root #'power-source (fn [_] "7-eleven parking lot"))

power-source
;; "7-eleven parking lot"

(def alphabet-length 26)

(def letters (mapv (comp str char (partial + 65)) (range alphabet-length)))

(defn random-string
  "Returns a random string of specified length"
  [length]
  (apply str (take length (repeatedly #(rand-nth letters)))))

(defn random-string-list [list-length string-length]
  (doall (take list-length (repeatedly (partial random-string string-length)))))

(def orc-names (random-string-list 3000 7000))

(time (dorun (map clojure.string/lower-case orc-names)))
;; "Elapsed time: 171.552416 msecs"

(time (dorun (pmap clojure.string/lower-case orc-names)))
;; "Elapsed time: 231.487653 msecs"