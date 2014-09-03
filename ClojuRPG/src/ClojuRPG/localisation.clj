(ns ClojuRPG.localisation)

(def welcome-headline
  "Main headline" 
  "Welcome to the ClojuRPG!")
(def welcome-text
  "The welcome screen text"
  "ClojuRGP is a Role-playing game, fully written in Clojure. Althought not graphically astonishing, it has been developed in a dialect of Lisp; poles apart from Flash, JavaScript or any other massively used language!
<br/><br/>ClojuRPG will mesmerise you with it's story and relaibility - who knows, maybe inspire you to write one by yourself")

(def begin-game 
  "Begin adventure button text"
  "Adventure!")

(def race-choosing-headline 
  "Headline for race chosing screen"
  "Dawn of the hero")
(def race-choosing-text 
  "Introductory text for race chosing screen"
  "That misty night, dense fog had set over Amareia - so dense that a weary traveller had to lit two torches, barely to see his way through a sparkling light could be seen from every point in the land. The only night in the year when all races of Amareia could stand one next to another, for this time, it was increadibly peacefull. Humans, Orcs and Elves were at their lands, lookind towards starless sky, into the only light that could break through.
<br/><br/>Suddenly, it started to grow, becoming even lighter and bluish as it made it's way towards the surface. It became obvious that it won't last much above the surface - impact was imminent! Moments after, a giant pillar of sparkling smoke arouse from the territory of...")

(def choose-human 
  "Button text for choosing the Human race"
  "Humans")
(def choose-human-text
  "Text that describes Human race"
  "Known to be the wisest ones, healers, priests and grand librarians; Humans are the keepers of the knowledge and history of all Amareia")
(def choose-human-attributes 
  "Human atributes" 
  "Strength 3<br/>Dexterity 2<br/>Intelligence 5")

(def choose-elf 
  "Button text for choosing the Elvish race" 
  "Elves")
(def choose-elf-text 
  "Text that describes Elvish race" 
  "Peacefull nation living in woods, that has learned to become one with the nature. Practise meditation and are incredibly agile hunters")
(def choose-elf-attributes
  "Elvish atributes" 
  "Strength 2<br/>Dexterity 5<br/>Intelligence 3")

(def choose-orc
  "Button text for choosing the race of Orcs"
  "Orcs")
(def choose-orc-text 
  "Text that describes Orcish race"
  "True noble people, often confused for their brute bloodline cousins the Hell Orcs. With them they only share enormus strength and body constitution")
(def choose-orc-attributes
  "Orcish atributes" 
  "Strength 5<br/>Dexterity 2<br/>Intelligence 3")

(def smallest-difficulty 
  "How is the smallest difficulty presented" 
  "Easy")
(def medium-difficulty 
  "How is medium difficulty presented" 
  "Medium")
(def greatest-difficulty 
  "How is the greatest difficulty presented" 
  "Hard")

(def human-difficulty-headline 
  "Main headline for Human difficulty selection screen" 
  "A beacon of light")
(def human-difficulty-text 
  "Select difficulty if playing with Humans"
  "Shining object crash landed in the vincinity of the Forebearers temple - the holiest place for all Humman race. Now resting on the highest mountain, an object fallen from the sky lied there like a beacon of light. Neadless to say, both villagers and schollars were baffled by it's manifestation - each for their own reasons. The wisest of them all, three Grand theorists sat together and resoluted that noone must go near it until it is proven safe.
<br/><br/>They soon gathered a handfull of men, most diverse in profession in order to go up the monuntain and examine the \"Holly\" artifact... As so they did; it was a small boulder - there wasn't anything specical about it except for one thing... It had a lateral opening that was just big enough for a hand to fit in. Underneath it, there was a deeply etched text. It red \"~HERO~\".
<br/><br/>They couldn't find a way to study it - because there was nothing to study; just a plain, drilled and etched rock. Also, it was the hardest rock they have ever seen and they couldn't break even a smallest pebble of it for retrieval to the Grand theorists for further studies... Then an idea sprouted - maybe the secret of it lies in the hollowed tube! Nobody was brave enough to try and reach inside. Their torches were long gone so there was no other way of seeing what lies there, but reaching for it. Nobody had the guts; nobody but the...")

(def human-difficulty-easy 
  "Key of the primary attribute for Easy" 
  ":int")
(def human-difficulty-easy-character
  "Occupation of the character for Easy" 
  "wise Alchemist")
(def human-difficulty-easy-description 
  "Backstory about Easy difficulty"
  "Alchemists are the most respected and most powerfull off schollars that ever walked the halls of Forebearers temple. Your future will be as brigth as a platinum reagent!")

(def human-difficulty-medium 
  "Key of the primary attribute for Medium"
  ":str")
(def human-difficulty-medium-character 
  "Occupation of the character for Medium"
  "disciplined Monk")
(def human-difficulty-medium-description 
  "Backstory about Medium difficulty" 
  "Don't let the title fool you, Human monks are one of the toughest figters there are! You will learn the paths of enlightment and inner peace - where you shall drain your power from!")

(def human-difficulty-hard 
  "Key of the primary attribute for Hard"
  ":dex")
(def human-difficulty-hard-character
  "Occupation of the character for Hard"
  "fearless Adventurer")
(def human-difficulty-hard-description 
  "Backstory about hard difficulty" 
  "Humans have naturally inquisitive mind and their light spirit is not easily taimed. Although not the most powerfull Human there will be, you will always be on the move!")

(def elf-difficulty-headline 
  "Main headline for Elvish difficulty selection screen" 
  "Minahrean embracers")
(def elf-difficulty-text
  "Select difficulty if playing with Elves" 
  "Mysterious object fell near a densly populated capital of the elvish lands. Only a small amount of residents dared to abandon the safety of Minahrea - thick brown stone walls, riddled with vines and spruots provided shelter and solace to generations of these pure beings. The wisest of Elves had gathered, and decided to send an expedition towards a landing site. As nobody knew the artifact's origin nor purpose, they decided to send in a diversified team of all professions that Minahrea had.
<br/><br/>The team had been gathered and briefed whitin hours. Truly, only the boldest and nimblest representatives had come - and expedition had set off! Quickly after they came to the mysterious artifact. It was a small boulder - there wasn't anything specical about it except for one thing... It had a lateral opening that was just big enough for a hand to fit in. Underneath it, there was a deeply etched text. It red \"~HERO~\".
<br/><br/>They couldn't find a way to study it - because there was nothing to study; just a plain, drilled and etched rock. Also, it was the hardest rock they have ever seen and they couldn't break even a smallest pebble of it for retrieval to Minahrea for further studies... Then an idea sprouted - maybe the secret of it lies in the hollowed tube! Nobody was brave enough to try and reach inside. Their torches were long gone so there was no other way of seeing what lies there, but reaching for it. Nobody had the guts; nobody but the...")

(def elf-difficulty-easy
  "Key of the primary attribute for Easy"
  ":dex")
(def elf-difficulty-easy-character 
  "Occupation of the character for Easy"
  "swift Ranger")
(def elf-difficulty-easy-description 
  "Backstory about Easy difficulty" 
  "The nimblest of them all, noble Ranger, will take care of you and train you to your fullest! You will be most pleased as his protege/child and live up to your potential!")

(def elf-difficulty-medium 
  "Key of the primary attribute for Medium"
  ":int")
(def elf-difficulty-medium-character 
  "Occupation of the character for Medium"
  "serene Sage")
(def elf-difficulty-medium-description 
  "Backstory about Medium difficulty" 
  "Wise Elvish sage will teach you about the ways of ancient Elvish magic. Although not the most powerful Elvish magic is, you will grow into a fastest spellcaster!")

(def elf-difficulty-hard
  "Key of the primary attribute for Hard"
  ":str")
(def elf-difficulty-hard-character
  "Occupation of the character for Hard" 
  "tenacious Shadewalker")
(def elf-difficulty-hard-description 
  "Backstory about hard difficulty" 
  "Highly unpopular among Elves, Shadewalkers, are the ones that decided to give up their speed and agility in exchange for brute strength. You shall not be the strongest Elf, but you will be raised as one!")

(def orc-difficulty-headline
  "Main headline for Orcish difficulty selection screen"
  "Pedestal of brilliance and sand")
(def orc-difficulty-text
  "Select difficulty if playing with Orcs" 
  "Unknown object broke the skies and fell in the middle of a Tharak plane. A long forgotten sacred place for all of Orc, as there once stood a Pillar of forgivness, where two brother races swore on truce. Several wachtowers spotted a strange phenomenon and quickly informed the high council. Orcs were ready to respond and had sent a well prepared expedeiton towards the hart of the just-formed crater.
<br/><br/>Fearlessly they started do decend along a side of it, barely noticing the upcomming sandstorm in the distance. Deep down, it had fused with a sand and formed a symbollical pillar of it own - a pedestal of brilliance and sand. It was a small boulder - there wasn't anything specical about it except for one thing... It had a lateral opening that was just big enough for a hand to fit in. Underneath it, there was a deeply etched text. It red \"~HERO~\".
<br/><br/>They couldn't find a way to study it - because there was nothing to study; just a plain, drilled and etched rock. Also, it was the hardest rock they have ever seen and they couldn't break even a smallest pebble of it for retrieval to Minahrea for further studies... Then an idea sprouted - maybe the secret of it lies in the hollowed tube! Nobody was brave enough to try and reach inside. Their torches were long gone so there was no other way of seeing what lies there, but reaching for it. Nobody had the guts; nobody but the...")

(def orc-difficulty-easy 
  "Key of the primary attribute for Easy" 
  ":str")
(def orc-difficulty-easy-character
  "Occupation of the character for Easy"
  "forcefull Duelist")
(def orc-difficulty-easy-description
  "Backstory about Easy difficulty" 
  "Widely known by it's grand body constitution, Orc Duelists are the most wild of all warriors. Pure in hart, with grat strength and vigilance, you shall be a true defender of peace!")

(def orc-difficulty-medium 
  "Key of the primary attribute for Medium" 
  ":int")
(def orc-difficulty-medium-character 
  "Occupation of the character for Medium"
  "mighty Wizard")
(def orc-difficulty-medium-description
  "Backstory about Medium difficulty"
  "Orcish magic is well known among those that dared to underestimate it. Most powerfull curses and forbidden unholy spells shall be yours to cast in the righteous cause!")

(def orc-difficulty-hard 
  "Key of the primary attribute for Hard"
  ":dex")
(def orc-difficulty-hard-character
  "Occupation of the character for Hard"
  "fierce Bounty Hunter")
(def orc-difficulty-hard-description
  "Backstory about hard difficulty"
  "Noone is proud in the land of Orc of what these savages undertake. Infamous assasins that follow no code, except their own. Still, you will grow into a formidable opponent!")

(def human-history-headline
  "Headline of the Human player history" 
  "Newborn of light")
(def human-history-text
  "Human history text"
  "The momment bravest of all men slid the hand in, the boulder started to crack and everyone backed away. The beacon was now shining with even more energy and it became obvious that it won't las long. They all hid behind rough mountain face and covered their ears; loud, high pitched sound originated from now glowing sphere, that woke up every village beneath this holy mountain. For a second, it all stopped. Like a moment in time had frosen, no sound could be heared, no nore ligt beams shoot out of the glowing sphere.
<br/><br/>And then it collapsed - the sphere broke down into millions of pieces of light particles, leaving underneath what was once a boulder, glittering mixture of rock, dust and light. On it, there lied a beautiful Human baby. Baby carrying a glittering tiara with ancient runes transcribed on it. As our heroes proceeded to it, it started to cry - like a through newborn. Inside it's tiara, your later name, ~HERO~ was written.
<br/><br/>The one that put the hand into hollowed rock tube soon accepted you as a part of the family. You were tought tools of the trade, like you would once become what he was. And you had - you became your parent's pride and joy! Everyone knew how good you continued the family profession and everyone was proud of you! From the beginning you were destined to do grat things.
<br/><br/>Then came the famous year when every Human child had to go on it's way of destiny. You decided it would best to meet all the neighbouring races and started preparation for the city of Aniracg - on the borderlands of all three lands. Your parents prepared a feast in your name and by the dusk next day, you were on your way. It took you three days by horse to each it... And there you were - the city of Aniracg!")

(def elf-history-headline
  "Headline of the Elvish player history"
  "The band of grass")
(def elf-history-text 
  "Elvish history text" 
  "As soon as the brave Elf had slid his hand to try and find something inside the hollowed rock face, ligh started to emerge from the artifact, ripping the fog like an eagle through the sky. Elves quickly found shelter and covered their eyes because lightbeams were tearing through bolder and going in all directions. They quickly turned and started signaling to their guards on the walls to alert everyone, as this soon became a possible threat to all of Minahrea!
<br/><br/>Abruptly, the lights diminished and the fog appeared thicker than ever. But that were just theit eyes unadapted to the nigh due to massive amounts of luminoucity that had hit them. The bravest of them approached carefullt to that which was one a boulder. Now there lied a beautiful Elvish baby with a grass band around it's ankle. Among ancient runes, one could make out a name. It was ~HERO~.
<br/><br/>The one that put the hand into hollowed rock tube soon accepted you as a part of the family. You were tought tools of the trade, like you would once become what he was. And you had - you became your parent's pride and joy! Everyone knew how good you continued the family profession and everyone was proud of you! From the beginning you were destined to do grat things.
<br/><br/>Then came the famous year when every Elven child had to go on it's way of destiny. You decided it would best to meet all the neighbouring races and started preparation for the city of Aniracg - on the borderlands of all three lands. Your parents prepared a feast in your name and by the dusk next day, you were on your way. It took you one day to each it... And there you were - the city of Aniracg!")

(def orc-history-headline 
  "Headline of the Orcish player history" 
  "A baby in the arms")
(def orc-history-text
  "Orcish history text" 
  "The bravest of them all valiantly put his hand into the lateral opening and light started to emit, all arond the bouldery-object. Alas, this was nothing for the strong orc, as he and has expedition started to inspect it. Light soon turned into bits and they covered their mouths and noses, by reflex of course, because masks were already protecting them from the sand. Seconds after, a blindingly strong eruption of light happened!
<br/><br/>Everyone looked away from the object, only to hear a baby crying! An Orc baby, lied in the arm of the brave one that had put his hand on stake! Unprotected from the hostile atmosphere, baby Orc just had a Stone wristband. With the sandy winds, brave one could only see the name erched on it, your name. ~HERO~, it soon resonated the crater's bed and it was time to leave.
<br/><br/>The one that put the hand into hollowed rock tube soon accepted you as a part of the family. You were tought tools of the trade, like you would once become what he was. And you had - you became your parent's pride and joy! Everyone knew how good you continued the family profession and everyone was proud of you! From the beginning you were destined to do grat things.
<br/><br/>Then came the famous year when every Orcidh child had to go on it's way of destiny. You decided it would best to meet all the neighbouring races and started preparation for the city of Aniracg - on the borderlands of all three lands. Your parents prepared a feast in your name and by the dusk next day, you were on your way. It took you three days to each it... And there you were - the city of Aniracg!")

(def talk-to-the-locals-1
  "What will be written onto first button of the city"
  "Enter the local hall")
(def talk-to-the-locals-2
  "What will be written onto first button of the city"
  "Talk to the locals")
(def talk-to-the-elders-1
  "What will be written onto second button of the city"
  "The city walls")
(def talk-to-the-elders-3
  "What will be written onto second button of the city"
  "Talk to the Elders of Aniracg")
(def explore-1
  "What will be written onto third button of the city"
  "Explore the surrounding forests")
(def travel-1
  "What will be written onto fourth button of the city"
  "Travel further")

(def strength-0
  "How shall player's strength be described
   (below city's choices)"
  "Str: ")
(def dexterity-0
  "How shall player's dexterity be described
   (below city's choices)"
  "Dex: ")
(def inteligence-0
  "How shall player's inteligence be described
   (below city's choices)"
  "Int: ")

(def explore-headline-0
  "Headline for the explore screen" 
  "The forests of Aniracg")
(def explore-text-0
  "Text of the explore screen" 
  "As you decide to go out of the city to search for adventure, you managed to find your way to the forest.
   <br/><br/>Real adventure awaited for you there!")
(def explore-button-0
  "Text for the explore button" 
  "Back to city")

(def creature-power-0-0
  "Lowest power creature attribute 1"
  "weak")
(def creature-power-1-0
  "Lowest power creature attribute 2"
  "feeble")
(def creature-power-2-0
  "Lowest power creature attribute 3"
  "unsteady")

(def fight-won-1-0-0
  "The first part of the won fight message"
  "You encountered a ")
(def fight-won-2-0-0
  "The second part of the won fight message"
  " and defeated it!")
(def fight-lost-1-0-0
  "The first part of the lost fight message"
  "Alas, you encountered a ")
(def fight-lost-2-0-0
  "The second part of the lost fight message"
  " and it was too strong for you... You were defeated and had to return home.")
(def no-fight-0-0
  "What shall be written if there were no encounters"
  "This time, you found no worty adversaries in the forest, the Sun has begun set and you decide it would be the best not to spend the night in the open.")
(def fight-stat-1-0-0
  "How shall your stats be described"
  "<br>You: ")
(def fight-stat-2-0-0
  "How shall your attacker's stats be described"
  " | It: ")

(def people-headline-1 
  "Headline for the locals talk" 
  "Barina and headhunters")
(def people-text-1
  "What the locals will tell you on your first encounter with them"
  "You have entered the city without knowing anyone there. You have long found a place to sleep and have decided to start meeting people.
<br/><br/>One night, there was a social gathering at the local hall. Entering there, you imideatly see an old woman sitting alone in the corner - and decide to go and talk to her.
<br/><br/>You have presented yourself, but had not told her your complete history - who knows, maybe she would't take you so kindly knowing that you are actually not from this world!
<br/>Barina was her name and she lives in a house just near the hall. While everypne was dancing and drinking, you were chatting and you have found out that many roge warriors and headhunters roam the forests of Arniacg. She gave you instructions on how to find them and do the city a great favour by disposing of them!
<br/><br/>Now, you are ready to go and explore the forests!")
(def people-button-1 
  "What will write on the button to return to the city" 
  "Exit the hall and get prepared")

(def elders-headline-1 
  "Headline for the elders talk" 
  "The city walls")
(def elders-text-1
  "What the elders will tell you on your first encounter with them"
  "Strange stone sculture circles the city of Arniacg. Some say that those were the remainings of war bunker that once was in the city's face; others that it preceeded the war, dating all the way back to the early days of the city's history... You noticed it on your every return from the forest, and by now realised that this wall streches far more longer than one city egde.
<br/><br/>But at he momment, you don't have time to waste and look for where that stone defender stars, right now, you have to meet the people and get along with the soul of the city.")
(def elders-button-1 
  "What will write on the button to return to the city" 
  "Return to the city center")

(def travel-headline-1
  "Headline for the travel story" 
  "The outskirts of Arniacg")
(def travel-text-1
  "What shall be written on the first travel screen"
  "As you made your way towards the outskirts of the city, a strange feeling was overwhelming you. You felt uncomfortable to go alone somewhere further than the forests. You knew that you need a artner - someone who will aid you on your journey!")
(def travel-button-1 
  "What shall be written on the first travel screen button"
  "Continue the search for partner")

(def people-headline-2 
  "Headline for the locals talk, after 0 +1 progress" 
  "A peacefull town")
(def people-text-2
  "What the locals will tell you after your first encounter with them"
  "At the peacefull town of Arniacg, nothing worthy of your attention was going on.
<br/><br/>Except occasional bar brawl, only the infamous headhunters were threatning the locals. That is, the ones with money for their head.
<br/>As this is a border city, you are not the only adventurer that seeks his thrill here. Other and more experienced than you are also roaming around. Some of them sought the glory on dark places and therefore justice awaits them... you know that they have not deserved the headhunter's dagger. Some of them might serve as a good muscle on your path, so you decide to look for them.
<br/><br/>You go to the local Town hall to enquire for them, but noone is willing to serve you. They say that you can't do that without the permission of the city Elders.
<br/><br/>All they told you is to follow the wall to it's origin!")
(def people-button-2 
  "What will write on the button to return to the city, after 0 +1 progress" 
  "Exit the Town hall")

(def elders-headline-2 
  "Headline for the elders talk" 
  "The walls of stone")
(def elders-text-2
  "What the elders will tell you on your first encounter with them"
  "You later found out that the City Elders reside on the very city borders, far away from all the noise that the center has to offer.
<br/><br/>It took you more than an hour to walk to their residence; follow the city wall to it's origin - a building of stone and led. Once a glorious city, Aniracg was ruled by a noble family. A family that watched over it and did everything needed to keep order in this tri-border town. Now it's position became clear to you; It was placed on the very edges so one could see all of Arniacg withy just one glance from it's room.
<br/>It's walls were made of solid Elvish stone, put together by Human magic and fixed into place with Orkish led, poured along it's junctions with one another. You were generously welcomed in by the gate keepers - Town Hall clerks have already them you were coming.
<br/><br/>After a generous meal, all three of the town Elders have understood your story of origin and have agreed to let you search for your companion. The only thing you need now is an advice from local poeple of where one could be found; since the Town Hall doesn't actually keep records of adventurers. They are, as they say, too many and too egar lo leave, whatever that meaned.")
(def elders-button-2 
  "What will write on the button to return to the city" 
  "Exit the Elder's castle")

(def elders-headline-3 
  "Headline for the elders talk, after 2 +1 progress" 
  "Silent walls of stone")
(def elders-text-3
  "What the elders will tell you after your first encounter with them"
  "You decided to and talk to these wise people once more; however the gate's bridge had been raised and noone could get in.
<br/><br/>Guard on the gate told you they had retreated to the castle lower levels to discuss the city politics.")
(def elders-button-3 
  "What will write on the button to return to the city, after 2 +1 progress" 
  "Go back to the City")

(def people-headline-3 
  "Headline for the locals talk" 
  "A camp by the river")
(def people-text-3
  "What the locals will tell you on your encounter with them"
  "So you have now went back to the rural jungle of the city's center and begun asking around if anyone is ready to join on you on your adventure.
<br/><br/>Sadly, nobody was ready to join you, as nobody knew anything about you. You were for them just another adventurer, thirsty of money or fame, that will soon be leaving the town and noone cared as much.
<br/>But then, you stumbled upon a cloaked stranger. He had a big hat and offered you help. He told you that there is a small adventurers camp alongside the river; deep into the forest.
<br/>He also told you to go look for him when you next come to Arniacg and to be carefull on your way through the forest... Many creatures lie in there!")
(def people-button-3 
  "What will write on the button to return to the city" 
  "Prepare for the path to forest")

(def people-headline-4 
  "Headline for the locals talk, after 3 +1 progress" 
  "The city square")
(def people-text-4
  "What the locals will tell you on your encounter with them, after 3 +1 progress"
  "Since the caped stranger encounter, you started to carefully examine every person that goes your way. Paranoic? Maybe, but why would someone just approach you and help; without lettong you know who he is or why is he doing it?
<br/><br/>Nevertheless, other people were generally nice, except the fact that they didn't pay that many attention to adventurers; after all, they are just \"too many and too egar lo leave\".")
(def people-button-4 
  "What will write on the button to return to the city" 
  "Return to your cabin")

(def redirect-explore-headline-4 
  "Headline for the forest talk" 
  "The grassy hopper")
(def redirect-explore-text-4
  "What dialogue shall you make during your first visit to the forest"
  "So you noted the stranger's tip and carefully entered the dark foresty of Arniacg. Everything went the same as usual, you followed the path you always do.
<br/><br/>But this time, you decided to take the shortcut to the river! Half way there, you heared that something was rumbling behind a big rock. As you came closer to it, a thin figurine blocked what was left of the Sun as it rayed it's way through the leaves.
<br/>The creature landed just in front of you and you stepped back, trying not to fall down. It was an Elven Ranger! It looked like royal spy, just with armour a little too big for it's constitution. \"Grassy Hopper\", he said introducing himself to you as he extended his arm to greet you.
<br/><br/>After seeing noone was going to harm anyone, you strolled down the forest, back to he city. After a quick introduction from both sides, you realised that he also is a adventure-seeking thing like yourself! You both agreed to go as duo on further adventures and decided to get prepared as soon as possible!
<br/><br/>Oddly, that was not a problem, because someone already packed your stuff. Lying on bed beside them was a piece of paper with some kind of rose drawn on it.")
(def redirect-explore-button-4 
  "What will write on the button to return to the city" 
  "Pick the stuff up and get prepared")

(def travel-headline-5 
  "Headline for the travel story" 
  "Leaving Aniarcg behind")
(def travel-text-5
  "What shall be written on the first travel screen"
  "Grassy and you have set out to find a new place for your journey. From now on, you won't be alone in yout journeys - the dynamic duo as you are now has only begun to make it's place in the history of Amareia!
<br/><br/>Go forth young adventurer; the history awaits!")
(def travel-button-5 
  "What shall be written on the first travel screen button"
  "Adventure!")

(def talk-to-the-elders-3
  "What will be written onto second button of the city"
  "by Milan Đorić")
