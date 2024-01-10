;; Creating a Java object: (new classname)
(new java.util.Random)

(java.util.Random.) ;; same as (new java.util.Random)

(def rnd (new java.util.Random))

;; Calling methods.
(. rnd nextInt) ;; calling rnd.nextInt()

;; Accessing instance field.
(def p (java.awt.Point. 10 20))
(. p x) 
;; 10

;; Calling static method.
(. System lineSeparator)
;; "\n"

;; Accessing static field.
(. Math PI)
;; 3.14...

;; --- Using the more concise syntax ---

(.nextInt rnd 10) ;; same as (. rnd nextInt 10)

(.x p)
;; 10

(System/lineSeparator)
;; "\n"

Math/PI ;; Math comes from the java.lang package.
;; 3.14...