(ns ClojuRPG.core-test
  (:use midje.sweet
        ClojuRPG.core)
  (:require [ClojuRPG.core :as core]
            [ClojuRPG.fight-util :as fight]
            [ClojuRPG.hero-util :as hero]
            [ClojuRPG.localisation :as localisation]
            [ClojuRPG.monster-util :as monster]
            [ClojuRPG.pages :as pages]
            [ClojuRPG.progress-util :as progress]
            [ClojuRPG.util :as util]
            [clojure.math.numeric-tower :as math]))

(def player (hero/primary-atribute-resolver (hero/class-resolver "Human" (core/new-player "Milan")) "difficulty-easy"))

;-----> Core namespace tests

(fact "Creation of the new player map"
  (new-player "Milan") => {:name "Milan"
                          :race nil
                          :str nil 
                          :dex nil 
                          :int nil 
                          :prim nil
                          :xp nil
                          :prog nil})

(fact "City resolver"
      (core/city-selector player) => "Hellenia")

(facts "Selects the right page for a given ation; calls the right page"
       (fact "Call the talk page"
         (core/action-selector player 1 "people" 0) => (pages/talk player 1 "people" 0))
       ;Can't test by calling the explore funcion, because new random mosters are generated
       (fact "Call the explore page"
         (core/action-selector player 1 "explore" 0 ["0"]) => anything))

;-----> Hero-util namespace tests

(fact "Selects the right class for the player"
      (hero/class-resolver "Orc" player) => #(= (:race %) "orc"))

(facts "Selects the right primary attribute, based on the difficulty level and class"
       (fact "Set propper easy attribute for human race"
             (hero/primary-atribute-resolver player "difficulty-easy") => #(= (:prim %) ":int"))
       (fact "Set propper medium attribute for human race"
             (hero/primary-atribute-resolver player "difficulty-medium") => #(= (:prim %) ":str"))
       (fact "Set propper hard attribute for human race"
             (hero/primary-atribute-resolver player "difficulty-hard") => #(= (:prim %) ":dex")))

(fact "Finds out the level, based on the XP and function of those two"
      (hero/level-resolver #(math/sqrt %) 24) => 4)

(fact "Gives the name of the difficulty level"
      (hero/difficulty-resolver (:race player) (:prim player)) => "Easy")

(fact "Stats that should be increased per level (str, dex, int)"
      (hero/race-stat-level (:race player) 42) => [3 1 6])

;-----> Monster-util namespace tests - very few, because all monster stats are randomised

(def monster (monster/create-a-monster (:prog player)))
monster

(fact "Finds out the level, based on the XP and function of those two"
      (monster/create-a-monster (:prog player)) => anything)

;-----> Fight-util namespace tests - again, because of the monster's attribute randomization, not all functions have been tested

(facts "Return the batttle number"
       (fact "for monster"
             (fight/fight-number-calculator monster 20) => anything)
       (fact "for player"
             (fight/fight-number-calculator player 20 (:xp player)) => anything))

(fact "Player's bonus multiplier of battle number, based on monster's race"
             (fight/get-race-bonus (:race player) (:species monster) 20) => 0.80)

(fact "Resolve a fight"
      (fight/resolve-fight monster player) => 1)

(fact "Write the fight report"
      (fight/won-encounter-report player monster) => anything)

;-----> Progress-util namespace tests

(facts "Check progress"
       (fact "Progress is equal to"
         (progress/progress-prog-check [1 3 5 6] 3) => false)
       (fact "XP is equal or greater to"
         (progress/day-or-xp-progress-check 6 [[3 1] [4 3] [6 5] [9 6]] 3) => false)
       (fact "Day is equal or greater to"
         (progress/day-or-xp-progress-check 2 [[1 1] [2 3] [3 5] [4 6]] 3) => false)
       (fact "Level is equal or greater to"
         (progress/day-or-xp-progress-check 7 [[5 1] [7 3] [9 5] [10 6]] 3) => false))

(fact "Special event occured"
         (progress/special-checker player [[3 0] [9 26] [11 32]] [3 [">" "<"]]) => '(["xp" 0 "<" 26] ["xp" 0 "<" 32]))

(fact "Check whether progress increments, based on several attributes"
      (progress/progres-checker player [1] [] [] [] 4 3) => 1)

;-----> Util methods namespace tests

(fact "Text wrapped as h1"
         (str (util/wrap-element "h1" "ClijuRPG")) => "<h1>ClojuRPG</h1>")

(fact "Text starting with provided string"
         (util/prefix-specific-text "human" "difficulty-easy") => localisation/human-difficulty-easy)

(fact "Text ending with provided number"
         (util/suffix-specific-text "elders-button" 5) => localisation/elders-button-3)

(fact "Text ending with provided string"
         (util/suffix-specific-text "elders-button" "3") => localisation/elders-button-3)

(fact "Evaluate the variable with provided name"
         (util/value-of-var-from-loc "travel-headline-5") => "Leaving Aniarcg behind")

(fact "Write player's name and status"
         (util/player-name-and-status player [1 3 5]) => anything)

