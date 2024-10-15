import java.util.Hashtable;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;

public class Test {
    //Define the hashtables for storing objects in the game
    private static Hashtable<String, Integer> barrack = new Hashtable<>();
    private static Hashtable<String, Integer> brewery = new Hashtable<>();
    
    private static void populateBarrack() {
        //Populate the barrack hashtable with items for the game
        barrack.put("longsword", 20);
        barrack.put("leather jacket", -10);
        barrack.put("knife", 5);
        barrack.put("mace", 30);
        barrack.put("leather shield", -10);
    }

    private static void populateBrewery() {
        //Populate the brewery hashtable with items 
        brewery.put("healing potion", 100);
        brewery.put("fire glass", 80);
    }

    private static Queue<NonPlayableCharacter> initializeEnemyQueue() {
        // Initialize the queue with enemy NPCs
        Queue<NonPlayableCharacter> enemyQueue = new LinkedList<>();
        NonPlayableCharacter enemy = new NonPlayableCharacter("Feraidon", 100, barrack.get("knife"), 0, 20, "Human");
        NonPlayableCharacter enemy2 = new NonPlayableCharacter("Marcus", 500, barrack.get("mace"), barrack.get("leather shield"), barrack.get("leather jacket"), "Ogre");
        enemyQueue.add(enemy);
        enemyQueue.add(enemy2);
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
                    //Enemy absorbs damage
                    enemy.takeDamage(playerDamage);
                    if(enemy.getHealthP() > 0) { //If the enemy is still alive, it will attack back
                        System.out.println(enemy.getName() + " is attacking.");
                        Integer npcDamage = enemy.attack();
                        //Player absorbs the retaliated attack
                        Integer remainingHealth = player.takeDamage(npcDamage);
                        //Printing stats
                        System.out.println("You absorbed " + npcDamage + " points of damage wearing armor                          Health: " + remainingHealth);
                    }
                    System.out.println("                                                                   " + enemy.getName() + "'s health: " + enemy.getHealthP());
    }
    /**
     * Spell cast sequence when 'cast spell' action for player is envoked
     * @param player    the player that's envoking the spell cast
     * @param enemy     the NPC that receives the damage and attacks back
     * @param scanner   text scanner utility
     * @param entry     a different action in case player's potions (this.potionCount) run out
     */
    private static void castSpell(PlayableCharacter player, NonPlayableCharacter enemy, Hashtable<String, Integer> storage) {
        Integer remainingSpell = player.getPotionCount();
        //Check if player has potions left to use
        if(remainingSpell <= 0) {
            System.out.println("You've ran out of potions, either 'attack' or 'defend'");
            return;
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
        System.out.println("You absorbed " + damageWithDefend + " points of damage                                          Health: " + remainingHealthWithDefend);
        System.out.println();
    }

    public static void printToolStats(ArrayList<Map.Entry<String, Integer>> objects) {
        for(Map.Entry<String, Integer> entry : objects) { 
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(value > 0) { //Print weapons which add damage first
                System.out.println(key + " - Damage deal: " + value);
                System.out.println("----------------------------------");
            }
            else { //Print shield and armor which deducts damage next
                System.out.println(key + " - Damage deflection: " + value);
                System.out.println("----------------------------------");
            }
        }
    }

    public static void main(String[] args) {
        //Envoke the methods for populating hashtables with game objects
        populateBarrack();
        populateBrewery();

        //An arrayList to sort objects in hashtables for better displaying the objects
        ArrayList<Map.Entry<String, Integer>> sortedObjects = new ArrayList<>(barrack.entrySet());
        //Java built-in sort method to sort the list based on the values of each object --> Source cited in README
        sortedObjects.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });
        printToolStats(sortedObjects);
        
        //Inputing npc instances in a queue
        Queue<NonPlayableCharacter> enemyQueue = initializeEnemyQueue();
        
        //Create the player instance
        PlayableCharacter me = new PlayableCharacter("Taha", 200, barrack.get("longsword"), barrack.get("leather shield"), barrack.get("leather jacket"), 10, 10, "fire glass", 2);

        //A simulation of the game with same logic but manually calling attack, defend, and spell cast method for demonstration of the game
        while(!enemyQueue.isEmpty() && me.getHealthP() > 0) {
            NonPlayableCharacter currentEnemy = enemyQueue.poll();
            System.out.println();
            System.out.println("You encounter " + currentEnemy.getName());

            while(currentEnemy.getHealthP() > 0 && me.getHealthP() > 0) {
                attack(currentEnemy, me);
                castSpell(me, currentEnemy, barrack);
                defend(me, currentEnemy);
            }
            if (me.getHealthP() <= 0) {
                System.out.println("You have been defeated by " + currentEnemy.getName() + ".");
                System.exit(0);
            } else {
                System.out.println("You defeated " + currentEnemy.getName() + "!");
            }
        }
        if (me.getHealthP() > 0) {
            System.out.println("Congratulations! You have defeated all enemies and saved the town.");
        }
    }
}
