/**
 * @author Jovanni Garcia
 * @created 09/29/2021 - 07:45 PM
 * @revised 11/30/2021
 * @project 277 Project 2 updated from Project 1
 */
/*
    An abstract class can have abstract methods and non-abstract methods.
    Objects can NOT be instantiated from abstract classes ONLY their (Non-abstract) subclasses

    Abstract class doesn't support multiple inheritance.
    Abstract class can have final, non-final, static and non-static variables.
    Abstract class can provide the implementation of interface.

    The abstract keyword is used to declare abstract class.
    An abstract class can extend another Java class and implement multiple Java interfaces.
    An abstract class can be extended using keyword "extends".

    A Java abstract class can have class members like private, protected, etc.
*/
abstract class Pokemon extends Entity {
    // Static Variables CAN be called by objects UNLIKE Static Methods
    // All objects of the class share the same value for that static variable
    static final double [][] battleTable = {{1,  0.5,    2},
                                            {2,    1,  0.5},
                                            {0.5,  2,   1}};

    /**
     * Super() invokes the super(parent)class constructor
     * acts like the parent constructor inside the child class constructor
     * @param n String name
     */
    public Pokemon(String n,  int h, int m) {
        super(n, h, m);
    }

    /**
     * Basic and Special attack Menu
     * @return the basic and special attack menu string
     */
    public String getAttackTypeMenu() {
        return "1. Basic Attack\n2. Special Attack";
    }

    /**
     * Returns number for type of attacks
     * @return 2 attack menu types
     */
    public int getNumAttackMenuTypeItems() {
        return 2;
    }

    /**
     * Returns menu for Pokemon encounter
     * This is overridden in the base pokémon classes for the
     * special elemental attacks (based on atkType)
     * @return the menu listing the options of that pokémon’s moves (ex. slam/tackle/punch).
     */
    public String getAttackMenu(int atkType) {
        return "1. Slam\n2. Tackle\n3. Punch\n";
    }

    /**
     * Returns the number 3
     * @return the number of moves in the above menu.
     * This is overridden in the base pokémon classes for the special elemental attacks.
     */
    public int getNumAttackMenuItems(int atkType) {
        return 3;
    }

    /**
     * Basic attacks shared by all pokemons: Slam, Tackle, Punch
     * calculates the total damage, deals it to the defending pokémon, and builds
     * the full attack string that will be returned to be displayed during a fight (ex.
     * “Oddish is SLAMMED by Charmander and takes 7 damage”).
     * @param p that gets slammed
     * @param atkType
     * @param move
     * @return String
     */
    public String Attack(Pokemon p, int atkType, int move) {
        if (atkType == 1) {
            double baseDmg = this.getAttackDamage(atkType, move) * this.getAttackMultiplier(p, atkType);
            int baseDmgInt = (int) baseDmg;
            if (move == 1) {
                if (baseDmgInt == 0) {
                    return this.getName() + "'s attack missed.\n";
                } else if (baseDmgInt > 0) {
                    p.takeDamage(baseDmgInt);
                    return p.getName() + " is " + this.getAttackString(atkType, move) + " and takes " + baseDmgInt + " damage.\n";
                }
            } else if (move == 2) {
                if (baseDmgInt == 0) {
                    return this.getName() + "'s attack missed.\n";
                } else if (baseDmgInt > 0) {
                    p.takeDamage(baseDmgInt);
                    return p.getName() + " is " + this.getAttackString(atkType, move) + " and takes " + baseDmgInt + " damage.\n";
                }
            } else if (move == 3) {
                if (baseDmgInt == 0) {
                    return this.getName() + "'s attack missed.\n";
                } else if (baseDmgInt > 0) {
                    p.takeDamage(baseDmgInt);
                    return p.getName() + " is " + this.getAttackString(atkType, move) + " and takes " + baseDmgInt + " damage.\n";
                }
            }
        } else if (atkType == 2) {
            double ElementalDmg = this.getAttackDamage(atkType, move) * this.getAttackMultiplier(p, atkType);
            int ElementalDmgInt = (int) ElementalDmg;
            if (move == 1) {
                if (ElementalDmgInt == 0) {
                    return this.getName() + "'s attack missed.\n";
                } else if (ElementalDmgInt > 0) {
                    p.takeDamage(ElementalDmgInt);
                    return p.getName() + " is " + this.getAttackString(atkType, move) + " and takes " + ElementalDmgInt + " damage.\n";
                }
            } else if (move == 2) {
                if(ElementalDmgInt == 0) {
                    return this.getName() + "'s attack missed.\n";
                } else if (ElementalDmgInt > 0) {
                    p.takeDamage(ElementalDmgInt);
                    return p.getName() + " is " + this.getAttackString(atkType, move) + " and takes " + ElementalDmgInt + " damage.\n";
                }
            } else if (move == 3) {
                if(ElementalDmgInt == 0) {
                    return this.getName() + "'s attack missed.\n";
                } else if (ElementalDmgInt > 0) {
                    p.takeDamage(ElementalDmgInt);
                    return p.getName() + " is " + this.getAttackString(atkType, move) + " and takes " + ElementalDmgInt + " damage.\n";
                }
            }
        }
        return "";
    }

