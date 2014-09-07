(ns ClojuRPG.fight-util
  (:require [ClojuRPG.util :as util]
            [clojure.math.numeric-tower :as math]
            [ClojuRPG.hero-util :as hero-util]))

(defn fight-number-calculator
  "Calculates the number of points for
   the fighter provided; also increments
   the primary stat by fixed number:
   primary-stat-increment-percent or p-s-i-p."
  ([monster p-s-i-p] (+ 
                       (+ 
                         (:str monster) 
                         (:int monster)
                         (:dex monster))
                       (- 
                         (math/round (*  
                                       ((read-string (:prim monster)) monster) 
                                       p-s-i-p))
                         ((read-string (:prim monster)) monster))))
   ([player p-s-i-p xp] (let [lvl-inc (hero-util/race-stat-level (:race player) xp)]
                          (+ 
                           (+ 
                             (+ (first lvl-inc) (:str player))
                             (+ (second lvl-inc) (:dex player))
                             (+ (last lvl-inc) (:int player)))
                           (- 
                             (math/round (* 
                                            (hero-util/player-lvl-increment-primary lvl-inc player)
                                            p-s-i-p))
                              (hero-util/player-lvl-increment-primary lvl-inc player))))))

(defmulti multibonus (fn [race species percent] race))

(defn get-race-bonus
  "Gives multiplier for each player race
   when fights a monster"
  [race species percent] (multibonus race species percent))

(defn resolve-fight
  "Returns 1 if the player defeats a monster,
   0 if monster defeats the player"
  [monster player] (if 
                     (>= (*
                           (get-race-bonus (:race player) (:species monster) 20) 
                           (fight-number-calculator player 20 (:xp player)))
                         (fight-number-calculator monster 20))
                     1 0))

(defn won-encounter-report
  "Writes the success message about
   defeating a monster"
  [player monster](str
                    (util/suffix-specific-text "fight-won-1-0" (:prog player))
                    (:name monster)
                    (util/suffix-specific-text "fight-won-2-0" (:prog player))
                    (util/suffix-specific-text "fight-stat-1-0" (:prog player))
                    (let [lvl-incr (hero-util/race-stat-level (:race player) (:xp player))]
                      (seq [(+ (first lvl-incr) (:str player))
                            (+ (second lvl-incr) (:dex player)) 
                            (+ (last lvl-incr) (:int player))]))
                    (util/suffix-specific-text "fight-stat-2-0" (:prog player))
                    (seq [(:str monster) (:dex monster) (:int monster)])
                    ))

(defn lost-encounter-report
  "Writes the message about who
   defeated the player"
  [player monster] (str
                     (util/suffix-specific-text "fight-lost-1-0" (:prog player))
                     (:name monster)
	                   (util/suffix-specific-text "fight-lost-2-0" (:prog player))
	                   (util/suffix-specific-text "fight-stat-1-0" (:prog player))
	                   (seq [(:str player) (:dex player) (:int player)])
	                   (util/suffix-specific-text "fight-stat-2-0" (:prog player))
	                   (seq [(:str monster) (:dex monster) (:int monster)])
	                   ))

(defn check-lost-fights
  "Checks if any of the fights were lost
   and writes the appropriate text"
  [player monsters fight-resaults] (if-not (= (count monsters) (count fight-resaults))
                                     (lost-encounter-report player
                                                            (second 
                                                              (take-nth (count fight-resaults) monsters)))))

(defn fight-monsters
  "Fights provided monsters and gives
   the resault of the fight"
  [monsters player] (if-not (nil? monsters)
                      (for [attacker monsters :while (= 1 (resolve-fight attacker player))] 
                        (won-encounter-report player attacker))))

;Elf>Satyr>Human Human>Centaur>Orc Orc>Gnoll>Elf

(defmethod multibonus "human" [race species percent] 
  (condp = species
    "satyr" (- 1 (* percent 0.01))
    "centaur" (+ 1 (* percent 0.01))
    "gnoll" 1))

(defmethod multibonus "orc" [race species percent] 
  (condp = species
    "satyr" 1
    "centaur" (- 1 (* percent 0.01))
    "gnoll" (+ 1 (* percent 0.01))))

(defmethod multibonus "elf" [race species percent] 
  (condp = species
    "satyr" (+ 1 (* percent 0.01))
    "centaur" 1
    "gnoll" (- 1 (* percent 0.01))))