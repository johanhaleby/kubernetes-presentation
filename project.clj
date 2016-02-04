(defproject kubernetes-presentation "1.0.0"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [compojure "1.4.0"]
                 [ch.qos.logback/logback-classic "1.1.2"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-jetty-adapter "1.4.0"]]
  :plugins [[lein-ring "0.9.7"] [com.palletops/uberimage "0.4.1"]]
  :ring {:handler kubernetes-presentation.handler/app}
  :uberimage {:tag "gcr.io/parkster-env-jayway-pres/kubernetes-presentation:1.0.0"}
  :main ^:skip-aot kubernetes-presentation.handler
  :profiles {:dev     {:dependencies [[javax.servlet/servlet-api "2.5"]
                                      [ring/ring-mock "0.3.0"]]}
             :uberjar {:aot :all}})
