(defproject first-atom "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [com.clojure-goes-fast/clj-memory-meter "0.1.0"]]
  :main ^:skip-aot first-atom.core
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[nrepl "0.5.3"
             :plugins [[cider/cider-nrepl "0.20.0"]]]]}
})
