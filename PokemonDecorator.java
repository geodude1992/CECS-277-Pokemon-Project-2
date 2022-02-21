/**
 * @author Jovanni Garcia
 * @created 11/24/2021 - 3:47 PM
 * @project 277 Project 2
 */
/*
    Decorator patterns allow a user to add new functionality to an existing object without
        altering its structure. So, there is no change to the original class.
    The decorator design pattern is a structural pattern, which provides a wrapper to the
        existing class.
    Decorator design pattern uses abstract classes or interfaces with the composition to
        implement the wrapper.
    Decorator design patterns create decorator classes, which wrap the original class
        and supply additional functionality by keeping the class methods’ signature unchanged.
    Decorator design patterns are most frequently used for applying single responsibility
        principles since we divide the functionality into classes with unique areas of concern.
    The decorator design pattern is structurally almost like the chain of responsibility
        pattern.
 */
abstract class PokemonDecorator extends Pokemon {

    /**
     * Data member for pokemon object
     */
    private Pokemon pokemon;

    /**
     * Decorate Constructor applied to Pokemon
     * @param p - current Pokemon
     * @param extraName to be added to pokemons name
     * @param extraHp to be added to the hp and maxhp
     */
    public PokemonDecorator(Pokemon p, String extraName, int extraHp) {
        super(p.getName() + extraName, p.getHp() + extraHp, p.getMaxHp() + extraHp);
        pokemon = p;
    }

    /**
     * decorates getAttackMenu
     * @param atkType basic/elemental
     * @return pokemon object attack menu method
     */
    public String getAttackMenu(int atkType) {
        return pokemon.getAttackMenu(atkType);
    }

    /**
     * decorates getAttackMenuItems
     * @param atkType basic/elemental
     * @return pokemon object attack menu method
     */
    public int getNumAttackMenuItems(int atkType) {
        return pokemon.getNumAttackMenuTypeItems();
    }

    /**
     * Basic attack shared by all pokemons
     * @param atkType Pokemon
     * @param move Attack choice
     * @return String
     */
    public String getAttackString(int atkType, int move) {
        return pokemon.getAttackString(atkType, move);
    }

    /**
     * Attack damage for Basic attacks
     * @param atkType basic/ elemental
     * @param move Attack choice
     * @return integer amount for basic attack
     */
    public int getAttackDamage(int atkType, int move) {
        return pokemon.getAttackDamage(atkType, move);
    }

    /**
     * Attack damage for Basic attacks
     * @param p pokemon multiplier is for
     * @param atkType basic/ elemental
     * @return double to be casted later
     */
    public double getAttackMultiplier(Pokemon p, int atkType) {
        return pokemon.getAttackMultiplier(p, atkType);
    }

    /**
     * Attack damage bonus from buff/debuff
     * @param atkType basic/ elemental
     * @return Attack damage bonus
     */
    public int getAttackBonus(int atkType) {
        return pokemon.getAttackBonus(atkType);
    }

    /**
     * decorates getType
     * @return type
     */
    public int getType() {
        return pokemon.getType();
    }
}
