(ns threads
  (:require [clojure.core.async :as a :refer [<!! >!!]]))

;; >!! is the blocking put.
(let [c (a/chan 10)]
  (>!! c "hello")
  (assert (= "hello" (<!! c)))
  (a/close! c))

(let [c (a/chan)]
  ;; Execute the form in another thread. >!! blocks.
  (a/thread (>!! c "hello"))
  ;; Read the value in the current thread. <!! blocks.
  (assert (= "hello" (<!! c)))
  (a/close! c))