    /**
     * Basic attack shared by all pokemons
     * This is overridden in the base
     * pokémon classes for the special elemental attacks.
     * @param atkType Pokemon
     * @param move Attack choice
     * @return the partial string for the chosen move (ex.
     * “SLAMMED” if choice was 1 for the basic attack)
     */
    public String getAttackString(int atkType, int move) {
        StringBuilder attackString = new StringBuilder();
        if (atkType == 1) {
            if (move == 1) {
                attackString.append("laid out by SLAM");
            } else if (move == 2) {
                attackString.append("stunned by TACKLE");
            } else if (move == 3) {
                attackString.append("seeing double by PUNCH");
            }
        }
        return attackString.toString();
    }

    /**
     * Basic Attack: Slam does random damage between 0 - 5
     * Basic Attack: Tackle does random damage of either 2 or 3
     * Basic Attack: Punch does random damage between 1 and 4
     * Overridden in the base pokémon classes for special elemental attacks.
     * @param atkType for basic attacks
     * @param move for slam, tackle, punch
     * @return the randomized damage for the chosen move
     */
    public int getAttackDamage(int atkType, int move) {
        int damage = 0;
        if (atkType == 1) {
            if (move == 1) {            // SLAM
                damage = (int) (Math.random() * (5 + 1));
            } else if (move == 2) {     // TACKLE
                damage = (int) (Math.random() * (3 - 2 + 1)) + 2;
            } else if (move == 3) {     // PUNCH
                damage = (int) (Math.random() * (4 - 1 + 1)) + 1;
            }
        }
        return damage;
    }

    /**
     * Basic attacks have a multiplier of 1
     * In other words, the damage does NOT change.
     * @param p pokemon getting the multiplier added to its attack
     * @param atkType basic attack
     * @return 1 for basic attack
     */
    public double getAttackMultiplier(Pokemon p, int atkType) {
        return 1;
    }

    /**
     * bonus added to pokemon attacks
     * @param atkType attack type
     * @return the attack bonus that will be added to the calculated damage.
     */
    public int getAttackBonus(int atkType) {
        return 0;
    }

    /**
     * Gets the Pokemons type by their interface
     * @return the integer corresponding to the elemental type of the Pokémon for use in the battle table
     */
    public int getType() {
        int type;
        // if pokemon is from this interface
        // fire =0 wtr=1 grass=2
        // obj instanceof interface
        if (this instanceof Fire) {
            type = 0;
        } else if (this instanceof Water) {
            type = 1;
        } else if (this instanceof Grass) {
            type = 2;
        } else {
            type = 3;
        }
        return type;
    }
}

