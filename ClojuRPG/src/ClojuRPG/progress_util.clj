(ns ClojuRPG.progress-util
  (:use [clojure.set]))

(defn progress-prog-check
  "Checks if current progress is equal
   to any from the given vector"
  [progress-vector progress] (empty? 
                               (take 1 
                                 (for [iter-progress progress-vector :when (= iter-progress progress)]
                                  iter-progress))))

(defn day-or-xp-progress-check
  "Checks if any of the conditions from
   [min-days/xp min-progress] (check-)vector
   are met"
  [day-xp check-vector progress] (every? nil? 
                                        (for [[min-days-xp min-prog]  check-vector :when (>= day-xp min-days-xp)]
                                          (if (and (>= progress min-prog) (< progress (+ min-prog 1)))
                                            min-days-xp))))

(defn level-progress-check
  "Checks if any of the conditions from
   [min-lvl min-progress] (check-)vector
   are met"
  [level check-vector progress] (every? nil? 
                                        (for [[min-lvl min-prog]  check-vector :when (>= level min-lvl)]
                                          (if (and (>= progress min-prog) (< progress (+ min-prog 1)))
                                            min-lvl))))
  
(defn redirect-prog-check
  "Checks if current progress is equal
   to any from the given vector. Used
   for signaling when to redirect from
   Explore to Talk screen."
  [progress-vector progress] (empty? 
                               (take 1 
                                 (for [iter-progress progress-vector :when (= iter-progress progress)]
                                  iter-progress))))

(defmulti multiprogress (fn [base player arg & current-day-or-extra] base))
  
(defn progress-increment
  "Increments the progress of the player
   if the criteria (base) provided is in
   required relation with the :prog stat"
  ([base player arg & current-day-or-extra] (multiprogress base player arg current-day-or-extra)))

(defn progres-checker
  "Checks if any of the conditions have
   been met for progress to increase, and
   returns the amount od progress increases"
  [player prog-vector xp-vector day-vector lvl-vector day level] (count
                                                                      (remove #(= % 0)
                                                                              [(progress-increment "prog" player prog-vector)
                                                                               (progress-increment "xp" player xp-vector)
                                                                               (progress-increment "day" player day-vector day)
                                                                               (progress-increment "level" player lvl-vector level)])))

(defn special-check
  "Check if a special event occured; based on
   trigger values from vector of [XP day] vectors.
   Given XP and day are not in any way related,
   and the ralations between current day-xp vector
   and check-vector are provided in the operations 
   vector - [6 140] [\"=\" \">\"] [[12 400] [23 520]]"
  [day-xp operations check-vector] (for [[iter-day iter-xp] check-vector :when (or ((resolve (read-string (first operations))) (first day-xp) iter-day) ((resolve (read-string (last operations))) (last day-xp) iter-xp))]
                                     (into [] (concat    
                                       (if ((resolve (read-string (first operations))) (first day-xp) iter-day) ["day" (first day-xp) (first operations) iter-day])
                                       (if ((resolve (read-string (last operations))) (last day-xp) iter-xp)["xp" (last day-xp) (last operations) iter-xp])))))

;If a special event occured - amount of days or amount of XP has been gathered
;imput parameter example: player [[21 300] [26 400]] [2 ["=" "="]]
;call parameter example [2 150] ["=" "="] [[21 300] [26 400]]
(defn special-checker
  "Checks if the special event has occured,
   and returns the vector consisting the
   event (\"day\" or \"XP\"), amount that
   has triggered the event, and operation that
   has checked as positive (from the ones provided).
   IMPORTANT: It will return multiple resaults
   if multiple relate as true (i.e. both day and XP
   have mate the equasion, from the elements provided
   true)"
  [player arg day-day-operation-xp-operation]
  (special-check [(first day-day-operation-xp-operation) (:xp player)] (first (rest day-day-operation-xp-operation)) arg))

;-----> Multimethods ahead! <-----

;[2 5 8 9] Increase progress only if current :prog is equal to one of the given numbers
(defmethod multiprogress "prog" [base player arg & current-day-or-extra] 
  (if-not (progress-prog-check arg (:prog player))
    1 0))

;[[200 3] [350 5] [520 7]] = [min-xp-for-prog-up min-prog-to-trigger-incrementation]
(defmethod multiprogress "xp" [base player arg & current-day-or-extra] 
  (if-not (day-or-xp-progress-check (:xp player) arg (:prog player))
    1 0))
  
;[[21 2] [33 4] [52 6]] = [min-days-for-prog-up min-prog-to-trigger-incrementation]  
(defmethod multiprogress "day" [base player arg & current-day-or-extra] 
  (if-not (day-or-xp-progress-check current-day-or-extra arg (:prog player)) 
    1 0))

;[[3 7] [6 10] [8 14]] = [min-lvl-for-prog-up min-prog-to-trigger-incrementation]
(defmethod multiprogress "level" [base player arg & current-day-or-extra] 
  (if-not (level-progress-check current-day-or-extra arg (:prog player)) 
    1 0))