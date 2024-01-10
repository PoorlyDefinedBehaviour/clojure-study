(require '[clojure.java.io :refer [reader]])

(take 2 (line-seq (reader "./seq-ing-a-stream.clj")))
;; ("(require '[clojure.java.io :refer [reader]])" "")

(with-open [rdr (reader "./seq-ing-a-stream.clj")]
  (count (filter #(re-find #"\S" %) (line-seq rdr))))
;; 5

(use '[clojure.java.io :only (reader)])
(use '[clojure.string :only (blank?)])

(defn non-blank? [line] (not (blank? line)))

(defn non-svn? [file] (not (.contains (.toString file) ".svn")))

(defn clojure-source? [file] (.endsWith (.toString file) ".clj"))

(defn clojure-loc [base-file]
  (reduce
   +
   (for [file (file-seq base-file) :when (and (clojure-source? file) (non-svn? file))]
     (with-open [rdr (reader file)]
       (count (filter non-blank? (line-seq rdr)))))))
