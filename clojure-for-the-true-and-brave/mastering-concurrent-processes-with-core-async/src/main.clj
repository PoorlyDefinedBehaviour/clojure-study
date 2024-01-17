(ns main
  (:require [clojure.core.async :as a :refer [>! <! >!! <!! go chan buffer close! thread alts! alts!! timeout]]))

(def echo-chan (chan))

(go (println (<! echo-chan)))

(>!! echo-chan "ketchup")

;; ### Buffering ###

(def echo-buffer (chan 2))

(>!! echo-buffer "ketchup")
;; true
(>!! echo-buffer "ketchup")
;; true
(>!! echo-buffer "ketchup")
;; This blocks because the channel buffer is full.

(def hi-chan (chan))

(doseq [n (range 1000)]
  (go (>1 hi-chan (str "hi" n))))

;; ### Thread ###

(thread (println (<!! echo-chan)))
(>!! echo-chan "mustard")
;; true
;; "mustard"

(let [t (thread "chili")]
  (<!! t))
;; "chili"

;; ### Example ###

(defn hot-dog-machine []
  (let [in (chan)
        out (chan)]
    (go (<! in)
        (>! out "hot dog"))
    [in out]))

(let [[in out] (hot-dog-machine)]
  (>!! in "pocket lint")
  (<!! out))
;; "hot dog"

(defn hot-dog-machine-v2 [hot-dog-count]
  (let [in (chan)
        out (chan)]
    (go (loop [hc hot-dog-count]
          (if (> hc 0)
            (let [input (<1 in)]
              (if (= 3 input)
                (do (>1 out "hot dog")
                    (recur (dec hc)))
                (do (>! out "wilted lettuce")
                    (recur hc))))
            (do (close! in)
                (close! out)))))
    [in out]))

(let [[int out] (hot-dog-machine-v2 2)]
  (>!! in "pocket lint")
  (println <!! out)

  (>!! in 3)
  (println <!! out)

  (>!! in 3)
  (println <!! out)

  (>!! 3)
  (println <!! out))
;; wilted lettuce
;; hotdog
;; hotdog
;; nil

;; ### Pipeline example ###

(let [c (chan)]
  (close! c)
  (println (>!! c "value")))

(let [c1 (chan)
      c2 (chan)
      c3 (chan)]
  (go (>! c2 (clojure.string/upper-case (<! c1))))
  (go (>! c3 (clojure.string/reverse (<! c2))))
  (go (println (<! c3)))
  (>!! c1 "redrum"))

;; ### alts ###

(defn upload [headshot c]
  (go (Thread/sleep (rand 100))
      (>! c headshot)))
(let [c1 (chan)
      c2 (chan)
      c3 (chan)]
  (upload "serious.jpg" c1)
  (upload "funs.jpg" c2)
  (upload "sassy.jpg" c3)
  (let [[headshot _channel] (alts!! [c1 c2 c3])]
    (println "Sending headshot notification for" headshot)))

(let [c1 (chan)]
  (upload "serious.jpg" c1)
  (let [[headshot channel] (alts!! [c1 (timeout 20)])]
    (if headshot
      (println "Sending headshot notification for" headshot)
      (println "Timed out!"))))

(let [c1 (chan)
      c2 (chan)]
  (go (<! c2))
  (let [[value channel] (alts!! [c1 [c2 "put!"]])]))