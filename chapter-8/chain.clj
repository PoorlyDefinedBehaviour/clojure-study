;; chain reimplements Clojure's .. macro
(defmacro chain 
  ([x form] (list '. x form))
  ([x form & more] (concat (list 'chain (list '. x form) more))))