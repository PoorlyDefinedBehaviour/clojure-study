(require '[clojure.string])

(def foo 10)
;; -> #'user/foo

(var foo)
;; #'user/foo --- var returns the var bound to the symbol user/foo

(#'foo)
;; #'user/foo --- same as (var foo)

(defn triple [number]
  (* 3 number))
;; #'user/triple

(triple 10)
;; 30

;; using (let [bindings*] exprs*)
(defn square-corners [bottom left size]
  (let [top (+ bottom size)
        right (+ left size)]
    [[bottom left] [top left] [top right] [bottom right]]))

;; --- destructuring ---

;; without destructuring
(defn greet-author-1 [author]
  (println "Hello,") (:first-name author))

;; with destructuring -- same as greet-author-1
(defn greet-author 2 [{first-name :first-name}]
  (println "Hello," first-name))

;; vector destructuring

(let [[x y] [1 2 3]] (+ x   y))
;; 3 

(let [[x y :as coords] [1 2 3 4 5 6]]
  (println "coords=" coords))

(defn ellipsize [words]
  (let [[w1 w2 w3] (clojure.string/split words #"\s+")]
    (clojure.string/join " " [w1 w2 w3 "..."])))

(resolve 'foo)
;; #'user/foo

;; creating a namespace
(in-ns 'myapp)

(clojure.core/use 'clojure.core)

;; importing classes InputStream and File form the java.io package into the current namespace.
(import '(java.io InputStream File))

(ns examples.exploring
  (:require [clojure.string :as str])
  (:import (java.io File)))