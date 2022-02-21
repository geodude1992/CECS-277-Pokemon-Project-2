/**
 * @author Jovanni Garcia
 * @created 09/29/2021 - 07:44 PM
 * @project 277 Project 2
 */

public class Grass extends Pokemon{

    /**
     *
     * @param n
     * @param h
     * @param m
     */
    public Grass(String n, int h, int m) {
        super(n, 25, 25);
    }

    /**
     * Returns menu for Pokemon encounter
     * This is overridden in the base pokémon classes for the
     * special elemental attacks (based on atkType)
     * @return the menu listing the options of that pokémon’s moves (ex. slam/tackle/punch).
     */
    @Override public String getAttackMenu(int atkType) {
        StringBuilder attackMenu = new StringBuilder();
        if (atkType == 1) {
            return super.getAttackMenu(atkType);
        } else if (atkType == 2) {
            attackMenu.append("1. Vine Whip\n2. Razor Leaf\n3. Solar Beam\n");
        }
        return attackMenu.toString();
    }

    /**
     * Returns the number 3
     * @return the number of moves in the above menu.
     * This is overridden in the base pokémon classes for the special elemental attacks.
     */
    @Override public int getNumAttackMenuItems(int atkType) {
        return 3;
    }

    /**
     * Special attack shared by all Fire types
     * This is overridden in the base
     * pokémon classes for the special elemental attacks.
     * @param atkType Pokemon
     * @param move Attack choice
     * @return the partial string for the chosen move (ex.
     * “SLAMMED” if choice was 1 for the basic attack)
     */
    @Override public String getAttackString(int atkType, int move) {
        if (atkType == 1) {
            if (move == 1) {
                return super.getAttackString(atkType, move);
            } else if (move == 2) {
                return super.getAttackString(atkType, move);
            } else if (move == 3) {
                return super.getAttackString(atkType, move);
            }
        } else if (atkType == 2) {
            if (move == 1) {
                return ("constricted by VINE WHIPPED");
            } else if (move == 2) {
                return ("slashed by RAZOR LEAF");
            } else {
                return ("dazzled by SOLAR BEAM");
            }
        }
        return "";
    }

    /**
     * Special Attack: Vine Whip does random damage between 1 - 3
     * Special Attack: Razor Leaf does random damage between 2 and 4
     * Special Attack: Solar Beam does random damage between 0 and 5
     * Overridden in the base pokémon classes for special elemental attacks.
     * @param atkType for special attacks
     * @param move for ember, blast, punch
     * @return the randomized damage for the chosen move.
     */
    @Override public int getAttackDamage(int atkType, int move) {
        int damage = 0;
        if (atkType == 1) {
            if (move == 1) {            // SLAM
                return super.getAttackDamage(atkType, move);
            } else if (move == 2) {     // TACKLE
                return super.getAttackDamage(atkType, move);
            } else if (move == 3) {     // PUNCH
                return super.getAttackDamage(atkType, move);
            }
        } else if (atkType == 2) {
            if (move == 1) {
                damage = (int) (Math.random() * (3 - 1 + 1)) + 1;
            } else if (move == 2) {
                damage = (int) (Math.random() * (4 - 2 + 1)) + 2;
            } else if (move == 3) {
                damage = (int) (Math.random() * (5 + 1));
            }
        }
        return damage;
    }

    /**
     * Fire type against other types
     * This can be overridden in the base pokémon classes for
     * the special elemental attacks and/or in the decoration buff/debuff classes.
     * @param p pokemon taking damage
     * @param atkType for special attacks
     * @return the attack multiplier for that class’s moves
     */
    @Override public double getAttackMultiplier(Pokemon p, int atkType) {
        double multi = 0;
        if (atkType == 1) {
            return super.getAttackMultiplier(p, atkType);
        } else if (atkType == 2) {// Grass Type Multiplier
                if (p.getType() == 0) {
                    multi = battleTable[2][0];     // Against Fire
                } else if (p.getType() == 1) {
                    multi = battleTable[2][1];     // Against Water
                } else if (p.getType() == 2) {
                    multi = battleTable[2][2];     // Against Grass
                }

        }
        return multi;
    }
}
