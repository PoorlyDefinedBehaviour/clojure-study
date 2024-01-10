(defn stack-consuming-fibo [n]
  (cond
    (= n 0) 0
    (= n 1) 1
    :else (+ (stack-consuming-fibo (- n 1))
             (stack-consuming-fibo (- n 2)))))

(stack-consuming-fibo 9)
;; 34

(defn tail-fibo [n]
  (letfn [(fib
            [current next n]
            (if (zero? n)
              current
              (fib next (+ current next) (dec n))))]
    (fib 0N 1N n)))

(tail-fibo 9)
;; 34N

(defn recur-fibo [n]
  (letfn [(fib
            [current next n]
            (if (zero? n)
              current
              (recur next (+ current next) (dec n))))]
    (fib 0N 1N n)))

(tail-fibo 9)
;; 34N

(defn lazy-seq-fibo
  ([]
   (concat [0 1] (lazy-seq-fibo 0N 1N)))
  ([a b]
   (let [n (+ a b)]
     (lazy-seq (cons n (lazy-seq-fibo b n))))))

(take 10 (lazy-seq-fibo))
;; (0 1 1N 2N 3N 5N 8N 13N 21N 34N)

(take 5 (iterate (fn [[a b]] [b (+ a b)]) [0 1]))
;; ([0 1] [1 1] [1 2] [2 3] [3 5])

(defn fibo []
  (map first (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])))

(take 10 (fibo))
;; (0N 1N 1N 2N 3N 5N 8N 13N 21N 34N)