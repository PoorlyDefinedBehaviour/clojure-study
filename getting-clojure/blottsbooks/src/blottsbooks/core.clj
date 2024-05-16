(ns blottsbooks.core
  (:gen-class))

(defn say-welcome [what]
  (println "Welcome to" what "!"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (say-welcome "Blotts Books"))
