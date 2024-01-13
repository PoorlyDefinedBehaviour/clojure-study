;; ### Single agent ###

;; Agent processes tasks one at a time.
(def sum (agent 0))

(def numbers [0 9 3 4 5 5 4 44 4 2 5 6 7 775])

(doseq [x numbers]
  (send sum + x))

;; Wait until all sent actions are done.
(await sum)

(println @sum)

;; ### Multiple agents ###

(def sums (map agent (repeat 10 0)))

(def numbers-2 (range 1000000))

(doseq [[x agent] (map vector numbers-2 (cycle sums))]
  (send agent + x))

;; Wait for at most 10 seconds.
(apply await-for 10000 sums)

;; Sum the answers from the agents.
(println (apply + (map deref sums)))

;; ### Agents consume from task queue ###

(def sums (map agent (repeat 10 0)))

(def numbers (agent (range 1000000)))

(defn dequeue-and-add [sum-agent]
  (letfn [(add [current-sum x]
          (let [new-sum (+ current-sum x)]
            (send numbers dequeue)
            new-sum))
          (dequeue [xs]
                   (when (not (empty? xs))
                     (send sum-agent add (first xs))
                     (rest xs)))]
    (send numbers dequeue)))

;; Start 10 agents working.
(doseq [sum-agent sums]
  (dequeue-and-add sum-agent))

;; Wait for all the numbers to be cleared from the queue.
(loop []
  (when (seq @numbers)
    (Thread/sleep 1000)
    (recur)))

;; Sum up the answers from the agents.
(println (apply + (map deref sums)))