(peek '(1 2 3)) ; same as first
;; 1

(pop '(1 2 3))
;; (2 3)

(rest ())
;; ()

(pop ())
;; Exception: can't pop empty list

(peek [1 2 3])
;; 3

(pop [1 2 3])
;; [1 2]

(get [:a :b :c] 1)
;; :b

(get [:a :b :c] 5)
;; nil

;; Vectors are themselves functions.

([:a :b :c] 1)
;; :b

([:a :b :c] 5)
;; Exception: index out of bounds

;; assoc associates a new value with a particular index.
(assoc [0 1 2 3 4] 2 :two)
;; [0 1 :two 3 4]

;; subvec returns a subvector of a vector.
(subvec [1 2 3 4 5] 3)
;; [4 5]

(subvec [1 2 3 4 5] 1 3)
;; [2 3]

(keys {:sundance "spaniel" :darwin "beagle"})
;; (:sundance :darwin)

(vals {:sundance "spaniel" :darwin "beagle"})
;; ("spaniel" "Beagle")

(get {:sundance "spaniel" :darwin "beagle"} :sundance)
;; "spaniel"

(get {:sundance "spaniel" :darwin "beagle"} :key-not-in-the-map)
;; nil

(get {:sundance "spaniel" :darwin "beagle"} :key-not-in-the-map "default-value")
;; "default-value"

;; Maps are also functions, they take a key and return the value associated with it.

({:sundance "spaniel" :darwin "beagle"} :key-not-in-the-map)
;; nil

({:sundance "spaniel" :darwin "beagle"} :key-not-in-the-map "default-value")
;; "default-value"

;; Keywords are also functions, they take a collectio and look themselves up on the collection.

(:sundance {:sundance "spaniel" :darwin "beagle"})
;; "spaniel"

(:not-in-the-map {:sundance "spaniel" :darwin "beagle"})
;; nil

(contains? {:sundance "spaniel" :darwin "beagle"} :darwin)
;; true

(contains? {:sundance "spaniel" :darwin "beagle"} :bob)
;; false

(def song {:name "Agnus Dei"
           :artist "Krzysztof Penderecki"
           :album "Polish Requiem"
           :genre "Classical"})

(assoc song :kind "MPEG Audio File")
;; {:name "Agnus Dei", :artist "Krzysztof Penderecki", :album "Polish Requiem", :genre "Classical", :kind "MPEG Audio File"}

(dissoc song :genre)
;; {:name "Agnus Dei", :artist "Krzysztof Penderecki", :album "Polish Requiem"}

(select-keys song [:name :artist])
;; {:name "Agnus Dei", :artist "Krzysztof Penderecki"}

(merge song {:size 8118166 :time 507245})
;; {:name "Agnus Dei", :artist "Krzysztof Penderecki", :album "Polish Requiem", :genre "Classical", :size 8118166, :time 507245}

(require '[clojure.set :refer :all])

(def languages #{"java" "c" "d" "clojure"})
(def beverages #{"java" "chai" "pop"})

(union languages beverages)
;; #{"d" "clojure" "pop" "java" "chai" "c"}

(intersection languages beverages)
;; #{"java"}

(difference languages beverages)
;; #{"d" "clojure" "c"}

(select #(= 1 (count %)) languages)
;; #{"d" "c"}

