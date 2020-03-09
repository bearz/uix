(defproject uix.dom "0.1.0-SNAPSHOT"
  :description "Testing if this will work with a project"
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.597"]
                 ;; uix.core is dependency but it is not added here yet.
                 [cljsjs/react-dom "16.9.0-0" :exclusions [cljsjs/react-dom-server]]])
