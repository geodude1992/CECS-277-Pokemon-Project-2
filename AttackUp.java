/**
 * @author Jovanni Garcia
 * @created 11/24/2021 - 3:57 PM
 * @project 277 Project 2
 */

public class AttackUp extends PokemonDecorator{

    /**
     * Buffs:  AttackUp – increases a pokémon’s damage by 1-2 and adds ‘+ATK’ to their name.
     * @param p pokemon that will get the buff
     */
    public AttackUp(Pokemon p) {
        super(p, "+ATK", 0);
    }

    /**
     * increases a pokémon’s damage by 1-2
     * @param atkType
     * @return
     */
    public int getAttackBonus(int atkType) {
        int randATKBonus = (int) (Math.random() * 2 - 1 + 1) + 1;
        int atkBonus = super.getAttackBonus(atkType) + randATKBonus;
        return atkBonus;
    }
}
