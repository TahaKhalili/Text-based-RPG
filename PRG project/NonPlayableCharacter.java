import java.util.HashMap;

public class NonPlayableCharacter extends Character{
    //The hashmap stores what race of creature the NPCs are which inflicts extra damage based on that
    HashMap<String, Integer> enemiesRace;
    String race;
    
    /**
     * Constructs a NPC which inherits some attributes from Character superclass but has unique variables and methods of its own
     * @param name          name of the NPC for displaying it
     * @param healthPoint   amount of health points the NPC has which determines whether the it wins a fight or dies
     * @param weapon        the integer value of NPC's weapon's damage point 
     * @param shield        the integer value of NPC's shield's damage absorption
     * @param armor         the integer value of NPC's armor's damage absorption
     * @param race          the race of the NPC which affects its attack damage value
     */
    public NonPlayableCharacter(String name, Integer healthPoint, Integer weapon, Integer shield, Integer armor, String race) 
    {
        super(name, healthPoint, weapon, shield, armor);
        this.race = race;
        this.enemiesRace = new HashMap<>();
        //Different races in the game added with the respected additional damage
        enemiesRace.put("Human",0);
        enemiesRace.put("Ogre", 5);
        enemiesRace.put("Giant", 10);
    }

    /**
     * Gets the indicated race of the character
     * @return  race of the character
     */
    public String getRace() {
        return this.race;
    }
    /**
     * Sets the race of the character
     * @param race
     */
    public void setRace(String race) {
        this.race = race;
    }

    /**
     * Simulates an attack action by the NPC.
     * @return the random base attack inflicted by the NPC plus the race and weapon of NPC's additional damage points
     */
    @Override
    public int attack() {
        Integer baseAttack = getRandomNumber(1,16);
        return baseAttack + enemiesRace.get(this.race) + weapon;
    }

    /**
     * Simulates the NPC taking damage from player's attack.
     * @param PC_AttackValue    the damage inflicted by player's attack method
     * @return  the remaining health points of the NPC after taking damage
     */
    public int takeDamage(int PC_AttackValue) {
        int damageTaken = Math.max(PC_AttackValue + this.armor, 0);
        this.healthPoint -= damageTaken;
        return this.healthPoint;
    }

    /**
     * Simulates the NPC taking damage from the player's spell cast attack.
     * @param PC_SpellAttackVal the damage infliceted by player character's spell
     * @return  the remaining health points of the NPC after taking damage
     */
    public int takeSpellDamage(int PC_SpellAttackVal) {
        this.healthPoint -= PC_SpellAttackVal;
        return this.healthPoint;
    }
}
