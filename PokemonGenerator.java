import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Random;

/**
 * @author Jovanni Garcia
 * @created 11/24/2021 - 3:47 PM
 * @project 277 Project 2
 */
public class PokemonGenerator {

    /**
     *  Hashmap that contains Pokemon that can be generated
     */
    private HashMap<String, String> pokemon;

    /**
     * singleton instance
     */
    private static PokemonGenerator instance = null;    // Single PokemonGenerator instance

    /**
     * Hidden Constructor should read from the file into a HashMap to store the
     * different pokémon names and their associated elemental type.
     */
    private PokemonGenerator(){
        pokemon = new HashMap<String, String>();
        try {
            Scanner fileObj = new Scanner(new File("PokemonList.txt"));
            while (fileObj.hasNextLine()) {
                String line = fileObj.nextLine();
                String[] pokeLine = line.split("[,]", 2);
                //System.out.println(Arrays.toString(pokeLine));

                String pokemonName = pokeLine[0];   // key
                String elementType = pokeLine[1];   // value

                if (elementType.contains("Fire")) {
                    pokemon.put(pokemonName, elementType);
                } else if (elementType.contains("Grass")) {
                    pokemon.put(pokemonName, elementType);
                } else if (elementType.contains("Water")) {
                    pokemon.put(pokemonName, elementType);
                }
            } fileObj.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
            e.printStackTrace();
        }
    }

    /**
     * gets the single PokemonGenerator instance
     * @return static PokemonGenerator object
     */
    public static PokemonGenerator getInstance() {
        if(instance == null) {
            instance = new PokemonGenerator();
        }
        return instance;
    }

    /**
     * generateRandomPokemon should randomly pick a pokémon from
     * the HashMap, and then construct a pokémon of the corresponding
     * elemental base type. Then for each level greater than one,
     * repeatedly decorate it with a random buff
     * @param level map level to apply buff
     * @return random pokemon
     */
    public Pokemon generateRandomPokemon(int level) {

        Object[] keys = pokemon.keySet().toArray();
        String key = (String) keys[new Random().nextInt(keys.length)];
        String ptype = pokemon.get(key);

        Pokemon p = null;
        if (ptype.equals("Fire")) {
            p = new Fire(key, 25, 25);
        } else if (ptype.equals("Grass")) {
            p = new Grass(key, 25, 25);
        } else if (ptype.equals("Water")) {
            p = new Water(key, 25, 25);
        }

        for (int i = 1; i <= level; i++) {
            PokemonGenerator pBuff = PokemonGenerator.getInstance();
            p = pBuff.addRandomBuff(p);
            PokemonGenerator.getInstance().addRandomBuff(p);
        }
        return p;
    }

    /**
     * getPokemon passes in a string with the name of a pokémon and
     * constructs an object of the correct corresponding type.
     * @param name of pokemon
     * @return pokemon object
     */
    public Pokemon getPokemon(String name) {
        Pokemon p = null;
        if(name.equals("Charmander") || name.equals("Vulpix")
                || name.equals("Growlithe") || name.equals("Ponyta")
                || name.equals("Moltres")) {
            p = new Fire(name, 25, 25);
        } else if(name.equals("Bulbasaur") || name.equals("Oddish")
                || name.equals("Bellsprout") || name.equals("Exeggcute")
                || name.equals("Tangela")) {
            p = new Grass(name, 25, 25);
        } else if(name.equals("Squirtle") || name.equals("Psyduck")
                || name.equals("Poliwag") || name.equals("Tentacool")
                || name.equals("Slowpoke") || name.equals("Seel")
                || name.equals("Shellder") || name.equals("Krabby")
                || name.equals("Horsea") || name.equals("Goldeen")
                || name.equals("Staryu") || name.equals("Magikarp")
                || name.equals("Lapras")) {
            p = new Water(name, 25, 25);
        }
        return p;
    }

    /**
     * addRandomBuff randomly chooses a buff to apply
     * to the pokémon
     * @param p pokemon getting buff
     * @return pokemon getting buff
     */
    public Pokemon addRandomBuff(Pokemon p) {
        int rand = (int) (Math.random() * (2 - 1 + 1)) + 1;
        if (rand == 1) {
            p = new AttackUp(p);
        } else if (rand == 2) {
            p = new HpUp(p);
        }
        return p;
    }

    /**
     * addRandomDebuff randomly chooses a debuff to apply
     * to the pokémon
     * @param p pokemon getting debuff
     * @return pokemon getting debuff
     */
    public Pokemon addRandomDebuff(Pokemon p) {
        int rand = (int) (Math.random() * (2 - 1 + 1)) + 1;
        if (rand == 1) {
            p = new AttackDown(p);
        } else if (rand == 2) {
            p = new HpDown(p);
        }
        return p;
    }
}
