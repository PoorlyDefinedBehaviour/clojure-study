;; create-struct is deprecated in favor of records.

(def person (create-struct :first-name :last-name))

;; defstruct is already part of Clojure.
(defmacro defstruct
  [name & keys]
  `def ~name (create-struct ~@keys))

;; decleare is an alternative to manually writing forward declarations:
;; (def a)
;; (def b)
;; (def c)
;; 
;; declare is part of Clojure.
(defmacro declare [& names]
  `(do ~@(map #(list 'def %) names))
  )