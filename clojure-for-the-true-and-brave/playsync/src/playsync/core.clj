(ns playsync.core
  (:gen-class)
  (:require [clojure.core.async :as a :refer [>! <! >!! <!! go chan buffer close! thread alts! alts!! timeout]]
            [clojure.string]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(def echo-chan (chan))
(go println (<! echo-chan))
(>!! echo-chan "ketchup")
;; true
;; ketchup

(def echo-buffer (chan 2))
(>!! echo-buffer "ketchup")
;; true

(>!! echo-buffer "ketchup")
;; true

(>!! echo-buffer "ketchup")
;; Blocks because the channel buffer is full.

(def hi-chan (chan))
(doseq [n (range 1000)]
  (go (>! hi-chan (str "hi " n))))

(thread (println (<!! echo-chan)))
(>!! echo-chan "mustard")

(let [t (thread "chili")]
  (<!! t))
;; "chili"

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
            (let [input (<! in)]
              (if (= 3 input)
                (do (>! out "hot dog")
                    (recur (dec hc)))
                (do (>! out "wilted lettuce")
                    (recur hc))))
            (do (close! in)
                (close! out)))))
    [in out]))

(let [[in out] (hot-dog-machine-v2 2)]
  (>!! in "pocket lint")
  (println (<!! out))

  (>!! in 3)
  (println (<!! out))

  (>!! in 3)
  (println (<!! out))

  (>!! in 3)
  (<!! out))

(let [c1 (chan)
      c2 (chan)
      c3 (chan)]
  (go (>! c2 (clojure.string/upper-case (<! c1))))
  (go (>! c3 (clojure.string/reverse (<! c2))))
  (go (println (<! c3)))
  (>!! c1 "redrum"))

(defn upload [headshot c]
  (go (Thread/sleep (rand 100)
                    (>! c headshot))))

(let [c1 (chan)
      c2 (chan)
      c3 (chan)]
  (upload "serious.jpg" c1)
  (upload "fun.jpg" c2)
  (upload "sasy.jpg" c3)
  (let [[headshot _channel] (alts!! [c1 c2 c3])]
    (println "Sending headshot notification for" headshot)))

(let [c1 (chan)]
  (upload "serious.jpg" c1)
  (let [[headshot _channel] (alts!! [c1 (timeout 20)])]
    (if headshot
      (println "Sending headshot notification for" headshot)
      (println "Timed out!"))))

(let [c1 (chan)
      c2 (chan)]
  (go (<! c2))
  (let [[value channel] (alts!! [c1 [c2 "put!"]])]
    (println value)
    (= channel c2)))