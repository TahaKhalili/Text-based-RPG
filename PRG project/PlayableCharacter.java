public class PlayableCharacter extends Character{
    //Additional instance variables unique to this subclass
    Integer strength;
    Integer wisdom;
    String spell;
    Integer potionCount;

    /**
     * Constructs a playablecharacter which inherits some attributes from Character superclass but has unique variables and methods of its own
     * @param name          name of the player for displaying it
     * @param healthPoint   amount of health points the player has which determines whether the player wins a fight or dies
     * @param weapon        the integer value of player's weapon's damage point 
     * @param shield        the integer value of player's shield's damage absorption
     * @param armor         the integer value of player's armor's damage absorption
     * @param strength      the integer value which buffs player's attack damage
     * @param wisdom        the integer value which buffs player's spell cast effect
     * @param spell         the name of the spell player owns which affects castSpell method
     * @param potionCount   the number of potions the player has to determine whether they can cast spell or not
     */
    public PlayableCharacter(String name,Integer healthPoint, Integer weapon, Integer shield, 
    Integer armor, Integer strength, Integer wisdom, String spell, Integer potionCount) 
    {
        super(name, healthPoint, weapon, shield, armor);
        this.strength = strength;
        this.wisdom = wisdom;
        this.spell = spell;
        this.potionCount = potionCount;
    }

    /**
    * Gets the strength attribute of the character.
    * @return the strength attribute of the character
    */    
    public Integer getStrength() {
        return strength;
    }
    /**
    * Sets the strength attribute of the character.
    * @param strength the strength attribute to set
    */
    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    /**
    * Gets the wisdom attribute of the character.
    * @return the wisdom attribute of the character
    */ 
    public Integer getWisdom() {
        return wisdom;
    }
    /**
    * Sets the wisdom attribute of the character.
    * @param wisdom the wisdom attribute to set
    */
    public void setWisdom(Integer wisdom) {
        this.wisdom = wisdom;
    }

    /**
    * Gets the spell of the character.
    * @return the spell of the character
    */
    public String getSpell() {
        return spell;
    }
    /**
    * Sets the spell of the character.
    * @param spell the spell to set
    */
    public void setSpell(String spell) {
        this.spell = spell;
    }

    /**
     * Gets the number of available potions for the character
     * @return  the number of spells the character has
     */
    public Integer getPotionCount() {
        return potionCount;
    }
    /**
     * Sets the number of potions for character
     * @param potionCount   the number of potions/spells to set for character
     */
    public void setPotionCount(Integer potionCount) {
        this.potionCount = potionCount;
    }

    /**
    * Simulates an attack action by the player.
    * Aside from the base attack, the strengh, and weapon's additional damage value will be added
    * @return the damage done by the attack
    */
    @Override
    public int attack() {
        Integer baseAttack = getRandomNumber(1,16);
        return baseAttack + strength + weapon;
    }
    /**
    * Simulates a defense action by the player against a NPC's attack.
    * @param attackDamage the damage inflected by enemy character's 'attack' method
    * @return the inflected damage after the shield variable reduces the impact of attack
    */
    @Override
    public int defend(Integer attackDamage) {
        Integer damageWithDefend = attackDamage + this.shield;
        Integer reducedDamage = Math.max(damageWithDefend, 0);
        return reducedDamage;
    }

    /**
     * Simulates casting a spell by the player.
     * If the acquired spell is a healing potion, the method will set the player's health point to maximum 100
     * If the acquired spell is fire glass for damage, the method will inflect damage on the enemy
     * @return the damage/healing caused by the spell
     */
    public int castSpell() {
        if(this.spell.equals("healing potion")) {
            setHealthP(100 + this.wisdom);
            setPotionCount(getPotionCount() - 1);
            return this.healthPoint;
        }
        else if(this.spell.equals("fire glass")) {
            Integer spellDamage = 80 + this.wisdom;
            Integer buffedDamage = Math.max(spellDamage, 0);
            setPotionCount(getPotionCount() - 1);
            return buffedDamage;
        }
        return 0;
    }

    /**
     * Simulates the player taking damage from an attack by reducing the health point.
     * The 'this.armor' variable will reduce the amount of damage inflected by eneym's attack
     * @param NPC_AttackValue   the inflicted damage from enemy's attack
     * @return  the remaining health points after taking damage
     */
    public Integer takeDamage(int NPC_AttackValue) {
        int damageTaken = Math.max(NPC_AttackValue + this.armor, 0);
        this.healthPoint -= damageTaken;
        return this.healthPoint;
        }
}
