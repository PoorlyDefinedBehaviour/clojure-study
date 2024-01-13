#_{:clj-kondo/ignore [:namespace-name-mismatch]}
(ns chapter-7.gulp
  (:import (java.io File FileInputStream InputStream OutputStream InputStreamReader BufferedReader FileOutputStream OutputStreamWriter BufferedWriter)))

(defn make-reader [src]
  (-> src
      FileInputStream.
      InputStreamReader.
      BufferedReader))

(defn gulp [src]
  (let [sb (StringBuilder.)]
    (with-open [reader (make-reader src)]
      (loop [c (.read reader)]
        (if (neg? c)
          (str sb)
          (do
            (.append sb (char c))
            (recur (.read reader))))))))




(defn make-writer [dst]
  (->
   dst
   FileOutputStream.
   OutputStreamWriter.
   BufferedWriter.))


(defn expectorate [dst content]
  (with-open [writer (make-writer dst)]
    (.write writer (str content))))

;; Define a Java interface.
(definterface IOFactory
  (^java.io.BufferReader make-reader [this])
  (^java.io.BufferWriter make-writer1 [this]))

(defprotocol IOFactory
  "A protocol for thins that can be read from and written to."
  (make-reader [this] "Creates a BufferedReader")
  (make-writer [this] "Creates a BufferedWriter"))

(extend InputStream
  IOFactory
  {:make-reader (fn [src] (-> src
                              InputStreamReader.
                              BufferedReader.))
   :make-writer (fn [_dst]  (throw (IllegalArgumentException. "Can't open as an InputStream.")))})

(extend OutputStream
  IOFactory
  {:make-reader (fn [_src] (throw (IllegalArgumentException. "Can't open as OutputStream")))
   :make-writer (fn [dst] (->
                           dst
                           OutputStreamWriter.
                           BufferedWriter.))})

(extend-type File
  IOFactory
  (make-reader [src]
    (make-reader (FileInputStream. src)))
  (make-writer [dst]
    (make-writer (FileOutputStream. dst))))