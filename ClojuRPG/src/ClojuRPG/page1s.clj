(ns tierOne.stranice (:use hiccup.page hiccup.element hiccup.form)
  (:require [tierOne.localisation :as localisation-txt]))

(defn oblikuj-element "Dodaje tagove tekstu"
  ([x y] (str "<" x ">" y "</" x ">"))
  ([x y z] (str "<" x "id=" z ">" y "</" x ">")))


(defn pocetna "Main headline" [] (html5 [:html
                                         [:head
                                          [:title "ClojuRPG"]
                                          (include-css "./resources/style.css")
                                          ]
                                         [:body 
                                          [:div#main-headline.h1 (oblikuj-element "h1" localisation-txt/welcome-headline)]
                                          [:div#welcome-text.p (oblikuj-element "p" localisation-txt/welcome-text)]
                                          [:div#go-forma (form-to [:post "/kreni"] (text-field {:placeholder "Player name"} "weights") (submit-button localisation-txt/begin-game))]
                                         ]
                                        ]))
(defn dalje "U igru" [ime] (html5 [:html
                                         [:head
                                          [:title "ClojuRPG"]
                                          (include-css "/style.css")
                                          ]
                                         [:body 
                                          [:div#main-headline.h1 ime]
                                         ]
                                        ]))
(defn id "U igru" [id] (html5 [:html
                                         [:head
                                          [:title "ClojuRPG"]
                                          (hiccup.page/include-css "/style.css")
                                          ]
                                         [:body 
                                          [:div#main-headline.h1 (oblikuj-element "h1" id)]
                                         ]
                                        ]))