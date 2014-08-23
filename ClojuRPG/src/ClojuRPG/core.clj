(ns ClojuRPG.core
  (:use clojure.repl compojure.core [ring.middleware.params :only [wrap-params]])
  (:require [compojure.route :as route] [ClojuRPG.pages :as pages]))

(defstruct player-template :name :race :str :dex :int :prim :xp :prog)
(defn new-player
      "Make a new player"
      [name] (struct-map player-template :name name))
(defn city-selector
  "Selects a city based on the player's progress (:prog) in game"
  [player] (cond
            (< (player :prog) 3) "Arniacg"
            :else "to be continued..."
             ))
(defroutes routes-def
  (GET "/" [] (pages/landing))
  (POST "/begin" [hero-name player] (if-not (clojure.string/blank? hero-name) 
                                     (pages/character-creation (new-player hero-name))
                                     (if-not (nil? player) 
                                       (pages/difficulty-selection (read-string player))
                                     )))
  (POST "/intro" [player] (if-not (nil? player) 
                                   (pages/heros-history (read-string player))))
  (POST "/game" [talk explore rest player day] (if-not (nil? player) 
                                   (pages/city-ambient (read-string player) (city-selector (read-string player)) (if (nil? day) 0 (inc (read-string day))))
                                   ;for testing
                                   ;(pages/city-ambient heroj city)
                                   ))
  ;(POST "/begin" [name :as u] (pages/character-creation name u))
  ;(GET "/kreni" [id] (stranice/id)))
  ;(ANY "*" [] (route/not-found (slurp (io/resource "404.html")))))
  )
  
(def route (wrap-params routes-def))

