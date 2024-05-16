(println "Hello world")
;; hello world
;; nil

(str "Clo" "jure")
;; "Clojure"

(count "Hello world")
;; 11

(println true)
;; true
;; nil


(+ 1900 84)
;; 1984

(def first-name "Russ")

(def the-average (/ (+ 20 40.0) 2.0))


(defn hello-world [] (println "Helo, world!"))

(defn say-welcome [what]
  (println "Welcome to" what))

(say-welcome "Clojure")
;; Welcome to Clojure
;; nil


(defn average [a b]
  (/ (+ a b) 2.0))

(average 5.0 10.0)
;; 7.5