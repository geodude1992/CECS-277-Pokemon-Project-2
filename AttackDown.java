/**
 * @author Jovanni Garcia
 * @created 11/24/2021 - 3:58 PM
 * @project 277 Project 2
 */

public class AttackDown extends PokemonDecorator{

    /**
     * Debuffs: AttackDown – decreases the pokémon’s damage by 1 and adds a ‘-ATK’ to their name.
     * @param p pokemon that will get the debuff
     */
    public AttackDown(Pokemon p) {
        super(p,"-ATK", 0);
    }

    /**
     * decreases the pokémon’s damage by 1
     * @param atkType
     * @return
     */
    public int getAttackBonus(int atkType) {
        int randATKBonus = -1;
        int atkBonus = super.getAttackBonus(atkType) + randATKBonus;
        return atkBonus;
    }
}
