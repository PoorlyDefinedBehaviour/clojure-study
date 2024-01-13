;; Every aggregate data structure in Clojure can be viewed as a sequence.

;; Getting the first element.
;; (first a-sequence)

;; Getting everything after the first element.
;; (rest a-sequence)

;; Construct a new sequence by adding an item to the front of an existing sequence.
;; cons (element a-sequence)

;; Transforming a seq-able collection into a seq.
;; (seq coll)

;; (next a-sequence) is the same as (seq (rest a-sequence))

;; Treating lists as seqs.
(first '(1 2 3))
;; 1

(rest '(1 2 3))
;; (2 3)

(cons 0 '(1 2 3))
;; (0 1 2 3)

;; Treating vectors as seqs.
(first [1 2 3])
;; 1

(rest [1 2 3])
;; (2 3)

(cons 0 [1 2 3])
;; (0 1 2 3)

;; Treating maps as seqs.
(first {:first-name "Aaron" :last-name "Bedra"})
;; [:first-name "Aaron"]

(rest {:first-name "Aaron" :last-name "Bedra"})
;; ([:last-name "Bedra"])

(cons [:middle-name "James"] {:first-name "Aaron" :last-name "Bedra"})
;; ([:middle-name "James"] [:first-name "Aaron"] [:last-name "Bedra"])

;; Treating sets as seqs.
(first #{:the :quick :brown :fox})
;; :fox

(rest #{:the :quick :brown :fox})
;; (:the :quick :brown)

(cons :jumped #{:the :quick :brown :fox})
;; (:jumped :fox :the :quick :brown)

;; Maps and sets have a stable traversal order but the order depends on implementation details.
;; Do not depend on it.
;; Use sorted-set if you need a set that's sorted by the natural sort order of the values.
;; Use sorted-map if you need a map that's sorted by the natural sort order of the values.

(sorted-set :the :quick :brown :fox)
;; #{:brown :fox :quick :the}

(sorted-map :c 1 :b 2 :a 3)
;; Ordered by key.
;; {:a 3, :b 2, :c 1}

;; For lists, conj adds elements to the front.
(conj '(1 2 3) :a)
;; (:a 1 2 3)

(into '(1 2 3) '(:a :b :c))
;; (:c :b :a 1 2 3)

;; For vectors, conj adds elements to the back.
(conj [1 2 3] :a)
;; [1 2 3 :a]

(into [1 2 3] [:a :b :c])
;; [1 2 3 :a :b :c]