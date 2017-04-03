(defproject mlclojure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "https://github.com/johngray/mlclojure"
  :license {:name "MIT License"
            :url "http://www.opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clatrix "0.5.0"]
                 [org.clojure/data.csv "0.1.3"]
                 [org.clojure/tools.trace "0.7.9"]
                 [debugger "0.2.0"]
                 [org.jzy3d/jzy3d-api "1.0.1-SNAPSHOT"]]
  :plugins [[lein-environ "1.1.0"]
            [lein-kibit "0.1.3"]]
  :main ^:skip-aot mlclojure.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :repositories [["Jzy3d Snapshots" "http://maven.jzy3d.org/snapshots"]
                 ["Jzy3d Releases" "http://maven.jzy3d.org/releases"]]
  :source-paths ["src"])
