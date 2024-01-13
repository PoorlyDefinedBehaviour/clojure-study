(ns hangman.core
  (:require [clojure.java.io :as jio] [clojure.string :as str]))

(defprotocol Player
  (next-guess [player progress]))

(defrecord ChoicesPlayer [choices]
  Player
  (next-guess [_this _progress]
    (let [guess (first @choices)]
      (swap! choices rest)
      guess)))

(defonce letters (mapv char (range (int \a) (inc (int \z)))))

(defn rand-letter []
  (rand-nth letters))

(def random-player
  (reify Player
    (next-guess [_player _progress] (rand-letter))))

(defn choices-player [choices]
  (->ChoicesPlayer (atom choices)))

(defn shuffle-player []
  (choices-player (shuffle letters)))

(defn alpha-player []
  (choices-player letters))

(defn frequence-player []
  (choices-player (seq "etaoinshrdlcumwfgypbvkjxqz")))

(defn valid-letter? [c]
  (<= (int \a) (int c) (int \z)))


(defonce available-words
  (with-open [r (jio/reader "words.text")]
    (->>
     (line-seq r)
     (filter #(every? valid-letter? %))
     vec)))

(defn rand-word []
  (rand-nth available-words))

(defn take-guess []
  (println)
  (print "Enter a letter: ")
  (flush)
  (let [input (.readLine *in*)
        line (str/trim input)]
    (cond (str/blank? line) (recur)
          (valid-letter? (first line)) (first line)
          :else (do (println "That is not a valid letter!") (recur)))))

(def interactive-player
  (reify Player
    (next-guess [_player _progress] (take-guess))))

(defn new-progress [word]
  (repeat (count word) \_))

(defn update-progress [progress word guess]
  (map #(if (= %1 guess) guess %2) word progress))

(defn complete? [progress word]
  (= progress (seq word)))

(defn report [begin-process guess end-progress]
  (println)
  (println "You guessed:" guess)
  (if (= begin-process end-progress)
    (if (some #{guess} end-progress)
      (println "Sorry, you already guessed:" guess)
      (println "Sorry, the word does not contain:" guess))
    (println "The letter" guess "is in the word!"))
  (println "Progress so far:" (apply str end-progress)))

(defn game
  [word player &
   {:keys [verbose] :or {verbose false}}]
  (when verbose
    (println "You are guessing a word with" (count word) "letters"))
  (loop [progress (new-progress word)
         guesses 1]
    (let [guess (next-guess player progress)
          progress' (update-progress progress word guess)]
      (when verbose (report progress guess progress'))
      (if (complete? progress' word)
        guesses
        (recur progress' (inc guesses))))))

