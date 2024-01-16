(ns channels
  (:require [clojure.core.async :as a]))

;; Creating an unbuffered channel.
(a/chan)

;; Creating a buffered channel.
(a/chan 10)

;; close! a channel to stop accepting puts.
(let [c a/chan]
  (a/close! c))

;; Use dropping-buffer to drop newest values when the buffer is full:
(a/chan (a/dropping-buffer 10))

;; Use sliding-buffer to drop oldest values when the buffer is full:
(a/chan (a/sliding-buffer 10))