(def authors (java.io.File. "authors.txt"))

(if (.exists authors)
  (println "authors.txt exists")
  (println "authors.txt does not exist"))

(if (.canRead authors)
  (println "the file can be read")
  (println "the file cannot be read"))

(def rect (java.awt.Rectangle. 0 0 10 20))
(println "width:" (.-width rect))
(println "height:" (.-height rect))

(import java.io.File)

(def authors (File. "authors.txt"))

;; Calling a static method.
(def temp-authors-file (File/createTempFile "authors_list" ".txt"))