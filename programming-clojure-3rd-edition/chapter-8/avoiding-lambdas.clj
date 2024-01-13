;; a macro could be better because it avoids the overhead of calling a function.
(defn bench [f]
  (let [start (System/nanoTime)
        result (f)]
    {:result result :elapsed (- (System/nanoTime start))}))