/**
 * @author Jovanni Garcia
 * @created 09/29/2021 - 07:50 PM
 * @project 277 Project 1
 */
abstract class Entity {

    private String name;
    private int hp;
    private int maxHp;

    /**
     * Create Entity
     * @param n Entity
     */
    public Entity(String n) {
        // Use this. keyword to distinguish local variables and instance variables
        // other wise unnecessary
        name = n;
    }

    /**
     * Create Entity
     * @param n Entity name
     * @param h Entity Max Health/Hit Points
     * @param m Entity Max Health/Hit Points
     */
    public Entity(String n, int h, int m) {
        name = n;
        hp = h;
        maxHp = m;
    }

    /**
     * Get the entity's current HP
     * @return Entity HP
     */
    public int getHp() {
        return hp;
    }

    /**
     * Gets entity's Max HP
     * @return Integer max HP
     */
    public int getMaxHp() {
        return maxHp;
    }

    /**
     * Update HP
     * @param d damage
     */
    public void takeDamage(int d) {
        hp = hp - d;
        if (hp <= 0) {      // if pokemons hp hits negative value set hp to 0
            hp = 0;
        }
    }

    /**
     * Heal func. Updates HP
     */
    public void heal() {
        hp = maxHp;
    }

    /**
     * Get Entity's Name
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     *  toString – returns a String representation of your object.
     *  The default Object version returns the object's memory address
     *  as a String.
     *  Override this method when you want to be able to print you object
     *  or concatenate it with another String.
     *  It is often handy for debugging, since you can use it to quickly
     *  and easily display the instance variables of the object.
     * @return String Entity Object with current HP out of MaxHP
     */
    @Override public String toString() {
        return name + " HP: " + hp + "/" + maxHp + '\n';
    }

}
