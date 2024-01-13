(import 'java.util.concurrent.ExecutorService)
(import 'java.util.concurrent.Executors)

(def service (Executors/newFixedThreadPool 4))

(def f (.submit ^ExecutorService service ;; the hint is required to use the Callable version
                ^Callable (fn []
                            ;; do some work
                            )))

;; block on the Future.
(println @f)