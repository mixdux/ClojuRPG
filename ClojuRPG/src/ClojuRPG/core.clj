(ns ClojuRPG.core
  (:require [clojure.repl :refer :all]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ClojuRPG.pages :as pages]))

(defstruct player-template :name :race :str :dex :int :prim :xp :prog)

(defn new-player
  "Make a new player"
  [name] (struct-map player-template :name name))

(defn city-selector
  "Selects a city based on the player's progress (:prog) in game"
  [player] (cond 
            (< (:prog player) 6) "Arniacg"
            :else "Tomorrowland"))

(defn action-selector
  "Determines the action that the player wants to make"
  [player day action increase & redirect] (cond
            (= action "people") (pages/talk player day action increase)
            (= action "elders") (pages/talk player day action increase)
            (= action "explore") (if (= 1 (read-string (last (last redirect))))
                                   (pages/talk player day "redirect-explore" increase)
                                   (pages/explore player day (city-selector player) increase))
            (= action "travel") (pages/talk player day action increase)
            :else (pages/debug "Action not supported")))

(defroutes routes-def
  (GET "/" [] (pages/landing))
  (POST "/begin"
        [hero-name player] (if-not (clojure.string/blank? hero-name) 
                             (pages/character-creation (new-player hero-name))
                             (if-not (nil? player) 
                               (pages/difficulty-selection (read-string player)))))
  (POST "/intro"
        [player] (if-not (nil? player)
                   (pages/heros-history (read-string player))))
  (POST "/game"
        [player day] (if-not (nil? player) 
                        (pages/city-ambient (read-string player) (city-selector (read-string player)) (if (nil? day) 0 (inc (read-string day))))))
  (POST "/action"
        [player day action increase & redir] (if (= 6 (:prog (read-string player))) ;Used for ending the game
                                               (pages/debug "to be continued...")
                                               (if-not (nil? player)
                                                 (action-selector (read-string player) (read-string day) action (read-string increase) (first redir))))))
;(POST "/begin" [name :as u] (pages/character-creation name u))
  ;(GET "/kreni" [id] (stranice/id)))
  ;(ANY "*" [] (route/not-found (slurp (io/resource "404.html")))))
  
(def route (ring.middleware.params/wrap-params routes-def))

