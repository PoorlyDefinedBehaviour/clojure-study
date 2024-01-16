(def person {:name "Eric Normand”"
             :address "123 Main St."
             :height 1.6
             :date-of-birth #inst "1981-07-18"})



(assoc person :name "John Smith")
;; {:name "John Smith", :address "123 Main St.", :height 1.6, :date-of-birth #inst "1981-07-18T00:00:00.000-00:00"}

(dissoc person :name)
;; {:address "123 Main St.", :height 1.6, :date-of-birth #inst "1981-07-18T00:00:00.000-00:00"}