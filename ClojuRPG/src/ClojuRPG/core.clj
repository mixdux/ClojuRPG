(ns ClojuRPG.core
  (:use clojure.repl compojure.core [ring.middleware.params :only [wrap-params]])
  (:require [compojure.route :as route] [ClojuRPG.pages :as pages]))

(defstruct player-template :name :race :str :dex :int :prim :xp :prog)
(defn new-player
      "Make a new player"
      [name] (struct-map player-template :name name))
(def Player (ref (new-player "")))

(defn class-resolver
  "Populate class specific attributes"
  [class] (cond
            (= class "Human") (dosync (ref-set Player (merge @Player {:race "human" :str 3 :dex 2 :int 5 :xp 0 :prog 0})))
            (= class "Elf") (dosync (ref-set Player (merge @Player {:race "elf" :str 2 :dex 5 :int 3 :xp 0 :prog 0})))
            (= class "Orc") (dosync (ref-set Player (merge @Player {:race "orc" :str 5 :dex 2 :int 3 :xp 0 :prog 0})))
            ))

(defn primary-atribute-resolver
  "Sets the primary attribute of a hero - and a difficulty"
  [primary] (dosync (ref-set Player (assoc-in @Player [:prim] primary))))

(defn onto-class-selection "To class selection screen" [name]
  (dosync (ref-set Player (assoc-in @Player [:name] name)))
  (pages/character-creation))

(defn onto-difficulty-screen "To the difficulty selection screen" [race]
  (class-resolver race)
  (pages/difficulty-selection (@Player :race) (@Player :name)))

(defn onto-history-screen "To the history story screen" [primary]
  (primary-atribute-resolver primary)
  (pages/heros-history (@Player :race) (@Player :name)))

(defn onto-main-screen "To the main, city, screen" [city]
  (pages/city-ambient city))

(defroutes routes-def
  (GET "/" [] (pages/landing))
  (POST "/begin" [hero-name race] (if-not (clojure.string/blank? hero-name) 
                                   (onto-class-selection hero-name) 
                                   (if-not (clojure.string/blank? race) 
                                     (onto-difficulty-screen race)
                                   )
                                   ))
  (POST "/intro" [primary] (if-not (clojure.string/blank? primary) 
                                   (onto-history-screen primary) 
                                   ))
  (POST "/game" [city talk] (if-not (clojure.string/blank? city) 
                                   (onto-main-screen city)
                                   ;for testing
                                   (onto-main-screen "Arniacg")
                                   ))
  ;(POST "/begin" [name :as u] (pages/character-creation name u))
  ;(GET "/kreni" [id] (stranice/id)))
  ;(ANY "*" [] (route/not-found (slurp (io/resource "404.html")))))
  )
  
(def route (wrap-params routes-def))

