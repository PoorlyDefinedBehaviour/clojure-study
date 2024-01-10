(defn is-small? [number]
  (if (< number 100) "yes")) ;; the else branch if missing, defaults to nil.

(is-small? 50)
;; "yes"

(is-small? 150)
;; nil

(defn is-small-2? [number]
  (if (< number 100) "yes" "no"))

(is-small-2? 50)
;; "yes"

(is-small-2? 150)
;; "no"

(defn is-small-3? [number]
  (if (< number 100)
    "yes"
    (do ;; do evaluates any number of forms and returns the last. Necessary here because if only accepts two forms.
      (println "Saw a big number" number)
      "no")))

(is-small-3? 200)
;; | Saw a big number
;; "no"

(loop [result [] x  5]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))
;; [5 4 3 2 1]

;; We can recur back to the top of a function. (why is this necessary? just call the function)

(defn countdown [result x]
  (if (zero? x)
    result
    ;; recur will call countdown again.
    (recur (conj result x) (dec x))))

(countdown [] 5)
;; [5 4 3 2 1]

(defn countdown-2 [x]
  (into [] (take x (iterate dec x))))

(countdown-2 5)
;; [5 4 3 2 1]