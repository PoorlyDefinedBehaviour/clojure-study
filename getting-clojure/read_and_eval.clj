(def s
  "(defn print-greeting [preferred-customer]
     (if preferred-customer (println \"Welcome back\")))")

(read-string s)
;; (defn print-greeting [preferred-customer] (if preferred-customer (println "Welcome back")))

(def a-data-structure '(+ 2 2))
(eval a-data-structure)
;; 4

(eval (read-string s))
(print-greeting true)
;; => Welcome back
;; nil

(require '[clojure.java.io :as io])

(defn read-source [path]
  (with-open [r (java.io.PushbackReader. (io/reader path))]
    (loop [result []]
      (let [expr (read r false :eof)]
        (if (= expr :eof)
          result
          (recur (conj result expr)))))))

(defn russ-repl []
  (println (eval (read)))
  (recur))

(def eval-symbol)
(def eval-vector)
(def eval-list)

(defn reval [expr]
  (cond
    (string? expr) expr
    (keyword? expr) expr
    (number? expr) expr
    (symbol? expr) (eval-symbol expr)
    (vector? expr) (eval-vector expr)
    (list? expr) (eval-list expr)
    :else :completely-confused))


(defn eval-symbol [symbol]
  (.get (ns-resolve *ns* symbol)))

(defn eval-vector [xs]
  (vec (map reval xs)))

(defn eval-list [xs]
  (let [evaluated-items (map reval xs)
        f (first evaluated-items)
        args (rest evaluated-items)]
    (apply f args)))