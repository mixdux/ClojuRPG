(ns ClojuRPG.pages 
  (:require [clojure.repl :refer :all]
            [hiccup.page :refer :all]
            [hiccup.element :refer :all]
            [hiccup.form :refer :all]
            [ClojuRPG.localisation :as localisation-txt]
            [clojure.math.numeric-tower :as math]
            [ClojuRPG.util :as util]
            [ClojuRPG.hero-util :as hero-util]
            [ClojuRPG.progress-util :as progress-util]
            [ClojuRPG.monster-util :as monster-util]
            [ClojuRPG.fight-util :as fight-util]))

(defn landing
"The first page"
[] (html5 [:html
           [:head
            [:title "ClojuRPG"]
            (include-css "resources/style.css")]
           [:body
            [:div#main-headline {:align "center"} (util/wrap-element "h1" localisation-txt/welcome-headline)]
            [:div#welcome-text (util/wrap-element "p" localisation-txt/welcome-text)]
            [:div#go-form {:align "center"}
             (form-to [:post "/begin"]
                      (label {:style "margin: 0 10px 0 0;"} "name-pick" "Pick your hero's name")
                      (text-field {:placeholder "Player name"} "hero-name")
                      [:br] [:br] (submit-button localisation-txt/begin-game))]]]))

(defn character-creation
  "Where the hero is made"
  [player] (html5 [:html
                   [:head
                    [:title "ClojuRPG"]
                    (include-css "/style.css")]
                   [:body
                    [:div#main-headline {:align "center"} (util/wrap-element "h1" localisation-txt/race-choosing-headline)]
                    [:div#storyline-text (util/wrap-element "p" localisation-txt/race-choosing-text)]
                    [:div#choose-race 
                     [:div#human-choose {:style "float: left; margin: 10px;"}
                      [:div#human-container {:align "center" :style "width: 250px;"}
                       (form-to [:post "/begin"]
                                (hidden-field "player" (hero-util/class-resolver "Human" player)) 
                                [:br] (submit-button localisation-txt/choose-human))
                       [:br] (util/wrap-element "p" localisation-txt/choose-human-text)
                       [:br] (util/wrap-element "p" localisation-txt/choose-human-attributes)]]
                     [:div#elf-choose {:style "float: left; margin: 10px;"}
                      [:div#elf-container {:align "center" :style "width: 250px;"}  
                       (form-to [:post "/begin"]
                                (hidden-field "player" (hero-util/class-resolver "Elf" player))
                                [:br] (submit-button localisation-txt/choose-elf))
                       [:br] (util/wrap-element "p" localisation-txt/choose-elf-text)
                       [:br] (util/wrap-element "p" localisation-txt/choose-elf-attributes)]]
                     [:div#orc-choose {:style "float: left; margin: 10px;"}
                      [:div#orc-container {:align "center" :style "width: 250px;"}
                       (form-to [:post "/begin"]
                                (hidden-field "player" (hero-util/class-resolver "Orc" player))
                                [:br] (submit-button localisation-txt/choose-orc)) 
                       [:br] (util/wrap-element "p" localisation-txt/choose-orc-text) 
                       [:br](util/wrap-element "p" localisation-txt/choose-orc-attributes)]]]]]))

(defn difficulty-selection
  "Select primary attribute"
  [player] (html5 [:html
                   [:head
                    [:title "ClojuRPG"]
                    (include-css "resources/style.css")]
                   [:body 
                    [:div#main-headline {:align "center"} (util/wrap-element "h1" (util/prefix-specific-text (player :race) "difficulty-headline"))]
                    [:div#storyline-text  (util/wrap-element "p" (util/add-hero-name (player :name) (util/prefix-specific-text (player :race) "difficulty-text")))]
                    [:div#choose-difficulty 
                     [:div#easy-choose {:style "float: left; margin: 10px;"}
                      [:div#easy-container {:align "center" :style "width: 250px;"}
                       (form-to [:post "/intro"] 
                                (hidden-field "player" (hero-util/primary-atribute-resolver player "difficulty-easy")) 
                                [:br] (submit-button (util/prefix-specific-text (player :race) "difficulty-easy-character"))) 
                       [:br] (util/wrap-element "p" localisation-txt/smallest-difficulty)
                       [:br] (util/wrap-element "p" (util/prefix-specific-text (player :race) "difficulty-easy-description"))]]
                     [:div#medium-choose {:style "float: left; margin: 10px;"}
                      [:div#medium-container {:align "center" :style "width: 250px;"}  
                       (form-to [:post "/intro"] 
                                (hidden-field "player" (hero-util/primary-atribute-resolver player "difficulty-medium"))
                                [:br] (submit-button (util/prefix-specific-text (player :race) "difficulty-medium-character")))
                       [:br] (util/wrap-element "p" localisation-txt/medium-difficulty) 
                       [:br] (util/wrap-element "p" (util/prefix-specific-text (player :race) "difficulty-medium-description"))]]
                     [:div#hard-choose {:style "float: left; margin: 10px;"}
                      [:div#hard-container {:align "center" :style "width: 250px;"} 
                       (form-to [:post "/intro"]
                                (hidden-field "player" (hero-util/primary-atribute-resolver player "difficulty-hard")) 
                                [:br] (submit-button (util/prefix-specific-text (player :race) "difficulty-hard-character")))
                       [:br] (util/wrap-element "p" localisation-txt/greatest-difficulty)
                       [:br] (util/wrap-element "p" (util/prefix-specific-text (player :race) "difficulty-hard-description"))]]]]]))

(defn heros-history
  "A short history of the hero, before the main menu"
  [player] (html5 [:html
                   [:head
                    [:title "ClojuRPG"]
                    (include-css "resources/style.css")]
                   [:body 
                    [:div#main-headline {:align "center"} (util/wrap-element "h1" (util/prefix-specific-text (player :race) "history-headline"))]
                    [:div#storyline-text (util/wrap-element "p" (util/add-hero-name (player :name) (util/prefix-specific-text (player :race) "history-text")))]
                    [:div#continue-form {:align "center"} (form-to [:post "/game"] 
                                                                   (hidden-field "player" player)
                                                                   [:br] (submit-button "Enter the city"))]]]))



(defn city-ambient 
  "Main menu - city ambient" 
  [player city day] (let [player-lvl (hero-util/level-resolver #(math/sqrt %) (:xp player))]
                      (html5 [:html
                              [:head
                               [:title (str "ClojuRPG" " - " city)]
                               (include-css "resources/style.css")]
                              [:body 
	                             [:div#main-headline {:align "center"} (util/wrap-element "h1" city) [:br] (util/wrap-element "p"
	                                                                                                                   (str "Day " day
	                                                                                                                        " - progress " (:prog player)))]
	                             [:div#talk-people-form {:align "center"} (form-to [:post "/action"]
	                                                                                  (hidden-field "action" "people") 
	                                                                                  (hidden-field "player" player)
	                                                                                  (hidden-field "increase" 
	                                                                                                (progress-util/progres-checker player
	                                                                                                              [1 3]
	                                                                                                              []
	                                                                                                              []
	                                                                                                              []
	                                                                                                              day
	                                                                                                              player-lvl))
	                                                                                  (hidden-field "day" day) 
	                                                                                   [:br] (submit-button (util/suffix-specific-text "talk-to-the-locals" (:prog player))))]
	                             [:div#talk-elders-form {:align "center"} (form-to [:post "/action"]
	                                                                                  (hidden-field "action" "elders")
	                                                                                  (hidden-field "player" player) 
	                                                                                  (hidden-field "increase"
	                                                                                                (progress-util/progres-checker player
	                                                                                                              [2]
	                                                                                                              []
	                                                                                                              []
	                                                                                                              []
	                                                                                                              day
	                                                                                                              player-lvl))
	                                                                                  (hidden-field "day" day)
	                                                                                  [:br] (submit-button (util/suffix-specific-text "talk-to-the-elders" (:prog player))))]
	                             [:div#explore-forest-form {:align "center"} (form-to [:post "/action"] 
	                                                                                     (hidden-field "action" "explore")
	                                                                                     (hidden-field "player" player) 
	                                                                                     (hidden-field "increase"
	                                                                                             (progress-util/progres-checker player
	                                                                                                              [4]
	                                                                                                              []
	                                                                                                              []
	                                                                                                              []
	                                                                                                              day
	                                                                                                              player-lvl))
	                                                                                     (hidden-field "day" day)
                                                                                      (hidden-field "redirect" 
                                                                                                    (if-not 
                                                                                                      (progress-util/redirect-prog-check [4] (:prog player)) 
                                                                                                      1 0))
	                                                                                     [:br] (submit-button (util/suffix-specific-text "explore" (:prog player))))]
	                             [:div#travel-form {:align "center"} (form-to [:post "/action"] 
	                                                                             (hidden-field "action" "travel") 
	                                                                             (hidden-field "player" player)
	                                                                             (hidden-field "increase"
	                                                                                             (progress-util/progres-checker player
	                                                                                                              [5]
	                                                                                                              []
	                                                                                                              []
	                                                                                                              []
	                                                                                                              day
	                                                                                                              player-lvl))
	                                                                             (hidden-field "day" day) 
	                                                                             [:br] (submit-button (util/suffix-specific-text "travel" (:prog player))))]
                              [:div#player-info {:align "center"}
                               [:br] [:div#player-info-basic
                                      (util/wrap-element "p" (str 
                                                          (:name player)
                                                          " - "
                                                          (:race player)
                                                          " | xp: "
                                                          (:xp player)
                                                          " | lvl: "
                                                          player-lvl))]
                               [:div#player-info-stats
                                (let [lvl-incr (hero-util/race-stat-level (:race player) (:xp player))]
                                  (util/wrap-element "p" (util/player-name-and-status player lvl-incr)))]
                               [:div#player-info-difficulty
                                 (util/wrap-element "p" (hero-util/difficulty-resolver (:race player) (:prim player)))]]]])))

(defn talk 
  "Where the talkings are handled"
  [player day action increase] (html5 [:html
                              [:head
                               [:title "ClojuRPG"]
                               (hiccup.page/include-css "/style.css")]
                              [:body 
                               [:div#talk-headline-container {:align "center"} (util/wrap-element "h1" (util/suffix-specific-text (str action "-" "headline") (:prog player) ))]
                               [:div#talk-text-container (util/wrap-element "p" (util/suffix-specific-text (str action "-" "text") (:prog player)))]
                               [:div#talk-form {:align "center"} (form-to [:post "/game"] 
                                                                   (hidden-field "player"
                                                                                (if-not (zero? increase)
                                                                                  (update-in player
                                                                                              [:prog] #(+ % increase))
                                                                                  player))
                                                                  (hidden-field "day" day)
                                                                   [:br] (submit-button (util/suffix-specific-text (str action "-" "button") (:prog player))))]]]))

(defn explore
  "Exploring and fighting is handled here"
  [player day city increase] (let [monsters (monster-util/opponent-creator player)
                                   monsters-fought-resaults (fight-util/fight-monsters monsters player)]
                               (html5 [:html
                              [:head
                               [:title "ClojuRPG"]
                               (hiccup.page/include-css "/style.css")]
                              [:body 
                               [:div#explore-headline-container {:align "center"} (util/wrap-element "h1" (util/suffix-specific-text "explore-headline" (:prog player)))]
                               [:div#explore-text-container (util/wrap-element "p" (util/suffix-specific-text "explore-text" (:prog player)))]
                                 (for [resault monsters-fought-resaults]
                                   [:div#explore-resaults (util/wrap-element "p" resault)])
                                 (fight-util/check-lost-fights player monsters monsters-fought-resaults)
                                 (if (= 0 (count monsters))
                                   (util/suffix-specific-text "no-fight-0" (:prog player)))
                                 [:div#talk-form {:align "center"} (form-to [:post "/game"] 
                                                                   (hidden-field "player" (hero-util/player-xp-updater 
                                                                                            player 
                                                                                            (count monsters-fought-resaults)))
                                                                     (hidden-field "day" day)
                                                                   [:br] (submit-button (util/suffix-specific-text "explore-button" (:prog player))))]]])))

(defn debug 
  "Write an element in h1"
  [id] (html5 [:html
               [:head
                [:title "ClojuRPG"]
                (hiccup.page/include-css "/style.css")]
               [:body 
                [:div#main-headline.h1 (util/wrap-element "h1" id)]]]))