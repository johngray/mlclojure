(defproject mlclojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clatrix "0.5.0"]
                 [org.clojure/data.csv "0.1.3"]
                 [org.clojure/tools.trace "0.7.9"]]
  :plugins [[lein-environ "1.1.0"]]
  :main ^:skip-aot mlclojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
