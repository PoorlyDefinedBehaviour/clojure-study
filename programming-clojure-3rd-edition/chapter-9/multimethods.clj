;; PROBLEM: need to modify my-print to print each new type.
(defn my-print [ob]
  (cond
    (nil? ob) (.write *out* "nil")
    (string? ob) (.write *out* ob)))


(defn my-println [ob]
  (my-print ob)
  (.write *out* "\n"))

(my-println "hello")
;; hello

(defmulti my-print-2 class)

(defmethod my-print-2 :default [s]
  (.write *out* "#<")
  (.write *out* (.toString s))
  (.write *out* ">"))

(defmethod my-print-2 String [s]
  (.write *out* s))

(defmethod my-print-2 nil [_]
  (.write *out* "nil"))

(defmethod my-print-2 Number [n]
  (.write *out* (.toString n)))

(defn my-println-2 [ob]
  (my-print-2 ob)
  (.write *out* "\n"))