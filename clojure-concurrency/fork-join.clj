(import 'java.util.concurrent.RecursiveTask)
(import 'java.util.concurrent.ForkJoinPool)

(def pool (ForkJoinPool.))

(defn summation [numbers]
  (proxy [RecursiveTask] []
    (if (<= (count numbers) 512)
      (reduce + 0 numbers)
      ((let [half (quot (count numbers) 2)
             f1 (summation (subvec numbers 0 half))
             f2 (summation (subvec numbers half))]
         (.fork f2)
         (+ (.compute f1) (.join f2)))))))

(defn sum [numbers]
  (.invoke pool (summation (vec numbers))))

(def answer (sum (range 1000000)))