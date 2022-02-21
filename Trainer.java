import java.awt.Point;
import java.util.ArrayList;
/**
 * Trainer aggregate map
 * "has a..: relationship
 * one class has an instance object of another class
 *
 * @author Jovanni Garcia
 * @created 09/29/2021 - 07:46 PM
 * @project 277 Project 1
 */

public class Trainer extends Entity {
    private int money;
    private int potions;
    private int pokeballs;
    private Point loc;
    private ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();

    /**
     * Trainer Overloaded Constructor
     * @param n String of trainers name
     * @param p Pokemon object of trainers starting pokemon
     */
    public Trainer(String n, Pokemon p){
        super(n, 25, 25);
        pokemon.add(p);
        money = 25;
        potions = 1;
        pokeballs = 5;
        loc = Map.getInstance().findStart();
    }

    /**
     * Accessor Method retrieves trainers money in possession
     * @return Integer value
     */
    int getMoney(){
        return money;
    }

    /**
     * Allows trainer to spend money at store
     * @param amt integer value of amount that will be spent
     * @return boolean value if trainer can or cant spend money
     */
    public boolean spendMoney(int amt){
        if (money >= amt){
            money -= amt;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Users for random person encounter that will give trainer money
     * @param amt integer value for money
     */
    public void recieveMoney(int amt){
        money += amt;
    }

    /**
     * Method checks if trainer has a potion for use
     * @return boolean value depending if trainer has potions in possession
     */
    public boolean hasPotions(){
        return potions > 0;
    }

    /**
     * Void method increase trainers potion in possession by one
     */
    public void receivePotion(){
        potions += 1;
    }

    /**
     * Method for using a potion on a selected pokemon
     * decrements the potions in trainers possession by 1
     * @param pokeIndex index value of pokemon in arraylist that will get healed with potion
     */
    public void usePotion(int pokeIndex) {
        Pokemon pokemonToHeal = this.pokemon.get(pokeIndex);
        pokemonToHeal.heal();  // heal pokemon at index value of list
        pokemon.remove(pokemonToHeal);
        PokemonGenerator p = PokemonGenerator.getInstance();
        pokemonToHeal = p.addRandomBuff(pokemonToHeal);   // Potion Buff
        pokemon.add(pokemonToHeal);
        System.out.println(pokemon.get(pokeIndex).getName() + " was healed and buffed!\n");
        potions--;
    }

    /**
     * Method checks if trainer has a pokeballs for use
     * @return boolean value depending if trainer has pokeballs in possession
     */
    public boolean hasPokeball(){
        return pokeballs > 0;
    }

    /**
     * Void method increase trainers pokeball in possession by one
     */
    public void recievePokeball(){
        pokeballs += 1;
    }

    /**
     * Catches a Pokemon based on how low their HP is
     * ChanceInc = (MaxHP - Hp) / MaxHp
     * @param p Pokemon that will be caught
     * @return boolean value if pokemon is caught or not
     */
    public boolean catchPokemon(Pokemon p){
        System.out.println(this.getName() + " used POKé BALL!");    // Option 3 trainerAttack()
        this.pokeballs--;   // Every time a Pokeball is thrown, decrement pokeballs
        double chanceInc = ((double) p.getMaxHp() - (double) p.getHp()) / (double) p.getMaxHp();
        chanceInc *= 100;
        System.out.println("There is a " + (int) chanceInc + "% chance.");
        // Pokeballs only have a 10% chance of catching a pokemon
        int rand = (int) (Math.random() * (100+1));     // random number 10-100
        if (rand <= (int) chanceInc) {        // if num == 1 catch pokemon return T
            System.out.println("Shake...Shake...Shake...\n" +
                    "You caught " + p.getName() + ".\n");
            // Check if trainer has move than 6 pokemon
            if(pokemon.size() == 6){
                System.out.println("You can only carry 6 Pokémon at a time!\n" +
                        "Choose a Pokémon to remove from your inventory:");
                System.out.println(this.getPokemonList());
                // Since Pokemon Inventory is shown as 1 - 6 instead of index the player choice is decremented
                // by 1 so it removes based of the index
                int remove = CheckInput.getIntRange(1, pokemon.size()+1);
                int removeIndex = remove - 1;
                removePokemon(removeIndex);
            }
            pokemon.add(p);     // add pokemon to arraylist
            System.out.println("You've added " + p.getName() + " to your inventory!\n" +
                    "This is your update inventory: \n" + this.getPokemonList());
            Map.getInstance().removeCharAtLoc(this.getLocation());
            return true;
        } else {
            System.out.println("Shake...Shake...Shake...\n" +
                    "You missed the pokemon!\n");
            return false;       // else return F
        }

    }

    /**
     * Accessor Method returns a point as the location
     * @return locations
     */
    public Point getLocation(){
        return loc;
    }

    /**
     * moves trainers location at x-1
     * @return Char at location
     */
    public char goNorth(){
        if (loc.x <= 0) {
            return 'j';
        }
        loc.x -= 1;
        Map.getInstance().reveal(loc);
        return Map.getInstance().getCharAtLoc(loc);
    }

    /**
     * moves trainers location at x+1
     * @return Char at location
     */
    public char goSouth(){
        if (loc.x >= 4) {
            return 'j';
        }
        loc.x += 1;
        Map.getInstance().reveal(loc);
        return Map.getInstance().getCharAtLoc(loc);
    }

    /**
     * moves trainers location at y+1
     * @return Char at location
     */
    public char goEast(){
        if (loc.y >= 4) {
            return 'j';
        }
        loc.y += 1;
        Map.getInstance().reveal(loc);
        return Map.getInstance().getCharAtLoc(loc);
    }

    /**
     * moves trainers location at y-1
     * @return Char at location
     */
    public char goWest(){
        if (loc.y <= 0) {
            return 'j';
        }
        loc.y -= 1;
        Map.getInstance().reveal(loc);
        return Map.getInstance().getCharAtLoc(loc);
    }

    /**
     * Accessor function retieves size of pokemon arraylist
     * @return integer of trainers total pokemon in possession
     */
    public int getNumPokemon(){
        return pokemon.size();
    }

    /**
     * no return method that iterates through every
     * pokemon in the trainers possession and heals them
     */
    public void healAllPokemon(){
        for(int i=0; i < pokemon.size(); i++) {
            pokemon.get(i).heal();
        }
    }

    /**
     * no return method that iterates through every
     * pokemon in the trainers possession and heals them
     */
    public void buffAllPokemon(){
        for (Pokemon value : pokemon) {
            PokemonGenerator.getInstance().addRandomBuff(value);
        }
    }

    /**
     * no return method that iterates through every
     * pokemon in the trainers possession and heals them
     */
    public void debuffPokemon(int index){
        Pokemon pokemonTodebuff = this.pokemon.get(index);
        pokemon.remove(pokemonTodebuff);
        PokemonGenerator p = PokemonGenerator.getInstance();
        pokemonTodebuff = p.addRandomDebuff(pokemonTodebuff);
        pokemon.add(pokemonTodebuff);
        potions--;
    }

    /**
     * Accessor gets trainers pokemon from ArrayList by index value
     * @param index int
     * @return Pokemon obj
     */
    public Pokemon getPokemon(int index) {
        return pokemon.get(index);
    }

    /**
     * Gets the Pokemon Objects from the ArrayList and
     * returns them as a string
     * @return String of trainers Pokemon list
     */
    public String getPokemonList() {
        // String Builder(mutable) can be used when changing a string a lot
        // i.e. loops , concat
        StringBuilder list = new StringBuilder();   // SB VAR that we can append our strings
        for(int i=0; i < pokemon.size(); i++) {
            // ArrayList.get(int i) Returns the element at a specified index in this list
            // i. pokemon[i]\n
            // 3. Charmander hp: 1/100
            list.append(i + 1).append(". ").append(pokemon.get(i).toString());
        }
        return list.toString();
    }

    /**
     * Accessor gets trainers pokemon from ArrayList by index value
     * @param index int
     * @return Pokemon obj
     */
    public Pokemon removePokemon(int index) {
        return pokemon.remove(index);
    }

    /**
     * Methods Overrides toString method
     * @return String of trainer stats
     */
    @Override
    public String toString() {
        return super.toString() + "\nMoney: " + money + "\nPotions: " + potions
                + "\nPoke Balls: " + pokeballs + "\nPokemon\n-------\n"
                + getPokemonList() + "\nMap:\n" + Map.getInstance().mapToString(loc);
    }
}