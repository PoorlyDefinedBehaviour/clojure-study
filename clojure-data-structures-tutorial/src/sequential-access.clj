;; Using a vector.
(def todos (atom []))

(defn add-todo! [item]
  (swap! todos conj item))

(add-todo! "Buy kitten")
(add-todo! "Buy cat food")
(add-todo! "Feed kitten")

(doseq [item @todos]
  (println item))
;; Buy kitten
;; Buy cat food
;; Feed kitten


;; Using a list.
(def todos-2 (atom ()))

(defn add-todo-2! [item]
  (swap! todos-2 conj item))

(add-todo-2! "Buy kitten")
(add-todo-2! "Buy cat food")
(add-todo-2! "Feed kitten")

(doseq [item @todos-2]
  (println item))
;; Feed kitten
;; Buy cat food
;; Buy kitten

(def todos-3 (atom (sorted-set)))

(defn add-todo-3! [item]
  (swap! todos-3 conj item))

(add-todo-3! "Buy kitten")
(add-todo-3! "Buy cat food")
(add-todo-3! "Feed kitten")

(doseq [item @todos-3]
  (println item))
;; Feed kitten
;; Buy cat food
;; Buy kitten


(defn priority-order [a b]
  (compare (:priority a) (:priority b)))

(def todos-4 (atom (sorted-set-by priority-order)))

(defn add-todo-4! [item]
  (swap! todos-4 conj item))

(add-todo-4! {:priority 1 :name "Take nap"})
(add-todo-4! {:priority 4 :name "Clean kitchen"})
(add-todo-4! {:priority 2 :name "Eat lunch"})

(doseq [item @todos-4]
  (println item))
;; {:priority 1, :name Take nap}
;; {:priority 2, :name Eat lunch}
;; {:priority 4, :name Clean kitchen}

(def todos-5 (atom #{}))

(defn add-todo-5! [item]
  (swap! todos-5 conj item))

(doseq [letter "ABCDEFGHIJKL"]
  (add-todo-5! (str letter)))

(println (apply str @todos-5))
;; KLGJHECFBAID