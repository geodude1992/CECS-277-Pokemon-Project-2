/**
 * @author Jovanni Garcia
 * @created 10/06/2021 - 09:09 PM
 * @project 277 Project 1
 */

public class Water extends Pokemon {

    /**
     *
     * @param n
     * @param h
     * @param m
     */
    public Water(String n, int h, int m) {
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
            attackMenu.append("1. Water Gun\n2. Bubble Beam\n3. Waterfall\n");
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
        StringBuilder attackString = new StringBuilder();
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
                attackString.append("soaked by WATER GUN");
            } else if (move == 2) {
                attackString.append("splashed by BUBBLE BEAM");
            } else {
                attackString.append("drowned by WATER FALL");
            }
        }
        return attackString.toString();
    }

    /**
     * Special Attack: WaterGun does random damage between 2 - 5
     * Special Attack: Bubble Beam does random damage between 1 - 3
     * Special Attack: WaterFall does random damage between 1 and 4
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
                damage = (int) (Math.random() * (5 - 2 + 1)) + 2;
            } else if (move == 2) {
                damage = (int) (Math.random() * (3 - 1 + 1)) + 1;
            } else if (move == 3) {
                damage = (int) (Math.random() * (4 - 1 + 1)) + 1;
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
        } else if (atkType == 2) {// Water Type Multiplier
                if (p.getType() == 0) {
                    multi = battleTable[1][0];     // Against Fire
                } else if (p.getType() == 1) {
                    multi = battleTable[1][1];     // Against Water
                } else if (p.getType() == 2) {
                    multi = battleTable[1][2];     // Against Grass
                }

        }
        return multi;
    }
}