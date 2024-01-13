(with-out-str (print "hello, ") (print "world"))
;; "hello, world"

;; How with-out-str is defined:
(defmacro with-out-str
  [& body]
  `(let [s# (new java.io.StringWriter)]
     (binding [*out* s#]
       ~@body
       (str s#))))

(assert (= 1 1))
;; nil

(assert (= 1 2))
;; Assert failed: (= 1 2)