;; ## REPL

;; In the user namespace.
(def discount-rate 0.15)

;; Creating a namespace. (ns creates the namespace and makes it the current namespace)
(ns pricing)

;; In the pricing namespace.
(def discount-rate 0.15)

(defn discount-price [book]
  (* (- 1.0 discount-rate) (:price book)))

;; Change to the user namespace.
(ns user)

;; Use the function in the pricing namespace from the user namespace.
(pricing/discount-price {:title "Title" :price 10})

;; ## .clj file

(require 'clojure.data)

(def literature ["Emma" "Oliver Twist" "Possession"])

(def horror ["It" "Carrie" "Possession"])

(clojure.data/diff literature horror)
;; [["Emma" "Oliver Twist"] ["It" "Carrie"] [nil nil "Possession"]]


