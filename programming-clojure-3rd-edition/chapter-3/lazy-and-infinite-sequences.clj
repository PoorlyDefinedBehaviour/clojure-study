(def x (for [i (range 1 3)] (do (println i) i)))

;; --- Seq-ing Java collections ---.

;; String.getBytes returns a byte array.
(first (.getBytes "hello"))
;; 104

(rest (.getBytes "hello"))
;; (101 108 108 111)

(first "Hello")
;; \H

(rest "Hello")
;; (\e \l \l \o)

(cons \H "ello")
;; (\H \e \l \l \o)

(reverse "hello")
;; (\o \l \l \e \H)

(apply str (reverse "hello"))
;; "olleh"

;; --- Seq-ing regular expressions ---.

(let [m (re-matcher #"\w+" "the quick brown fox")]
  (loop [match (re-find m)]
    (when match
      (println match)
      (recur (re-find m)))))
;; the
;; quick
;; brown
;; fox
;; nil

(re-seq #"\w+" "the quick brown fox")
;; ("the" "quick" "brown" "fox")

;; --- Seq-ing the file system ---.

(import 'java.io.File)

(.listFiles (File. "."))
;; #object["[Ljava.io.File;" 0x425d5d46 "[Ljava.io.File;@425d5d46"]

(seq (.listFiles (File. ".")))
;; (#object[java.io.File 0x76954a33 "./lazy-and-infinite-sequences.clj"] #object[java.io.File 0x24a298a6 "./everything-is-a-sequence.clj"] #object[java.io.File 0x982bb90 "./using-the-sequence-library.clj"])

;; no need to call seq here, map would call it for us.
(map #(.getName %) (seq (.listFiles (File. "."))))
;; ("lazy-and-infinite-sequences.clj" "everything-is-a-sequence.clj" "using-the-sequence-library.clj")

(count (file-seq (File. ".")))
;; 4

(defn minutes-to-millis [mins] (* mins 1000 60))

(defn recently-modified? [file]
  (> (.lastModified file)
     (- (System/currentTimeMillis) (minutes-to-millis 30))))

(map #(.getName %) (filter recently-modified? (file-seq (File. "."))))
;; ("." "lazy-and-infinite-sequences.clj")
