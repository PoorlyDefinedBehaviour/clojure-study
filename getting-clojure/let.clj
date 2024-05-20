(defn compute-discount-amount [amount discount-percentage min-charge]
  (if (> (* amount (- 1.0 discount-percentage)) min-charge)
    (* amount (- 1.0 discount-percentage))
    min-charge))

(defn compute-discount-amount [amount discount-percentage min-charge]
  (let [discounted-amount (* amount (- 1.0 discount-percentage))]
    (if (> discounted-amount min-charge)
      discounted-amount
      min-charge)))

(def user-discounts {"Nicholas" 0.10
                     "Jonatahn" 0.07
                     "Felicia" 0.05})

(def anonymous-book {:title "Sir Gawain and the Green Knight"})

(def with-author
  {:title "Once and Future King" :author "White"})

(defn uppercase-author [book]
  (if-let [author (:author book)]
    (.toUpperCase author)))

(defn uppercase-author [book]
  (when-let [author (:author book)]
    (.toUpperCase author)))