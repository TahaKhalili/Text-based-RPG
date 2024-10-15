import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.Hashtable;

public class Dialouge {
    /**
     * Loop which directs the user to type the correct input 
     * @param entry   user's input
     * @param scanner text scanner in terminal
     */
    public void entryCheck(String entry, Scanner scanner) {
        while(!entry.equals("next")) {
            System.out.println("Please type 'next' to proceed.");
            entry = scanner.nextLine().toLowerCase();
        }
    }
    /**
     * Loop which directs the user to type either 'accept' or 'refuse'
     * @param entry     user's input
     * @param scanner   text scanner in terminal
     */
    public void startOffer(String entry, Scanner scanner) {
        while(!entry.equals("accept") && !entry.equals("refuse")) {
            System.out.println("Please type 'accept' or 'refuse' to proceed.");
            entry = scanner.nextLine().toLowerCase();
        }
        //If refused, the program will quit, otherwise, the game continues with more dialogue
        if(entry.equals("refuse")) {
            System.out.println("YOU LEAVE THE TOWN AND ITS PEOPLE, REFUSING THE KNIGHT'S REQUEST TO HELP THEM");
            System.exit(0);
        }
        System.out.println();
        System.out.println("Great! I will show the way to our barracks and spell brewery.");
        System.out.println("By the way, would you care to introduce yourself?");
        System.out.println();
    }
    /**
     * Loop for correct specialty and set the entered specialty into player's corresponding variables
     * @param entry     user's input
     * @param scanner   text scanner in terminal
     * @param player    the player instance who's specialty is being set based on the entry
     */
    public void specialtySelect(String entry, Scanner scanner, PlayableCharacter player) {
        while(!entry.equals("strength") && !entry.equals("wisdom")) {
            System.out.println("Please type 'strength' or 'wisdom' to proceed");
            entry = scanner.nextLine().toLowerCase();
        }
        //Set player's strength or wisdom based on the entry
        if(entry.equals("strength")) {
            player.setStrength(20);
        }
        else if(entry.equals("wisdom")){
            player.setWisdom(40);
        }
    }

    /**
     * For loop to print a list of hash values line by line
     * @param objects   a list of hash values
     */
    public void printToolStats(ArrayList<Map.Entry<String, Integer>> objects) {
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
        System.out.println();
        System.out.println("YOU CAN ONLY CHOOSE ONE WEAPON, ONE ARMOR, AND ONE SHIELD FROM THE BARRACK");
    }

    /**
     * For loop to print values in a hashtable
     * @param storage   the hashtable to print values from
     */
    public void printSpells(Hashtable<String, Integer> storage) {
        for(Map.Entry<String, Integer> entry : storage.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(key.equals("healing potion")) { //If the object is a desired spell, add the specific print statements 
                //Add the specific value buff if the player selects wisdom as their specialty
                Integer valueWithWisdom = value + 40;
                System.out.println(key + " - Maximum heal : +" + value +" / Maximum heal with extra +20 heal with wisdom: " + valueWithWisdom);
            }
            else if(key.equals("fire glass")) {
                Integer valueWithWisdom = value + 20;
                System.out.println(key + " - Damage deal: " + value + " / Damage deal with extra 20 damage with wisdom: " + valueWithWisdom);
            }
        }
    }

    /**
     * Loop to direct user into correct tool in the specified hashtable
     * @param tool  user input
     * @param storage   the hashtable you look the tool for 
     * @param aScanner  text scanner in termnial
     * @return  the corrected entry to the tool
     */
    public String toolSelect(String tool, Hashtable<String, Integer> storage ,Scanner aScanner) {
        while(!storage.containsKey(tool)) {
            System.out.println("The object is not available");
            tool = aScanner.nextLine().toLowerCase();
        }
        return tool;
    }
    /**
     * Dialogue at start of game
     */
    public void introTaha() {
        System.out.println("A RUINED TOWN IS ON YOUR SIGHT. YOU WALK TOWARD THE ENTRANCE GATE AND MEET A PETTY KNIGHT");
        System.out.println();
        System.out.println("Welcome adventurer!");
        System.out.println("My name is Taha the Thoughtful, protector of this once prosperous and great town.");
    }
    /**
     * Dialogue for interacting with the knight
     */
    public void dialougeTaha1() {
        System.out.println();
        System.out.println("As you can see, I haven't been the most courageos of knights.");
        System.out.println("Evil creatures dwell in the allies of this town, and some even outmatch me I'm afraid.");
        System.out.println("You, on the other hand, show promise. Although you could use some new weapons and some spells.");
        System.out.println();
    }
    /**
     * Dialogue for accepting to play
     */
    public void dialougeTaha2() {
        System.out.println();
        System.out.println("What say you? Would you rid my town of evil and gain the blessings and favor of our people or leave us to our demise?");
        System.out.println();
    }
    /**
     * Dialogue for stats entry
     */
    public void dialougeTaha3() {
        System.out.println();
        System.out.println("WHAT FIELD WOULD YOU LIKE TO SPECIALIZE IN?");
        System.out.println("SELECT 'strength' FOR: +20 EXTRA DAMAGE POINT PER ATTACK OR");
        System.out.println("SELECT 'wisdom' FOR +40 EXTRA DAMAGE POINT PER SPELL CAST");
    }
    /**
     * Dialogue
     * @param player an instance of player to get their name
     */
    public void dialogueTaha4(PlayableCharacter player) {
        System.out.println();
        System.out.println("It's good to have you here " + player.getName());
        System.out.println("Here's our Barrack. Not the best but better than nothing");
    }
}
