(ns inventory.core-test
  (:require [clojure.test :refer :all]
            [inventory.core :as i
             clojure.test.check.generators :as gen]))

(def books
  [{:title "2001" :author "Clarke" :copies 21}
   {:title "Emma" :author "Austen" :copies 10} {:title "Misery" :author "King" :copies 101}])

(deftest test-finding-books
  (is (not (nil? (i/find-by-title "Emma" books)))))

(def title-gen (gen/such-that not-empty gen/string-alphanumeric))
(def author-gen (gen/such-that not-empty gen/string-alphanumeric))
(def copies-gen (gen/such-that (complement zero?) gen/pos-int))
(def book-gen (gen/hash-map :title title-gen :author author-gen :copies copies-gen))
(def inventory-gen (gen/not-empty (gen/vector book-gen)))
(def inventory-and-book-gen (gen/let [inventory inventory-gen
                                      book (gen/elements inventory)]
                              {:inventory inventory
                               :book book}))