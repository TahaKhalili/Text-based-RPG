class Character {
    //Class variables
    String name;
    Integer healthPoint;
    Integer weapon;
    Integer shield;
    Integer armor;
    
    /**
     * Constructs a Character which shares its attributes to Playable (PC) and NonPlayableCharacter (NPC) subclasses
     * @param name          name of the character for displaying it
     * @param healthPoint   amount of health points the character has which determines whether the player wins a fight or dies
     * @param weapon        the integer value of a character's weapon's damage point 
     * @param shield        the integer value of a character's shield's damage absorption
     * @param armor         the integer value of a character's armor's damage absorption
     */
    public Character(String name, Integer healthPoint, Integer weapon, Integer shield, Integer armor) {
        this.name = name;
        this.healthPoint = healthPoint;
        this.weapon = weapon;
        this.shield = shield;
        this.armor = armor;
    }

    /**
     * Generates a random number
     * @param min   minimum value (inclusive)
     * @param max   maximum value (exclusive)
     * @return      random number
     */
    public Integer getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    /**
     * Gets the name of the character
     * @return  name of the character
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the character
     * @param name
     */    
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the health points of the character
     * @return  health point variable
     */
    public Integer getHealthP() {
        return this.healthPoint;
    }
    /**
     * Sets the health point variable of the character
     * @param healthPoint
     */
    public void setHealthP(Integer healthPoint) {
        this.healthPoint = healthPoint;
    }

    /**
     * Gets the damage value of the weapon variable of a character
     * The purpose for having an integer rather than the name of the weapon is to use the integer for modifying 'attack' method 
     * @return  the damage value
     */
    public Integer getWeapon() {
        return weapon;
    }
    /**
     * Sets the damage value of the weapon variable of a character
     * @param weapon
     */
    public void setWeapon(Integer weapon) {
        this.weapon = weapon;
    }

    /**
     * Gets the damage deflection value of the shield of a character
     * It's an integer rather than the name of the shield to use the integer for modifying 'defend' method
     * @return  the damage defelction value
     */
    public Integer getShield() {
        return shield;
    }
    /**
     * Set the damage deflection value of the shield of a character
     * @param shield
     */
    public void setShield(Integer shield) {
        this.shield = shield;
    }

    /**
     * Gets the damage deflection value of the armor of a character
     * It's an integer rather than the name of the armor to use the integer for modifying 'takeDamage' method
     * @return
     */
    public Integer getArmor() {
        return armor;
    }
    /**
     * Set the damage deflection value of the armor of a character
     * @param armor
     */
    public void setArmor(Integer armor) {
        this.armor = armor;
    }

    /**
     * Simulates an attack action by a character
     * @return  the damage done by the random base attack 
     */
    public int attack() {
        return getRandomNumber(1,20);
    }

    /**
     * Simulates a defend action by a character against another character's attack method
     * @param attackDamage  the damage inflected by enemy character's 'attack' method
     * @return  the inflected damage after the defend modifications which will reduce the damage value
     */
    public int defend(Integer attackDamage) {
        return attackDamage;
    }

}