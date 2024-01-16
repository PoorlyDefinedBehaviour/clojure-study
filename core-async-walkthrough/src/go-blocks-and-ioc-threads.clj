#_{:clj-kondo/ignore [:namespace-name-mismatch]}
(ns go-blocks-and-ioc-threads
  (:require [clojure.core.async :as a :refer [<!! <! >!]]))

(let [c (a/chan)]
  ;; >! does not block the execution thread.
  (a/go (>! c "hello"))
  ;; !< does not block the execution thread.
  (assert (= "hello" (<!! (a/go (<! c)))))
  (a/close! c))