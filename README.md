# Path of Adventure

**Overview**
This program is a text based RPG inspired by the famous game Dungeons & Dragons. It uses inheritance with a super class of 'Character' and subclasses 'PlayableCharacter' and 'NonPlayableCharacter'. The program uses 3 data structure concepts:
1. Hashmap:
The program uses hashtables to store different items the player can use in order to defeat NPCs. The items are also used to build the NPCs' stats. 
Code lines for Hashmap:
- Lines 14 - 36 in Main.java
- Line 133 - 136 in Main.java
or
- Lines 13 - 26 in Test.java
- Lines 129 - 130 in Test.java

2. Queue:
The program uses a linked list to create a queue for the NPCs the player encounters throughout the game. A NPC is returned and dequeued from the head (poll) when the player encounters them. If the player defeats the NPC (turning their HP variable to 0 using attack and spell methods), the program repeats the process.
Code lines for Queue:
- Lines 42 - 51 in Main.java
- Line 242 in Main.java
or 
- Lines 28 - 36 in Test.java
- Line 151 in Test.java

3. Sorting:
The program also uses Java's built-in sort method and 'Comparator' interface to sort hashtables based on values within each object. 
Code lines for Sorting:
- Lines 143 - 150 in Main.java
or 
- Lines 133 - 141 in Test.java

**Test.java**
If you'd like to check for data structure concepts and logic of the game without having to go through all the dialogue, I've all the concepts and manually put attack, defend, and cast spell methods in **Test.java** class to demonstrate the game.

**What does your project do that is interesting and substantive?**
- Players can create unique characters by choosing their name, weapons, tools, and specialization in either strength or wisdom.
- The game has a dynamic combat system where player can attack, defend, and cast spells based on their attributes and equipment.
- The game uses some small variable of chance for how much damage each character deals to the other.

**Why is inheritance useful for your previously specified superclass and subclasses?**
Inheritance is useful in this project because it allows for the creation of specialized players and NPCs while reducing code duplication. The Character superclass contains common attributes and methods shared by both playable characters and non-playable characters. Subclasses PlayableCharacter and NonPlayableCharacter inherit these attributes and methods while having additional customization specific to each type of character.

**For each of the two additional previously-specified class concepts that you used, why is that concept the best to use in your project?**
1. The reason for using hashtables is that figuring out whether the object is available in the table has a time complexity of O(1). This comes in handy when the number of objects is a lot.

2. The reason for using a linked list for enemies’ queue is that you can add more NPCs along the process of the game and the player will encounter them in an order one after the other. Also, the linkedlist’s (First in First out) logic most makes sense for player encountering enemies along the journey.

3. The reason for having a sorting algorithm is to sort every object of the game based on their power and value and **display** them more neatly. That way, the user also has an easier picking them and choosing between items.

**Usage**
To use the program, run the following after downloading the code:

```
$ javac *.java
$ java Main

```
The rest of the entry is directed by the program step by step
Mainly, the words for progressing along the game are inside quotation marks. Type them without space.

or 

```
$ javac *.java
$ java Test

```

**Sources**
The ASCII arts for the game is obtained from (https://www.asciiart.eu/)
I learned the sorting algorithm concept from a YouTube video by 'Telusko' 
titled '#95 Comparator vs Comparable in Java' (https://www.youtube.com/watch?v=ZA2oNhtNk3w)
