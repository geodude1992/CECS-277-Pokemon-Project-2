/**
 * @author Jovanni Garcia
 * @created 11/24/2021 - 3:59 PM
 * @project 277 Project 2
 */
public class HpUp extends PokemonDecorator{

    /**
     * HpUp – increases a pokémon’s hp and maxHp by 1-2 and adds ‘+HP’ to their name.
     * @param p pokemon that will get the buff
     */
    public HpUp(Pokemon p) {
        super(p, "+HP", (int)(Math.random() * 2 - 1 + 1) + 1);
    }
}
