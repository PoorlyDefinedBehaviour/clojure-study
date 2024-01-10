;; A functionc all is a list whose first element resolves to a function.

(str "hello" " " "world")
;; "hello world"

(string? "hello")
;; true

(keyword? :hello)
;; true

(symbol? 'hello)
;; true

(defn greeting
  "Returns a greeting of the form 'Hello, username"
  [username]
  (str "Hello, " username))

(greeting "world")
;; "Hello, world"

(defn greeting-2 
  "Returns a greeting of the form 'Hello, username'.
   Default username is 'world'"
  ([] (greeting "world"))
  ([username] (str "Hello, " username)))

(greeting-2)
;; "Hello, world"

(greeting-2 "a")
;; "Hello, a"

;; Variable arity parameter (variadics)
(defn date [person-1 person-2 & chaperones] 
  (println person-1 "and" person-2 "went out with" (count chaperones) "chaperones."))

(date "Romeo" "Juliet" "Friar Lawrence" "Nurse")
;; Romeo and Juliet went out with 2 chaperones.