import java.util.Scanner;
import java.util.Hashtable;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    //Define the hashtables for storing objects in the game
    private static Hashtable<String, Integer> barrack = new Hashtable<>();
    private static Hashtable<String, Integer> brewery = new Hashtable<>();
    
    private static void populateBarrack() {
        //Populate the barrack hashtable with items for the game
        barrack.put("longsword", 20);
        barrack.put("rusty longsword", 7);
        barrack.put("short sword", 15);
        barrack.put("club", 8);
        barrack.put("double-edged axe", 18);
        barrack.put("oak shield", -8);
        barrack.put("metal shield", -10);
        barrack.put("leather shield", -7);
        barrack.put("metal armor", -20);
        barrack.put("rusty metal armor", -15);
        barrack.put("leather jacket", -12);
        barrack.put("knife", 3);
        barrack.put("mace", 16);
        barrack.put("spear", 18);
    }

    private static void populateBrewery() {
        //Populate the brewery hashtable with spells 
        brewery.put("healing potion", 100);
        brewery.put("fire glass", 80);
    }

    /**
     * Initialize the queue with enemy NPCs, write their stats using barrack's objects, and add each NPC to the queue
     * @return  a linkedList queue of NPCs
     */
    private static Queue<NonPlayableCharacter> initializeEnemyQueue() {
        Queue<NonPlayableCharacter> enemyQueue = new LinkedList<>();
        NonPlayableCharacter deathKnight = new NonPlayableCharacter("Death Knight", 150, barrack.get("rusty longsword"), 0, barrack.get("rusty metal armor"), "Human");
        NonPlayableCharacter braknar = new NonPlayableCharacter("Braknar the Savage", 250, barrack.get("mace"), 0, 0, "Ogre");
        NonPlayableCharacter ulfgar = new NonPlayableCharacter("Ulfgar the Mighty", 350, barrack.get("spear"), barrack.get("oak shield"), barrack.get("leather jacket"), "Giant");
        enemyQueue.add(deathKnight);
        enemyQueue.add(braknar);
        enemyQueue.add(ulfgar);
        return enemyQueue;
    }
    
    /**
     * Attack sequence when 'attack' action for player is envoked
     * @param player    the player that's envoking the attack
     * @param enemy     the NPC that receives the damage and attacks back
     */
    private static void attack(NonPlayableCharacter enemy, PlayableCharacter player){
        Integer playerDamage = player.attack();
                    System.out.println();
                    System.out.println("You attack " + enemy.getName());
                    //Enemy asborbs the damage
                    enemy.takeDamage(playerDamage);
                    if(enemy.getHealthP() > 0) { //If the enemy is still alive, it will attack back
                        System.out.println(enemy.getName() + " is attacking.");
                        Integer npcDamage = enemy.attack();
                        //Player absorbs the retaliated attack
                        Integer remainingHealth = player.takeDamage(npcDamage);
                        //Printing stats
                        System.out.println("You absorbed " + npcDamage + " points of damage wearing armor                         Health: " + remainingHealth);
                    }
                    System.out.println("                                                                " + enemy.getName() + "'s health: " + enemy.getHealthP());
    }

    /**
     * Spell cast sequence when 'cast spell' action for player is envoked
     * @param player    the player that's envoking the spell cast
     * @param enemy     the NPC that receives the damage and attacks back
     * @param scanner   text scanner utility
     * @param entry     a different action in case player's potions (this.potionCount) run out
     */
    private static void castSpell(PlayableCharacter player, NonPlayableCharacter enemy, Scanner scanner, String entry) {
        Integer remainingSpell = player.getPotionCount();
        //Check if player has potions left to use
        if(remainingSpell <= 0) {
            System.out.println("You've ran out of potions, either 'attack' or 'defend'");
            entry = scanner.nextLine().toLowerCase();
        }
        else if(player.getSpell().equals("healing potion")) {
            //Player heals
            player.castSpell();
            System.out.println("You use the healing potion");
            System.out.println();
            System.out.println(enemy.getName() + " is attacking.");
            //Enemy attacks after the heal action
            Integer npcDamage = enemy.attack();
            Integer remainingHealth = player.takeDamage(npcDamage);
            System.out.println("You absorbed " + npcDamage + " points of damage                                           Health: " + remainingHealth);
        }
        else if(player.getSpell().equals("fire glass")){
            //Player deals damage with spell
            Integer spellDamage = player.castSpell();
            System.out.println("You cast a spell on " + enemy.getName());
            enemy.takeSpellDamage(spellDamage);
            System.out.println();
            System.out.println(enemy.getName() + " is attacking.");
            //Enemy retaliates
            Integer npcDamage = enemy.attack();
            Integer remainingHealth = player.takeDamage(npcDamage);
            System.out.println("You absorbed " + npcDamage + " points of damage                                           Health: " + remainingHealth);
            System.out.println("                                                                " + enemy.getName() + "'s health: " + enemy.getHealthP()); 
        }
    }

    /**
     * Defend sequence when 'defend' action for player is envoked
     * @param player    the player that defends an enemy attack
     * @param enemy     the NPC that envokes the attack method
     */
    private static void defend(PlayableCharacter player, NonPlayableCharacter enemy) {
        //Defend method for player is called
        Integer damageWithDefend = player.defend(enemy.attack());
        Integer remainingHealthWithDefend = player.takeDamage(damageWithDefend);
        System.out.println("You absorbed " + damageWithDefend + " points of damage                                            Health: " + remainingHealthWithDefend);
        System.out.println();
    }

    /**
     * This helper method directs the user to available items they can choose to build their character
     * @param scanner           text scanner utility
     * @param playerInventory   hashtable to store player's equiped items 
     * @param aPlayer           the player choosing the equipments
     * @param gameDialouge      instance of Dialouge class to call toolSelect method for directing player to enter the correct items
     * @param draw              instance of ASCIIart class to call a method that draws the mage
     */
    private static void chooseEquipment(Scanner scanner, Hashtable<String, Integer> playerInventory, PlayableCharacter aPlayer, Dialouge gameDialouge, ASCIIart draw) {
        System.out.println("Choose your weapon adventurer:");
        System.out.println("Longsword --- Double-edged Axe --- Short sword --- Spear --- Rusty Longsword --- Mace --- Club --- Rusty Longsword --- Knife");
        String weaponSelect = scanner.nextLine().toLowerCase();
        //Entry check
        weaponSelect = gameDialouge.toolSelect(weaponSelect, barrack, scanner);
        //Set the damage value of the weapon into the character's weapon parameter which adds bonous damage during attack method (Object value assignment)
        Integer valueOfWeapon = barrack.get(weaponSelect);
        aPlayer.setWeapon(valueOfWeapon);
        //Remove the entered weapon from 'barrack' hashtable and add it to 'inventory' hashtable (Object transfer)
        playerInventory.put(weaponSelect, valueOfWeapon);
        barrack.remove(weaponSelect);
        System.out.println();
        System.out.println("Choose your shield:");
        System.out.println("Metal shield --- Oak shield --- Leather shield");
        String shieldSelect = scanner.nextLine().toLowerCase();
        //Entry check
        shieldSelect = gameDialouge.toolSelect(shieldSelect, barrack, scanner);
        //Object value assignment
        Integer valueOfShield = barrack.get(shieldSelect);
        aPlayer.setShield(valueOfShield);
        //Object transfer
        playerInventory.put(shieldSelect, valueOfShield);
        barrack.remove(shieldSelect);
        System.out.println();
        System.out.println("Finally, choose your armor:");
        System.out.println("Metal armor --- Leather jacket --- Rusty Metal armor");
        String armorSelect = scanner.nextLine().toLowerCase();
        //Entry check
        armorSelect = gameDialouge.toolSelect(armorSelect, barrack, scanner);
        //Object value assignment
        Integer valueOfArmor = barrack.get(armorSelect);
        aPlayer.setArmor(valueOfArmor);
        //Object transfer
        playerInventory.put(armorSelect, valueOfArmor);
        barrack.remove(armorSelect);
        draw.jeremy();
        System.out.println("I shall give you only two epic spells, either for healing your wounds or dealing immense damage to your foes:");
        System.out.println();
        gameDialouge.printSpells(brewery);
        String spellPick = scanner.nextLine().toLowerCase();
        //Entry check
        spellPick = gameDialouge.toolSelect(spellPick, brewery, scanner);
        //Spell assignment
        Integer powerOfSpell = brewery.get(spellPick);
        aPlayer.setSpell(spellPick);
        //Object transfer
        playerInventory.put(spellPick, powerOfSpell);
        brewery.remove(spellPick);
    }

    /**
     * The helper method for the battle loop to determine whether the user's character wins or loses to NPCs
     * @param gameEnemies   the queue of NPCs the player encounters
     * @param aPlayer       user's built character
     * @param draw          instance of ASCIIart class to call methods that draws NPCs, winning, or defeating status 
     * @param scanner       text scanner utility
     */
    private static void battleSequence(Queue<NonPlayableCharacter> gameEnemies, PlayableCharacter aPlayer, ASCIIart draw, Scanner scanner) {
        //Battle loop
        while(!gameEnemies.isEmpty() && aPlayer.getHealthP() > 0) {
            //Returns and removes a NPC from enemyQueue
            NonPlayableCharacter currentEnemy = gameEnemies.poll();
            System.out.println();
            System.out.println("You encounter " + currentEnemy.getName());
            //Conditionals for drawing the correct ASCII art for the enemy
            if(currentEnemy.getName().equals("Death Knight")) {
                draw.deathKnight();
            }
            else if(currentEnemy.getName().equals("Braknar the Savage")) {
                draw.braknar();
            }
            else if(currentEnemy.getName().equals("Ulfgar the Mighty")) {
                draw.ulfgar();
            }
            System.out.println();
            //Fight round loop
            while(currentEnemy.getHealthP() > 0 && aPlayer.getHealthP() > 0) {
                System.out.println("Do you want to 'attack', 'cast spell', or 'defend'?");
                String action = scanner.nextLine().toLowerCase();
                if(action.equals("attack")) {
                    //Call attack's helper method
                    attack(currentEnemy, aPlayer);
                }
                else if(action.equals("cast spell")) {
                    //Call castSpell's helper method
                    castSpell(aPlayer, currentEnemy, scanner, action);
                }
                else if(action.equals("defend")) {
                    //Call defend's helper method
                    defend(aPlayer, currentEnemy);
                }
                else {
                    //Entry check
                    System.out.println("ENTER THE CORRECT ACTION TO PROCEED");
                    action = scanner.nextLine().toLowerCase();
                }
            }
            if (aPlayer.getHealthP() <= 0) {
                //If player dies, print Game Over and exit
                System.out.println("You have been defeated by " + currentEnemy.getName() + ".");
                draw.gameOverDisplay();
                System.exit(0);
            } else {
                //If kill enemy, print winning drawing
                System.out.println("You defeated " + currentEnemy.getName() + "!");
                draw.bossFightWin();
            }
        }
    }

    public static void main(String[] args) {
        //Text scanner in terminal
        Scanner scanner = new Scanner(System.in);

        //Envoke the methods for populating hashtables with game objects
        populateBarrack();
        populateBrewery();
        //Store items picked by the player
        Hashtable<String, Integer> inventory = new Hashtable<>();

        //Initiate classes for entering dialouge and drawings
        ASCIIart draw = new ASCIIart();
        Dialouge dialouge = new Dialouge();

        //An arrayList to sort objects in hashtables for better displaying the objects
        ArrayList<Map.Entry<String, Integer>> sortedObjects = new ArrayList<>(barrack.entrySet());
        //Java built-in sort method and 'Comparator' interface to sort the list based on the values of each object --> Source cited in README
        sortedObjects.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });
        
        //Inputing npc instances in a queue
        Queue<NonPlayableCharacter> enemyQueue = initializeEnemyQueue();

        //Game dialogue and setup
        dialouge.introTaha();
        draw.taha();
        System.out.println("Type 'next' to move on");
        //Retrieving terminal input from the user
        String nextDialouge1 = scanner.nextLine().toLowerCase();
        //Conditional to receive the correct input (Entry check)
        dialouge.entryCheck(nextDialouge1, scanner);
        dialouge.dialougeTaha1();
        System.out.println("'next'");
        String nextDialouge2 = scanner.nextLine().toLowerCase();
        //Entry check
        dialouge.entryCheck(nextDialouge2, scanner);
        dialouge.dialougeTaha2();
        System.out.println("Type 'accept' or 'refuse'");
        String playGame = scanner.nextLine().toLowerCase();
        //Entry check to either enter 'accept' or 'refuse' 
        dialouge.startOffer(playGame, scanner);
        System.out.println("TYPE YOUR NAME");
        //After accepting the offer, make an instance of player which represents the character that the user will build for themselves
        PlayableCharacter player1 = new PlayableCharacter(null, 100, null, null, null, 0, 0, null, 2);
        //Name and setting name to player1 instance
        String nameEntry = scanner.nextLine();
        player1.setName(nameEntry);
        dialouge.dialougeTaha3();
        String classSelect = scanner.nextLine().toLowerCase();
        //Entry check for specialty and setting player's stats accordingly
        dialouge.specialtySelect(classSelect, scanner, player1);
        dialouge.dialogueTaha4(player1);
        draw.weapons();
        System.out.println("AVAILABLE TOOLS AND WEAPONS AT THE BARRACK:");
        System.out.println();
        //Print the sorted items inside 'sortedObjects' for player to choose from
        dialouge.printToolStats(sortedObjects);

        //Envoke the method for assigning objects to character based on user entry
        chooseEquipment(scanner, inventory, player1, dialouge, draw);

        //Envoke the method for the battle loop 
        battleSequence(enemyQueue, player1, draw, scanner);

        scanner.close();
    }  
}