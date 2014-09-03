(ns ClojuRPG.hero-util
  (:require [ClojuRPG.util :as util]
            [clojure.math.numeric-tower :as math]
            [ClojuRPG.localisation :as localisation-txt]))

(defn class-resolver
  "Populate class specific attributes"
  [class player] (condp = class
                   "Human" (merge player {:race "human"
                                          :str 3
                                          :dex 2
                                          :int 5
                                          :xp 0
                                          :prog 1 })
                   "Elf" (merge player {:race "elf"
                                        :str 2
                                        :dex 5
                                        :int 3
                                        :xp 0
                                        :prog 1 })
                   "Orc" (merge player {:race "orc"
                                        :str 5
                                        :dex 2
                                        :int 3
                                        :xp 0
                                        :prog 1 })))

(defn primary-atribute-resolver
  "Sets the primary attribute of a hero - and a difficulty"
  [player difficulty] (assoc-in player [:prim] (util/prefix-specific-text (player :race) difficulty)))

(defn level-resolver
  "Returns the current level of a player.
   It requires a reverse function of XP difference between
   two levels i.e #(math/sqrt % and an XP amount."
  [lvl-function xp] (math/round (lvl-function xp)))
;(level-resolver #(math/sqrt %) 15)

(defn difficulty-resolver
  "Writes the difficulty selection"
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

(defn race-stat-level
  "Distributes points among attributes,
   differently for different races"
  [race xp] (condp = race
              "human" (increase-stats-per-level [2 3 1] xp)
              "elf" (increase-stats-per-level [3 1 2] xp)
              "orc" (increase-stats-per-level [1 3 2] xp)))

(defn player-lvl-increment-primary
  "Increments player primary stat, based on
   the race and increment vector"
  [lvl-inc player] (condp = (:prim player)
                     ":str" (+ (first lvl-inc) (:str player))
                     ":dex" (+ (second lvl-inc) (:dex player))
                     ":int" (+ (last lvl-inc) (:int player))))

(defn player-xp-updater
  "Updates the XP for the player provided
   based on the vestor of figths"
  [player fights-won] (update-in player [:xp] #(+ % fights-won)))