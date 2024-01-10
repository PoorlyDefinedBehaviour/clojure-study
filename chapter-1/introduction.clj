(defn blank? [str]
  (every? #(Character/isWhitespace %) str))

(defrecord Person [first-name last-name])

(def foo (->Person "Aaron" "Bedra"))

(:firstname foo)
;; Aaron

(defn hello-world [username]
  (println (format "Hello, %s" username)))

(defn cond-example [x]
  (cond (= x 10) "equal"
        (> x 10) "more"))

(defn find-requiem-composer [compositions]
  ;; list comprehension
  (for [c compositions :when (= (:name c) "REquirem")] (:composer c)))

(def accounts (ref #{}))
(defrecord Account [id balance])

(dosync
 (alter accounts conj (->Account "CLJ" 1000.00)))

(System/getProperties)
;; {java.runtime.name=Java(TM) SE Runtime Environment}

(.. "hello" getClass getProtectionDomain)

(.start (new Thread (fn [] (println "Hello" (Thread/currentThread)))))