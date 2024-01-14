(ns hello
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [clojure.data.json :as json]
            [io.pedestal.http.content-negoation :as content-negotiation]))

(def supported-types ["text/html"
                      "application/edn"
                      "application/json"
                      "text/plain"])

(def content-negotiation-interceptor
  (content-negotiation/negotiate-content supported-types))

(defn accepted-type [context]
  (get-in context [:request :accept :field] "text/plain"))

(defn transform-content [body content-type]
  (case content-type
    "text/html" body
    "text/plain" body
    "application/edn" (pr-str body)
    "application/json" (json/write-str body)))

(defn coerce-to [response content-type]
  (-> response
      (update :body transform-content content-type)
      (assoc-in [:headers "Content-Type"] content-type)))


(def coerce-body-interceptor
  {:name ::coerce-body
   :leave (fn [context]
            (if (get-in context [:response :headers "Content-Type"])
              context
              (update-in context [:response] coerce-to (accepted-type context))))})

(def unmentionables #{"YHWH"
                      "Voldemort"
                      "Mxyzptlk"
                      "Rumplestiltskin"
                      "曹操"})

(defn ok [body]
  {:status 200 :body body :headers {"Content-Type" "text/html"}})

(defn not-found []
  {:status 404 :body "Not found\n"})

(defn greeting-for [name]
  (cond
    (unmentionables name) nil
    (empty? name) "Hello, world\n"
    :else (str "Hello, " name "\n")))

(defn respond-hello [request]
  (let [name (get-in request [:query-params :name])
        body (greeting-for name)]
    (if body
      (ok body)
      (not-found))))

(def echo
  {:name ::echo
   :enter #(assoc % :response (ok (:request %)))})

(def routes
  (route/expand-routes
   #{["/greet" :get [coerce-body-interceptor content-negotiation-interceptor respond-hello] :route-name :greet]
     ["/echo" :get echo]}))

(defn create-server []
  (http/create-server
   {::http/routes routes
    ::http/type :jetty
    ::http/port 8890}))

(defn start []
  (http/start (create-server)))