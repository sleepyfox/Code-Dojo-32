(defproject diamond "0.1.0-SNAPSHOT"
  :description "An example implementation of the Diamond code kata"
  :url "https://github.com/sleepyfox/Code-Dojo-32"
  :license {:name "CC-BY-SA-NC 3.0"
            :url "http://creativecommons.org/licenses/by-nc-sa/3.0/"}
  :dependencies [[org.clojure/clojure "1.5.1"]]
  :main ^:skip-aot diamond.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
