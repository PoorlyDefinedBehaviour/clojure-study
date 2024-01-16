(def rolodex {"Eric" "504-543-0093"
              "Jane" "303-221-3333"
              "Joe" "222-323-2222"})

(get rolodex "Jane")
;; "303-221-3333"

;; Replace existing value.
(assoc [:a :b :c] 2 :x)
;; [:a :b :x]

;; Assoc one past the end.
(assoc [:a :b :c] 3 :x)
;; [:a :b :c :x]

(assoc [:a :b :c] 5 :x)
;; Execution error (IndexOutOfBoundsException)

(assoc {} :greeting "Hello, world!")
;; {:greeting "Hello, world!"}

(def meals {:breakfast []})

(update meals :breakfast conj :eggs)
;; {:breakfast [:eggs]}

(def visits (atom {}))

(defn record-visit! [ip]
  (swap! visits update ip (fnil inc 0)))

(record-visit! "2.2.2.2")
(record-visit! "2.2.2.2")
(record-visit! "2.2.2.2")
(record-visit! "1.1.1.1")

(println @visits)
;; {2.2.2.2 3, 1.1.1.1 1}

(def visits-2 (atom {"1.1.1.1" 102
                     "2.2.2.2" 80
                     "127.0.0.1" 1008}))

(dissoc @visits-2 "127.0.0.1")
;; "2.2.2.2" 3, "1.1.1.1" 1}