(ns alts
  (:require [clojure.core.async :as a :refer [<!! >!! <! >!]]))

(let [c1 (a/chan)
      c2 (a/chan)]
  (a/thread (while true
              ;; alts!! is the same as select. It blocks the execution thread.
              (let [[v ch] (a/alts!! [c1 c2])]
                (println "Read" v "from" ch))))
  (>!! c1 "hi")
  (>!! c2 "there"))

(let [c1 (a/chan)
      c2 (a/chan)]
  (a/go (while true
          ;; alts! do not block the execution thread.
          (let [[v ch] (a/alts! [c1 c2])]
            (println "Read" v "from" ch))))
  (a/go (>! c1 "hi"))
  (a/go (>! c2 "there")))

(let [n 1000
      cs (repeatedly n a/chan)
      begin (System/currentTimeMillis)]
  (doseq [c cs] (a/go (>! c "hi")))
  (dotimes [_ n]
    (let [[v _] (a/alts!! cs)]
      (assert (= "hi" v))))
  (println "Read" n "msgs in" (- (System/currentTimeMillis) begin) "ms"))

(let [t (a/timeout 100)
      begin (System/currentTimeMillis)]
  (<!! t)
  (println "Waited" (- (System/currentTimeMillis) begin)))

(let [c (a/chan)
      begin (System/currentTimeMillis)]
  (a/alts!! [c (a/timeout 100)])
  (println "Gave up after" (- (System/currentTimeMillis) begin)))