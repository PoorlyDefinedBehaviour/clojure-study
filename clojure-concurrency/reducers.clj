(require '[clojure.core.reducers :as r])

(def numbers (vec (range 1000000)))

(r/fold + numbers)

(r/fold + (r/filter even? numbers))