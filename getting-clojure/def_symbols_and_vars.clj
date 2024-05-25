(def title "Emma")

(def PI 3.14)

(defn book-description [book]
  (str (:title book) " Written by " (:author book)))

;; Get the symbol "title" instead of the value pointed by it.
'title
;; title

(str 'author)
;; "author"

;; Get the var for the symbol "title"
#'title
;; #'user/title

(def the-var #'title)

;; Get the value using the var
(.get the-var)
;; "Emma"

;; Get the symbol using the var
(.sym the-var)
(.-sym the-var)
;; title

(def ^:dynamic debug-enabled false)

(defn debug [msg]
  (if debug-enabled
    (println msg)
    nil))

(debug "hello world")
;; nil

(binding [debug-enabled true]
  (debug "hello world"))
;; => hello world
;; nil