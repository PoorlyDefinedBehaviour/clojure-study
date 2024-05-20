(defn print-greeting [preferred-customer]
  (if preferred-customer
    (println "Welcome back to Blotts Books!")))

(print-greeting true)
;; => Welcome back to Blotts Books!
;; nil

(print-greeting false)
;; nil

(defn shipping-charge [preferred-customer order-amount]
  (if preferred-customer
    0.0
    (* order-amount 0.10)))

(shipping-charge true 10)
;; 0.0

(shipping-charge false 10)
;; 1.0

(= 1 1)
;; true

(= 2 (+ 1 1))
;; true

(not= 1 2)
;; true

(number? 1)
;; true

(string? "hello world")
;; true

(keyword? :hello)
;; true

(do
  (println "1")
  (println "2")
  (println "3")
  4)
;; 4

(when true
  (println "here")
  (println "and here"))

(defn shipping-charge [preferred-customer order-amount]
  (cond
    preferred-customer 0.0
    (< order-amount 50.0) 5.0
    (< order-amount 100.0) 10.0
    :else (* 0.1 order-amount)))

(defn customer-greeting [status]
  (case status
    :gold "Welcome gold"
    :preferred "Welcome preferred"))