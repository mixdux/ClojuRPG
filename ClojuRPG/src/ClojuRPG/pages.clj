(ns ClojuRPG.pages (:use hiccup.page hiccup.element hiccup.form clojure.repl)
  (:require [ClojuRPG.localisation :as localisation-txt]))

(defn wrap-element "Add tags to text"
  ([x y] (str "<" x ">" y "</" x ">"))
  ([x y z] (str "<" x "id=" z ">" y "</" x ">")))
(defn add-hero-name "Inserts hero's name into provided text (on predefined positions)" [name text] (clojure.string/replace text #"~HERO~" name))
(defn race-specific-text "Returns text for a provided race" [race text] (var-get (ns-resolve 'ClojuRPG.localisation (symbol (str race "-" text)))))

(defn class-resolver
  "Populate class specific attributes"
  [class player] (cond
            (= class "Human") (merge player { :race "human" :str 3 :dex 2 :int 5 :xp 0 :prog 0 })
            (= class "Elf") (merge player { :race "elf" :str 2 :dex 5 :int 3 :xp 0 :prog 0 })
            (= class "Orc") (merge player { :race "orc" :str 5 :dex 2 :int 3 :xp 0 :prog 0 })
            ))
(defn primary-atribute-resolver
  "Sets the primary attribute of a hero - and a difficulty"
  [player difficulty] (assoc-in player [:prim] (race-specific-text (player :race) difficulty)))

(defn landing "The first page" [] (html5 [:html
                                         [:head
                                          [:title "ClojuRPG"]
                                          (include-css "resources/style.css")
                                          ]
                                         [:body 
                                          [:div#main-headline {:align "center"}(wrap-element "h1" localisation-txt/welcome-headline)]
                                          [:div#welcome-text (wrap-element "p" localisation-txt/welcome-text)]
                                          [:div#go-form {:align "center"} (form-to [:post "/begin"] (label {:style "margin: 0 10px 0 0;"} "name-pick" "Pick your hero's name")  (text-field {:placeholder "Player name"} "hero-name") [:br] [:br] (submit-button localisation-txt/begin-game))]
                                         ]
                                        ]))
(defn character-creation "Where the hero is made" [player] (html5 [:html
                                         [:head
                                          [:title "ClojuRPG"]
                                          (include-css "/style.css")
                                          ]
                                         [:body 
                                          [:div#main-headline {:align "center"} (wrap-element "h1" localisation-txt/race-choosing-headline)]
                                          [:div#storyline-text (wrap-element "p" localisation-txt/race-choosing-text)]
                                          [:div#choose-race 
                                           [:div#human-choose {:style "float: left; margin: 10px;"}
                                            [:div#human-container {:align "center" :style "width: 250px;"}
                                             (form-to [:post "/begin"] (hidden-field "player"  (class-resolver "Human" player)) [:br] (submit-button localisation-txt/choose-human))
                                             [:br] (wrap-element "p" localisation-txt/choose-human-text)
                                             [:br] (wrap-element "p" localisation-txt/choose-human-attributes)
                                            ]
                                           ]
	                                         [:div#elf-choose {:style "float: left; margin: 10px;"}
	                                           [:div#elf-container {:align "center" :style "width: 250px;"}  
	                                            (form-to [:post "/begin"] (hidden-field "player" (class-resolver "Elf" player)) [:br] (submit-button localisation-txt/choose-elf))
		                                          [:br] (wrap-element "p" localisation-txt/choose-elf-text)
	                                            [:br] (wrap-element "p" localisation-txt/choose-elf-attributes)
	                                           ]
                                           ]
                                          [:div#orc-choose {:style "float: left; margin: 10px;"}
                                           [:div#orc-container {:align "center" :style "width: 250px;"} 
	                                          (form-to [:post "/begin"] (hidden-field "player" (class-resolver "Orc" player)) [:br] (submit-button localisation-txt/choose-orc))
                                            [:br] (wrap-element "p" localisation-txt/choose-orc-text)
	                                          [:br] (wrap-element "p" localisation-txt/choose-orc-attributes)
	                                         ]
                                          ]
                                          ]
                                         ]
                                        ]))
(defn difficulty-selection "Select primary attribute" [player] (html5 [:html
                                         [:head
                                          [:title "ClojuRPG"]
                                          (include-css "resources/style.css")
                                          ]
                                         [:body 
                                          [:div#main-headline {:align "center"} (wrap-element "h1" (race-specific-text (player :race) "difficulty-headline"))]
                                          [:div#storyline-text  (wrap-element "p" (add-hero-name (player :name) (race-specific-text (player :race) "difficulty-text")))]
                                          [:div#choose-difficulty 
                                           [:div#easy-choose {:style "float: left; margin: 10px;"}
                                            [:div#easy-container {:align "center" :style "width: 250px;"}
                                             (form-to [:post "/intro"] (hidden-field "player" (primary-atribute-resolver player "difficulty-easy")) [:br] (submit-button (race-specific-text (player :race) "difficulty-easy-character")))
                                             [:br] (wrap-element "p" "Easy")
                                             [:br] (wrap-element "p" (race-specific-text (player :race) "difficulty-easy-description"))
                                            ]
                                           ]
	                                         [:div#medium-choose {:style "float: left; margin: 10px;"}
	                                           [:div#medium-container {:align "center" :style "width: 250px;"}  
	                                            (form-to [:post "/intro"] (hidden-field "player" (primary-atribute-resolver player "difficulty-medium")) [:br] (submit-button (race-specific-text (player :race) "difficulty-medium-character")))
		                                          [:br] (wrap-element "p" "Medium")
	                                            [:br] (wrap-element "p" (race-specific-text (player :race) "difficulty-medium-description"))
	                                           ]
                                           ]
	                                         [:div#hard-choose {:style "float: left; margin: 10px;"}
	                                          [:div#hard-container {:align "center" :style "width: 250px;"} 
		                                         (form-to [:post "/intro"] (hidden-field "player" (primary-atribute-resolver player "difficulty-hard")) [:br] (submit-button (race-specific-text (player :race) "difficulty-hard-character")))
	                                           [:br] (wrap-element "p" "Hard")
		                                         [:br] (wrap-element "p" (race-specific-text (player :race) "difficulty-hard-description"))
	                                          ]
	                                         ]
                                          ]
                                         ]
                                        ]))
(defn heros-history "A short history of the hero, before the main menu" [player] (html5 [:html
												                [:head
												                 [:title "ClojuRPG"]
												                 (include-css "resources/style.css")
												                 ]
												                [:body 
												                 [:div#main-headline {:align "center"} (wrap-element "h1" (race-specific-text (player :race) "history-headline"))]
												                 [:div#storyline-text (wrap-element "p" (add-hero-name (player :name) (race-specific-text (player :race) "history-text")))]
												                 [:div#continue-form {:align "center"} (form-to [:post "/game"]  (hidden-field "player" player) [:br] (submit-button "Enter the city"))]
												                 ]
												                ]))
(defn city-ambient "Main menu - city ambient" [player city day] (html5 [:html
												                [:head
												                 [:title (str "ClojuRPG" " - " city)]
												                 (include-css "resources/style.css")
												                 ]
						                            [:body 
			                                   [:div#main-headline {:align "center"} (wrap-element "h1" city) [:br] (wrap-element "p" (str "Day - " day))]
                                         [:div#talk-people-form {:align "center"} (form-to [:post ""]  (hidden-field "talk" "People") (hidden-field "player" player) (hidden-field "day" day) [:br] (submit-button "Talk to the locals"))]
																		     [:div#talk-elders-form {:align "center"} (form-to [:post ""]  (hidden-field "talk" "Elders") (hidden-field "player" player) (hidden-field "day" day) [:br] (submit-button (str "Talk to the elders of " city)))]
																		     [:div#explore-forest-form {:align "center"} (form-to [:post ""]  (hidden-field "explore" "Forest") (hidden-field "player" player) (hidden-field "day" day) [:br] (submit-button "Explore the surrounding forests"))]
																		     [:div#rest-form {:align "center"} (form-to [:post ""]  (hidden-field "rest" "Rest") (hidden-field "player" player) (hidden-field "day" day) [:br] (submit-button "Rest for today"))]
						                            ]
												                ]))

(defn debug "U igru" [id] (html5 [:html
                                         [:head
                                          [:title "ClojuRPG"]
                                          (hiccup.page/include-css "/style.css")
                                          ]
                                         [:body 
                                          [:div#main-headline.h1 (wrap-element "h1" id)]
                                         ]
                                        ]))