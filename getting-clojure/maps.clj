{"title" "Oliver twist"
 "author" "Dickens"
 "published" 1838}

;; Creates a map.
(hash-map "title" "Oliver twist"
          "author" "Dickens"
          "published" 1838)
;; {"author" "Dickens", "published" 1838, "title" "Oliver twist"}

(def book {"title" "Oliver Twist"
           "author" "Dickens"
           "published" 1838})

(get book "published")
;; 1838

;; Maps behave like functions.
(book "published")
;; 1838

(def book {:title "Oliver twist"
           :author "Dickens"
           :published 1838})

(book :title)
;; "Oliver twist"

(:title book)
;; "Oliver twist"

(assoc book :page-count 362)
;; {:title "Oliver twist", :author "Dickens", :published 1838, :page-count 362}

(dissoc book :published)
;; {:title "Oliver twist", :author "Dickens"}

(keys book)
;; (:title :author :published)

(vals book)
;; ("Oliver twist" "Dickens" 1838)