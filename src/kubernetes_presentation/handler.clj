(ns kubernetes-presentation.handler
  (:gen-class)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.tools.logging :as log]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))
(defroutes app-routes
           (GET "/" []
             (log/info "Received request to V1")
             "Hello World V1")
           (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main [& args]
  (run-jetty #'app {:port 3000 :join? false}))

