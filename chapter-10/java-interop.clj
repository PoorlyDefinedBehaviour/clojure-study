(defn say-hi []
  (println "Hello from thread" (.getName (Thread/currentThread))))

(dotimes [_ 3]
  (.start (Thread. say-hi)))

(java.util.Collections/binarySearch [1 13 42 1000] 42)
;; 2

;; ### Implementing Java interfaces

(import [java.io File FilenameFilter])

(defn suffix-filter [suffix]
  (reify FilenameFilter
    (accept [_this _dir name]
      (.endsWith name suffix))))

(defn list-files [dir suffix]
  (seq (.list (File. dir) (suffix-filter suffix))))

(list-files "." ".clj")
;; ("java-interop.clj")

(defrecord Counter [n]
  Runnable
  (run [_this] (println (range n))))

(def c (->Counter 5))

(.start (Thread. c))

;; from clojure.core
(defn spit
  [f content & options]
  (with-open [^java.io.Writer w (apply jio/writer f options)]
    (.write w (str content))))

(try
  (throw (Exception. "something failed"))
  (finally
    (println "we get to clean up")))
;; we get to clean up
;; Execution error at user/eval169 (REPL:1) .
;; something failed

(defn describe-class-with-type-hint [^Class c]
  {:name (.getName c)
   :final (java.lang.reflect.Modifier/isFinal (.getModifiers c))})

(defn wants-a-string [^String s] (println s))
