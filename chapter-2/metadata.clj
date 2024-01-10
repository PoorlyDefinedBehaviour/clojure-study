;; Adding metadata to a function.
;; Documenting that shout expects and returns a String.
(defn ^{:tag String} shout [^{:tag String} s] (clojure.string/upper-case s))

;; Using the short-form.
(defn ^String shout-2 [^String s] (clojure.string/upper-case s))

;; Aame thing again but in a different form.
(defn shout-3
  ([s] (clojure.string/upper-case s))
  {:tag String})