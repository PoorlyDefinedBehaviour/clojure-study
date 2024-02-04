(ns writing-macros.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defmacro infix
  "Use this macro when you pine for the notation of your childhood"
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))

;; Same as infix.
(defmacro infix-2 [[operand1 op operand2]]
  (list op operand1 operand2))

(infix (1 + 1))
;; 2

(macroexpand '(infix (1 + 1)))
;; (+ 1 1)

(defmacro my-print [expression]
  (list 'let ['result expression]
        (list 'println 'result)
        'result))

(+ 1 2)
;; 3

(quote (+ 1 2))
;; (+ 1 2)

+
;; #object [clojure.core$_PLUS_ 0x2552a8a5 "clojure.core$_PLUS_@2552a8a5"]

(quote +)
;; +

'(+ 1 2)
;; (+ 1 2)

(defmacro when
  [test & body]
  (list 'if test (cons 'do body)))

(defmacro unless
  [test & body]
  (list 'if ('not test) (cons 'do body)))

`(+ 1 ~(inc 1))
;; (clojure.core/+ 1 2)

`(+ 1 (inc 1))
;; (clojure.core/+ 1 (clojure.core/inc 1))

(list '+ 1 (inc 1))
;; (+ 1 2)

(defmacro code-critic
  "Phrases are courtesy Hermes Conrad from Futurama"
  [bad good]
  (list 'do
        (list 'println
              "Great squid of Madrid, this is bad code:"
              (list 'quote bad))
        (list 'println
              "Sweet gorilla of Manila, this is good code:"
              (list 'quote good))))

(code-critic (1 + 1) (+ 1 1))
;; => Great squid of Madrid, this is bad code: (1 + 1)
;; => Sweet gorilla of Manila, this is good code: (+ 1 1)
;; nil

(defmacro code-critic-2
  [bad good]
  `(do (println "Great squid of Madrid, this is bad code:"
                (quote ~bad))
       (println "Sweet gorilla of Manila, this is good code:"
                (quote ~good))))

(defn criticize-code [criticism code]
  `(println ~criticism (quote ~code)))

(defmacro code-criti-3 [bad good]
  `(do ~(criticize-code "Cursed bacteria of Liberia, this is bad code:" bad)
       ~(criticize-code "Sweet sacred boa of Western and Eastern Samoa, this is good code:" good)))

(defmacro code-critic-3 [bad good]
  `(do ~@(map #(apply criticize-code %) [["Great squid of Madrid, this is bad code:" bad]
                                         ["Sweet gorilla of Manila, this is good code:" good]])))

;; Variable capture example

(def message "Good job!")
(defmacro with-mischief [& stuff-to-do]
  (concat (list 'let ['message "Oh, big deal!"])
          stuff-to-do))

(def order-details
  {:name "Mitchard Blimmons"
   :email "mitchard.blimmonsgmail.com"})

(def order-details-validations
  {:name ["Please enter a name" not-empty]
   :email ["Please enter an email address" not-empty
           "Your email address doesn't look like an email address" #(or (empty? %) (re-seq #"@" %))]})

(defn error-messages-for
  "Return a seq of error messages"
  [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))

(defn validate
  "Returns a map with a vector of errors for each key"
  [to-validate validations]
  (reduce (fn [errors validation]
            (let [[fieldname validation-check-groups] validation
                  value (get to-validate fieldname)
                  error-messages (error-messages-for value validation-check-groups)]
              (if (empty? error-messages)
                errors
                (assoc errors fieldname error-messages))))
          {}
          validations))

(validate order-details order-details-validations)