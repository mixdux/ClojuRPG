ClojuRPG
========

A browser text RPG, fully written in Clojure!

This is my first big project coded in Clojure and none of the functions used are hard to understand or reproduce. ClojuRPG is designed to be changed to fit everyone's RPG developing needs. From the first lines, it is designed so that it can be easily molded, with minimal code alterations, so that one can make it's own RPG by rewriting text and adding progress marks 
Text
----
There is a separate nemaspace called Localisation and all text is contained there. Should you change it so you create your own RPG, make shure that all the story is contained there (whith the appropriate naming, of course)

Structure
----
It consists of Core, Pages and Localisation main namespaces and several other that serve as a support:
 - Core:
Since this RPG is played inside a internet browser, all routing logic is contained here. Also, is hosts the player struct definition (default - :name :race :str :dex :int :prim :xp :prog), function for player creation and a function that returns the name of the city the player is currently in.
 - Pages:
All screen drawing logic can be found inside this namespace. Only seven functions Are used to create all screens in game; three of them are used to create every Talking screen, City main screen and Explore/Fight monsters screen.
 - Localisation:
All the text is contained here; it's naming shall be discussed later.

Other namespaces serve as a utility:
 - Fight utility:
Contains all necessary logic for auto-resolving a fight between the player and monsters.
 - Hero utility:
Does the work of selecting the class of hero, difficulty (primary attribute) and multiple stat incrementations (for battle and for display). Also has the logic of returning the current level of a player.
 - Monster util:
Fully creates the monsters the player has to fight; makes their class, name and stats.
 - Progress util:
Conteins logic for progress checking and incrementation. Progress (:prog player) is the main tool by which one can go through game. Every text and ambient change is closely connected to the amount of progress variable.
 - Util:
Various utility methods; from getting the right text for current player race, to formatting one as it is required.



