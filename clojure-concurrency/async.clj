(require '[clojure.core.async :as async :refer [go]])

(def number-chan (async/chan 100))

(def sum (atom 0))

(dotimes [_ 100]
  (go
    (loop []
      (let [number (async/<! number-chan)]
        (swap! sum + number))
      (recur))))

(go
  (doseq [x (range 100000)]
    (async/>! number-chan x)))