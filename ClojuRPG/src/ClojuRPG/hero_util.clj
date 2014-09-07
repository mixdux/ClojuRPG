(ns ClojuRPG.hero-util
  (:require [ClojuRPG.util :as util]
            [clojure.math.numeric-tower :as math]
            [ClojuRPG.localisation :as localisation-txt]))

(defmulti multiclass (fn [class player] class ))

(defn class-resolver
  "Populate class specific attributes"
  [class player] (multiclass class player))

(defn primary-atribute-resolver
  "Sets the primary attribute of a hero - and a difficulty"
  [player difficulty] (assoc-in player [:prim] (util/prefix-specific-text (player :race) difficulty)))

(defn level-resolver
  "Returns the current level of a player.
   It requires a function between player's
   level and XP amount i.e #(math/sqrt %)."
  [lvl-function xp] (math/round (lvl-function xp)))
;(level-resolver #(math/sqrt %) 15)

(defn difficulty-resolver
  "Writes the selected difficulty name"
  [race primary] (if (= primary (util/prefix-specific-text race "difficulty-easy"))
                   localisation-txt/smallest-difficulty
                   (if (= primary (util/prefix-specific-text race "difficulty-medium"))
                     localisation-txt/medium-difficulty
                     localisation-txt/greatest-difficulty)))

(defn increase-stats-per-level
  "Gives amounts of stat increasing, following
   a given vector - i.e. [1 2 3] will increase
   first stat on each level, second on every 2.
   and third on every 3. level."
  [increase-vector xp] (let [lvl (level-resolver #(math/sqrt %) xp)]
                         [(quot lvl (first increase-vector))
                          (quot lvl (second increase-vector))
                          (quot lvl (last increase-vector))]))

(defmulti multistat (fn [race xp] race))

(defn race-stat-level
  "Distributes points among attributes,
   differently for different races"
  [race xp] (multistat race xp))

(defmulti multiincrement (fn [lvl-inc player] (:prim player)))

(defn player-lvl-increment-primary
  "Increments player primary stat, based on
   the race and increment vector"
  [lvl-inc player] (multiincrement lvl-inc player))

(defn player-xp-updater
  "Updates the XP for the player provided
   based on the vestor of figths"
  [player fights-won] (update-in player [:xp] #(+ % fights-won)))

;-----> Multimethods ahead! <-----

(defmethod multiclass "Human" [class player] 
  (merge player {:race "human"
                 :str 3
                 :dex 2
                 :int 5
                 :xp 0
                 :prog 1 }))

(defmethod multiclass "Elf" [class player] 
  (merge player {:race "elf"
                 :str 2
                 :dex 5
                 :int 3
                 :xp 0
                 :prog 1 }))
  
(defmethod multiclass "Orc" [class player] 
  (merge player {:race "orc"
                 :str 5
                 :dex 2
                 :int 3
                 :xp 0
                 :prog 1 }))

(defmethod multiincrement ":str" [lvl-inc player] 
  (+ (first lvl-inc) (:str player)))

(defmethod multiincrement ":dex" [lvl-inc player] 
  (+ (second lvl-inc) (:dex player)))

(defmethod multiincrement ":int" [lvl-inc player] 
  (+ (last lvl-inc) (:int player)))

(defmethod multistat "human" [race xp] 
  (increase-stats-per-level [2 3 1] xp))

(defmethod multistat "elf" [race xp] 
  (increase-stats-per-level [3 1 2] xp))

(defmethod multistat "orc" [race xp] 
  (increase-stats-per-level [1 3 2] xp))