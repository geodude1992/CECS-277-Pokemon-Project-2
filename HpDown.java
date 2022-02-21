/**
 * @author Jovanni Garcia
 * @created 11/24/2021 - 4:00 PM
 * @project 277 Project 2
 */
public class HpDown extends PokemonDecorator{

    /**
     * HpDown – decreases a pokémon’s hp and maxHp by 1 and adds ‘-HP’ to their name.
     * @param p pokemon that will get the debuff
     */
    public HpDown(Pokemon p) {
        super(p, "-HP", -1);
    }
}
