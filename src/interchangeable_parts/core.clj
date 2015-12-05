(ns interchangeable-parts.core
  (:require [ring.adapter.jetty :as jetty]))

(defprotocol Lifecycle
  (start [x])
  (stop [x])
  (running? [x]))

(deftype JettyServer [handler config server]
  Lifecycle
  (start [_]
    (println "Start Jetty server." (pr-str config))
    (if (nil? @server)
      (do
        (reset! server (jetty/run-jetty handler (assoc config :join? false)))
        (println "Done."))
      (println "Server already running; doing nothing."))
    nil)
  (stop [_]
    (println "Stopping Jetty server." (pr-str config))
    (if (nil? @server)
      (println "Server already stopped; doing nothing.")
      (do
        (.stop @server)
        (reset! server nil)
        (println "Done.")))
    nil)
  (running? [_]
    (boolean @server)))

(defn make-jetty-server [handler config]
  (JettyServer. handler config (atom nil)))

(extend-protocol Lifecycle
  clojure.lang.Sequential
  (start [parts]
    (doseq [part parts]
      (start part)))
  (stop [parts]
    (doseq [part (reverse parts)]
      (stop part)))
  (running? [parts]
    (boolean (some running? parts))))
