(def ^:dynamic foo 10)

foo
;; 10

(.start (Thread. (fn [] (println foo ; 10
                                 ))))

(binding [foo 42] foo)
;; 42

(defn print-foo [] (println foo))

(let [foo "let foo"] (print-foo))
;; 10

(binding [foo "bound foo"] (print-foo))
;; "bound foo"

(defn ^:dynamic slow-double [n]
  (Thread/sleep 100)
  (* n 2))

(defn calls-slow-double []
  (map slow-double [1 2 1 2 1 2]))

(defn demo-memoize []
  (time
   (dorun
    (binding [slow-double (memoize slow-double)]
      (calls-slow-double)))))