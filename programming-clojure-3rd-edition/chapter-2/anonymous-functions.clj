;; Anonymous functions are created with fn.

(require '[clojure.string])

(defn indexable-word? [word] (> (count word) 2))

(filter indexable-word? (clojure.string/split "A fine day it is" #"\W+"))
;; ("fine" "day")

(filter (fn [w] (> (count w ) 2)) (clojure.string/split "A fine day it is" #"\W+"))
;; ("fine" "day")

;; % is the first parameter.
(filter #(> (count %) 2) (clojure.string/split "A fine day it is" #"\W+"))
;; ("fine" "day")

(defn indexable-words [text]
  (let [indexable-word? (fn [w] (> (count w) 2))]
    (filter indexable-word? (clojure.string/split text #"\W+"))))

(indexable-words "a fine day it is")
;; ("fine" "day")

;; Returns a function.
(defn make-greeter [greeting-prefix] 
  (fn [username] (str greeting-prefix ", " username)))

(def hello-greeting (make-greeter "Hello"))

(hello-greeting "world")
;; "Hello, world"

(def aloha-greeting (make-greeter "Aloha"))

(aloha-greeting "world")
;; "Aloha, world"