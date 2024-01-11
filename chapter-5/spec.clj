(require '[clojure.spec.alpha :as s]
         '[clojure.spec.test.alpha :as stest])


;; without spec
(defn scale-ingreditn [ingredient factor]
  (update ingredient :quantity * factor))

;; example spec
(s/def :my.app/company-name string?)

(s/valid? :my.app/company-name "Acme Moving")
;; true

(s/valid? :my.app/company-name 100)
;; false

(s/def :marble/color #{:red :green :blue})

(s/valid? :marble/color :red)
;; true

(s/valid? :marble/color :pink)
;; false

#_{:clj-kondo/ignore [:namespace-name-mismatch]}
(ns chapter-5.spec :as bowling)

(s/def ::bowling/roll #{0 1 2 3})

(s/valid? ::bowling/roll 5)
;; true

(s/def :bowling/ranged-roll (s/int-in 0 11))

(s/valid? :bowling/ranged-roll 10)
;; true

(s/def :my.app/company-name-2 (s/nilable string?))

(s/valid? :my.app/company-name-2 nil)
;; true

(s/def :my.app/nilable-boolean (s/nilable boolean?))

(s/valid? :my.app/nilable-boolean nil)
;; true

(s/valid? :my.app/nilable-boolean true)
;; true

(s/valid? :my.app/nilable-boolean false)
;; true

(s/valid? :my.app/nilable-boolean "hello world")
;; false

(s/def :my.app/odd-int (s/and int? odd?))

(s/valid? :my.app/odd-int 5)
;; true

(s/valid? :my.app/odd-int 10)
;; false

(s/def :my.app/odd-or-42 (s/or :odd :my.app/odd-int :42 #{42}))

(s/def :my.app/names (s/coll-of string?))

(s/valid? :my.app/names ["Alex" "Stu"])
;; true

(s/valid? :my.app/names #{"Alex" "Stu"})
;; true

(s/valid? :my.app/names '("Alex" "Stu"))
;; true

(s/def :my.app/my-set (s/coll-of int? :kind set? :min-count 2))

(s/def :my.app/scores (s/map-of string? int?))

(s/valid? :my.app/scores {"Stu" 100 "Alex" 200})
;; true

(s/def :my.app/point (s/tuple float? float?))
(s/conform :my.app/point [1.3 2.7])
;; [1.3 2.7]

(s/def ::music/id uuid?)
(s/def ::music/artist string?)
(s/def ::music/title string?)
(s/def ::music/date inst?)
(s/def ::music/release
  (s/keys :req [::music/id]
          :opt [::music/artist
                ::music/title
                ::music/date]))

(s/def ::music/release-unqualified
  (s/keys :req-un [::music/id]
          :opt-un [::music/artist
                   ::music/title
                   ::music/date]))

(s/def ::cat-example (s/cat :s string? :i int?))

(s/valid? ::cat-example ["abc" 100])

(s/def ::alt-example (s/alt :i int? :k keyword?))

(s/valid ::alt-example [100])
;; true

(s/valid ::alt-example [:foo])
;; true

(s/conform ::alt-example [:foo])
;; [:k :foo]

(s/def ::oe (s/cat :odds (s/+ odd?) :even (s? even?)))

(s/conform ::oe [1 3 5 100])
;; {:odds [1 3 5] :even 100}

(s/def ::ods (s/+ odd?))

(s/def ::optional-even (s/? even?))

(s/def ::oe2 (s/cat :odds ::odds :even ::optional-even))

(s/conform ::oe2 [1 3 5 100])
;; {:odds [1 3 5] :even 100}

(s/def ::println-args (s/* any?))

(s/def ::set-args (s/cat :set set? :sets (s/* set?)))

(s/def ::meta map?)

(s/def ::validator ifn?)

(s/def ::atom-args
  (s/cat :x any? :options (s/keys* :opt-un [::meta ::validator])))

;; ### Specifying functions ###

(s/def ::rand-args (s/cat :n (s/? number?)))
(s/def ::rand-args-2 (s/? number?))
(s/def ::rand-ret double?)
(s/def ::rand-fn
  (fn [{:keys [args ret]}]
    (let [n (or (:n args) 1)]
      (cond (zero? n) (zero? ret)
            (pos? n) (and (>= ret 0) (<ret n)
                          (neg? n) (and (<= ret 0) (> ret n)))))))

(s/fdef clojure.core/rand
  :args ::rand-args
  :ret ::rand-ret
  :fn ::rand-fn)

(s/def ::pred
  (s/fspec :args (s/cat :x any?)
           :ret boolean?))
(s/fdef opposite
  :args (s/cat :pred ::pred)
  :ret ::pred)
(defn opposite [pred]
  (comp not pred))

;; ### Generative testing ###

(s/fdef clojure.core/symbol
  :args (s/cat :ns (s/? string?) :name string?)
  :ret symbol?
  :fn (fn [{:keys [args ret]}]
        (and (= (name ret) (:name args))
             (= (namespace ret) (:ns args)))))

(stest/check 'clojure.core/symbol)

;; Combining generators with s/and

(defn big? [] (> x 100))

(s/def ::big-odd (s/and int? odd? big?))

;; Creating custom generators