(ns ClojuRPG.progress-util)

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

(defn special-check
  "Check if a special event occured; based on
   trigger values from vector of [XP day] vectors.
   Given XP and day are not in any way related,
   and the ralations between current day-xp vector
   and check-vector are provided in the operations 
   vector - [6 140] [\"=\" \">\"] [[12 400] [23 520]]"
  [day-xp operations check-vector] (for [[iter-xp iter-day] check-vector :when (or ((read-string (first operations)) iter-day (first day-xp)) ((read-string (last operations)) iter-xp (last day-xp)))]
                                     (if (= iter-day (first day-xp)) ["day" (first day-xp) (first operations)] ["xp" (last day-xp) (last operations)])))
  
(defn redirect-prog-check
  "Checks if current progress is equal
   to any from the given vector. Used
   for signaling when to redirect from
   Explore to Talk screen."
  [progress-vector progress] (empty? 
                               (take 1 
                                 (for [iter-progress progress-vector :when (= iter-progress progress)]
                                  iter-progress))))

(defn progress-increment
  "Increments the progress of the player
   if the criteria (base) provided is in
   required relation with the :prog stat"
  ([base player arg & current-day-or-extra] (condp = base
                                              ;[2 5 8 9] Increase progress only if current :prog is equal to one of the given numbers
                                              "prog" (if-not (progress-prog-check arg (:prog player))
                                                       1 0)
                                              ;[[200 3] [350 5] [520 7]] = [min-xp-for-prog-up min-prog-to-trigger-incrementation]
                                              "xp" (if-not (day-or-xp-progress-check (:xp player) arg (:prog player))
                                                     1 0)
                                              ;[[21 2] [33 4] [52 6]] = [min-days-for-prog-up min-prog-to-trigger-incrementation]
                                              "day" (if-not (day-or-xp-progress-check current-day-or-extra arg (:prog player)) 
                                                      1 0)
                                              ;[[3 7] [6 10] [8 14]] = [min-lvl-for-prog-up min-prog-to-trigger-incrementation]
                                              "level" (if-not (level-progress-check current-day-or-extra arg (:prog player)) 
                                                      1 0)
                                              ;If a special event occured - amount of days or amount of XP has been gathered
                                              ;imput parameter example: "special" player [[21 300] [26 400]] [2 ["=" "="]]
                                              ;call parameter example [2 150] ["=" "="] [[21 300] [26 400]]
                                              "special" (special-check [(first current-day-or-extra) (:xp player)] (rest current-day-or-extra) arg))))

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