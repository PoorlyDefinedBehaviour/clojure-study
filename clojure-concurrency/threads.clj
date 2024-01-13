(def thread (Thread. (fn [] (println 1 2 3))))

(.start thread)

(System/exit 0)