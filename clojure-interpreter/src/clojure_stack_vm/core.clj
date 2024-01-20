(ns clojure-stack-vm.core
  (:gen-class)
  (:require [clojure.core.match :refer [match]]))

(defn get-binding-value [env binding-name]
  (get (last env) (keyword binding-name)))

(defn push [env stack sym]
  (cond
    (int? sym) (conj (last stack) sym)
    (symbol? sym) (conj stack (get-binding-value env sym))
    :else (throw (Exception. (str "invalid argument to push:" sym)))))

(defn invoke [env stack op arity]
  (let [result (reduce (fn [state _]
                         (let [arg (last (:stack state))
                               args (:args state)
                               stack (pop (:stack state))]
                           {:args (conj args arg)
                            :stack stack})) {:args [] :stack stack} (range 0 arity))
        args (reverse (:args result))
        stack (:stack result)]
    (push env stack (apply (resolve op) args))))

(defn evaluate [env exprs]

  (letfn [(go [env stack exprs]
              (println "evaluate: env=" env " stack=" stack)
              (if (empty? exprs)
                (last stack)
                (let [expr (first exprs)
                      exprs (rest exprs)]
                  (match [expr]
                    [(['invoke> op arity] :seq)] (go env (invoke env stack op arity) exprs)
                    [(['<pop>] :seq)] "pop"
                    [(['if> & ([then-exprs 'else> else-exprs] :seq)] :seq)] (str "if then-exprs" then-exprs " else exprs" else-exprs)
                    [var] (go env (push env stack var) exprs)))))]
    (go env [] exprs)))

(defmacro defstackfn [fn-name args & body]
  (let [bindings# (into {} (map (fn [arg] [(keyword arg) arg]) args))]
    `(defn ~fn-name ~args
       (println "runtime: bindings" ~bindings#)
       (evaluate [~bindings#] '~body))))

#_{:clj-kondo/ignore [:unresolved-symbol]}
(defstackfn f [!a !b !c]
  !a
  !b
  (invoke> + 2))

;; (let [x '(if>
;;           !v1
;;           !v2
;;           (invoke> - 2)
;;           else>
;;           "false!!" ; false
;;           (invoke> println 1) ; nil
;;           <pop> ;stack empty
;;           !v1 ; 3
;;           !v2 ; 3 8
;;           (invoke> * 2))]
;;   (println  (match [x]
;;               [(['if> &
;;                  ([then-exprs & _] :seq)] :seq)] (str then-exprs)
;;               ;; [([1] :seq)] :a0
;;               ;; [([_ 2 & ([a & b] :seq)] :seq)] [:a1 a b])
;;               )))