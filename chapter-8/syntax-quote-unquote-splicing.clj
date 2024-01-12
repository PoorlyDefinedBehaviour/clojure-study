(defmacro chain
  ([x form] `(. ~x ~form))
  ([x form & more] `(chain (. ~x ~form) ~@more)))


;; ### Creating names in a Macro ###

;; (bench (str "a" "b"))
;; should expand to:
(let [start (System/nanoTime)
      result (str "a" "b")]
  {:result result :elapsed (- (System/nanoTime) start)})

(defmacro bench [form]
  `(let [start# (System/nanoTime)
         result# ~form]
     {:result result# :elapsed (- (System/nanoTime start#))}))

(bench (str "a" "b"))
;; {:result "ab" :elapsed: 61000}

(macroexpand-1 '(bench (str "a" "b")))
;; (clojure.core/let [start__149__auto__ (java.lang.System/nanoTime)
;;                    result__150__auto__ (str "a" "b")]
;;   {:result result__150__auto__, :elapsed (clojure.core/- (java.lang.System/nanoTime start__149__auto__))})