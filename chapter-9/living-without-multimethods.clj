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



