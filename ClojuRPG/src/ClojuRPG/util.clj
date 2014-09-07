(ns ClojuRPG.util
  (:require [clojure.repl :refer :all]
            [ClojuRPG.localisation :as localisation-txt]))

(defn wrap-element
  "Add tags to text"
  ([x y] (str "<" x ">" y "</" x ">"))
  ([x y z] (str "<" x "id=" z ">" y "</" x ">")))

(defn add-hero-name
  "Inserts hero's name into provided text (on predefined positions)"
  [name text] (clojure.string/replace text #"~HERO~" name))

(defn resolve-var-from-loc
  "Gets the variable with the provided
   name form 'localisation' namespace"
  [var-name] (ns-resolve 'ClojuRPG.localisation (symbol var-name)))

(defn value-of-var-from-loc
  "Resolves (returns a value of the variable)
   that is defined in 'localisation' namespace"
   [var-name] (var-get (resolve-var-from-loc (symbol var-name))))

(defn prefix-specific-text
  "Returns text for a provided naming combination"
  [prefix text] (var-get (resolve-var-from-loc (str prefix "-" text))))

(defn suffix-specific-text
  "Returns text for a provided naming combination;
   Iterates through progress if suffix is a number
   - used when text is not defined for that particular
   amount of progress (returns the last defined)"
  [text suffix] (if (string? suffix) 
                  (value-of-var-from-loc (str text "-" suffix))
                  (first
                    (for [x (reverse (range (+ 1 suffix))) 
                          :when (not (nil? (resolve-var-from-loc (str text "-" x))))]
                      (value-of-var-from-loc (str text "-" x))))))

(defn pre-su-text
  "Makes a combination of prefix and suffix specific text
   by adding the prefix to it, and then iterating through
   progress"
  [prefix text suffix] (if (string? suffix) 
                         (value-of-var-from-loc (str prefix "-" text "-" suffix))
                         (first
                           (for [x (reverse (range (+ 1 suffix))) 
                                 :when (not (nil? (resolve-var-from-loc (str prefix "-" text "-" x))))]
                             (value-of-var-from-loc (str prefix "-" text "-" x))))))

(defn player-name-and-status
  "Is used to write players name,
   race, XP and level (currently
   beneath city buttons)"
  [player lvl-incr] (str 
             (suffix-specific-text "strength"  (:prog player))
             (+ (first lvl-incr) (:str player)) " | "
             (suffix-specific-text "dexterity"  (:prog player))
             (+ (second lvl-incr) (:dex player)) " | "
             (suffix-specific-text "inteligence" (:prog player))
             (+ (last lvl-incr)  (:int player))))