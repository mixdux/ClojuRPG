(ns ClojuRPG.monster-util
  (:require [clojure.repl :refer :all]            
            [ClojuRPG.util :as util]))

(defstruct monster-template :name :species :str :dex :int :prim)

(defn attribute-stat-randomizer
  "Randomises stats, taking into account the progress.
   Vector of max values (unmodified by progress) needs
   to be passed, fomated in this fasion:
   [tertiary secondary primary] i.e. [2 3 5]"
  [v-o-a progress]
  (let [
        ter (+ 1 (rand-int (first v-o-a)))
        sec (+ 1 (rand-int (second v-o-a)))
        prim (+ 1 (rand-int (last v-o-a)))] (
                                              let [
                                                   prim-p (+ prim  (rand-int (inc progress)))
                                                   sec-p (+ sec (rand-int (inc (- progress (- prim-p prim)))))
                                                   ter-p (+ ter (rand-int (inc (- progress (+ (- prim-p prim) (- sec-p sec))))))]
                                              [ter-p sec-p prim-p])))

(defmulti multimonster (fn [race progress] race))

(defn race-attribute-deployer
  "Deploys attribute amounts for each race;
   re-orders vector of values so it matches:
   1. strength; 2. dexterity; 3. inteligence
   as is needed for each race.
   IMPORTANT: Depending on the conplexity of
   the game, you may choose to modify the progress
   variable i.e. not send plain progress, but
   increment it by 2, or add it player's level
   (in order to get stornger opponents)"
  [race progress] (multimonster race progress))

(defn race-primary-resolver
  "Specifies the primary attribute for each race"
  [race] (condp = race
           "satyr" ":str"
           "centaur" ":dex"
           "gnoll" ":int"))

(defn create-a-monster
  "Creates a random monster"
  [progress] (let [race (condp = (rand-int 3)
                          0 "satyr"
                          1 "centaur"
                          2 "gnoll")
                   attributes-vector (race-attribute-deployer race progress)]
               (struct-map monster-template
                         :name (str (util/suffix-specific-text (str "creature-power-" (rand-int 3)) progress) " " race)
                         :species race
                         :str (first attributes-vector)
                         :dex (second attributes-vector)
                         :int (last attributes-vector)
                         :prim (race-primary-resolver race)
                         )))

(defn opponent-creator
  "Creates creatures for you to fight"
  [player] (take (rand-int (+ 1 ((read-string (:prim player)) player))) 
                 (repeatedly 
                   #(create-a-monster (:prog player)))))

;-----> Multimethods ahead! <-----

(defmethod multimonster "satyr" [race progress] 
  (let [attr-vec (attribute-stat-randomizer [2 3 5] progress)]
    [(last attr-vec) (first attr-vec) (second attr-vec)]))

(defmethod multimonster "centaur" [race progress] 
  (let [attr-vec (attribute-stat-randomizer [2 3 5] progress)]
    [(first attr-vec) (last attr-vec) (second attr-vec)]))

(defmethod multimonster "gnoll" [race progress] 
  (let [attr-vec (attribute-stat-randomizer [2 3 5] progress)]
    [(second attr-vec) (first attr-vec) (last attr-vec)]))