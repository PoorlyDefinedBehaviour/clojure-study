;; Clojure strings reuse the Java String implementation.

"This is a\nmultiline string"
;; "This is a\nmultiline string"

"This is also
a multiline string"
;; "This is a\nmultiline string"

(str 1 2 nil 3)
;; "123"

;; Clojure characters are also Java characters. Their literal syntax is \{letter}.


(str \h \e \y \space \y \o \u)
;; "hey you"