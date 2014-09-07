(defproject ClojuRPG "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [compojure "1.1.8"]
                 [ring "1.3.0"]
                 [hiccup "1.0.5"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [midje "1.4.0"]
                 ]
  :plugins [[lein-ring "0.8.11"]
            [lein-midje "3.1.3"]]
  :ring {:handler ClojuRPG.core/route
         :auto-reload? true
         })
