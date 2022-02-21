import java.awt.*;
import java.util.Scanner;
/**
 * @author Jovanni Garcia
 * @created 09/29/2021 - 05:22 PM
 * @project 277 Project 1
 */

public class Main {

    public static void main(String[] args) {
        // Game starts with level 0 Pokemon and level increments as map changes
        // level 0 means no buffs until level 1 then one buff is applied to wild
        int level = 0;

        // 1. Prompt the user to enter their name
        System.out.println("Prof. Oak: Hello there new trainer, what is your name?");
        Scanner input = new Scanner(System.in);
        // Returns the inputted value as a string, next() parses on any whitespace
        String userName = input.nextLine(); // User inputs name

        // 2. and select a starting pokémon (Charmander, Squirtle, or Bulbasaur)
        System.out.println("Great to meet you, " + userName + "! Choose your first Pokémon:\n" +
                "1. Charmander\n2. Bulbasaur\n3. Squirtle\n");
        Pokemon starter = null;   // Pokemon needs to be declared outside if-else scope
        int starterChoice = CheckInput.getIntRange(1, 3);
        if (starterChoice  == 1) {
            starter = PokemonGenerator.getInstance().getPokemon("Charmander");
        } else if(starterChoice  == 2) {
            starter = PokemonGenerator.getInstance().getPokemon("Bulbasaur");
        } else if (starterChoice == 3){
            starter = PokemonGenerator.getInstance().getPokemon("Squirtle");
        }

        // 3. then construct a Map and a Trainer object.
        Map map = Map.getInstance();
        Trainer ash = new Trainer(userName, starter);

        // 4. Display the map
        int directionOption = 0;
        int mapType = 1;
        while(directionOption != 5){
            if (ash.getHp() == 0) {
                System.out.println(ash.getName() + " has fainted!\nGameOver!");
                System.exit(1);
            }
            System.out.println(ash.toString());

            char event = ' ';
            directionOption = mainMenu();
            switch(directionOption){
                case 1:
                    event = ash.goNorth();
                    break;
                case 2:
                    event = ash.goSouth();
                    break;
                case 3:
                    event = ash.goEast();
                    break;
                case 4:
                    event = ash.goWest();
                    break;
                case 5:
                    System.out.println("You Quit!");
                    System.exit(1);
                default:
                    System.out.println("Invalid Input");
                    break;
            }

            if (event == 'j') {     // out of bounds
                System.out.println("You cannot go that way.");
            } else if (event == 'n') {      // nothing in location
                System.out.println("There is nothing out here...");
            } else if (event == 'w') {      // wild pokemon encounter
                Pokemon wild = PokemonGenerator.getInstance().generateRandomPokemon(level);
                // Prints wild pokemon encounter
                System.out.println("A wild " + wild.getName() + " has appeared.");
                System.out.println(wild.toString() + "\n");

                int encounterChoice = 0;

                while(wild.getHp() > 0) {
                    // PRINTS Pokemon Encounter Menu. i.e. 1Fight, 2Potion, 3Ball, 4Run
                    System.out.println("What do you want to do?\n1. Fight\n2. Use Potion\n" +
                            "3. Throw Poke Ball\n4. Run Away");
                    encounterChoice = CheckInput.getIntRange(1, 4);

                    if (encounterChoice == 1) {   // 1. Fight
                        trainerAttack(ash, wild);
                    } else if (encounterChoice == 2) {   // 2. Use Potion
                        // if hasPotion() is true then heal
                        if (ash.hasPotions()) {
                            System.out.println("\nChoose a Pokemon to heal:\n" + ash.getPokemonList());    // Choose Pokemon calls t func
                            int poke = CheckInput.getIntRange(1, ash.getNumPokemon()) - 1;
                            ash.usePotion(poke);
                        } else {
                            System.out.println("You don't have any Potions!\n");
                        }
                    } else if (encounterChoice == 3) {   // 3. Throw Poke Ball
                        // if hasPokeball() is true then catchPokemon()
                        if(ash.hasPokeball()) {
                            if (ash.catchPokemon(wild)) { // if pokemon is caught return to main menu
                                break;
                            }
                            // else wild attacks
                        } else {
                            System.out.println("You don't have any Pokéballs!\n");
                        }
                    } else if (encounterChoice == 4) {   // 4. Run Away
                        System.out.println("You escaped!");
                        break;
                    }
                }
                if (wild.getHp() == 0) {
                    map.removeCharAtLoc(ash.getLocation());
                }
            } else if (event == 'p'){   // random person encounter
                System.out.println("There is a strange person in the distance...\n...\n");
                int rand = (int) (Math.random() * 3);
                if (rand == 0){     // Person 1: gives you cash
                    rand = (int) (Math.random() * 10);
                    System.out.println("Hey there trainer, if you want to catch Pokémon you need Pokéballs.\n" +
                            "You can buy some at the store, here take some cash.\n");
                    ash.recieveMoney(rand);
                } else if(rand == 1) {      // Person 2: gives you a potion
                    System.out.println("Hey there trainer, after a Pokémon battle it is important to heal your Pokémon\n" +
                            "If a hospital isn't nearby try using a potion, here take a potion");
                    ash.receivePotion();
                } else {    // person 3: Team Rocket encounter, trainer take 5 damage
                    System.out.println("Prepare for trouble,\n" +
                            "And Make it double!\n" +
                            "To protect the world from devastation,\n" +
                            "To unite all people within our nation,\n" +
                            "To denounce the evil of truth and love,\n" +
                            "To extend our reach to the stars of above,\n" +
                            "Jessie!\n" +
                            "James!\n" +
                            "Team Rocket blast off at the speed of light!\n" +
                            "Surrender now or prepare to fight!\n" +
                            "Meowth, Thats Right!");
                    ash.takeDamage(5);
                    System.out.println(ash.getName() + " took 5 damage!\n");
                }
                map.removeCharAtLoc(ash.getLocation());
            } else if (event == 'i') {      // Random choice of getting a item
                int rand = (int) (Math.random() * 1);
                if(rand == 0){
                    ash.receivePotion();
                    System.out.println("You have found a potion.");
                } else {
                    ash.recievePokeball();
                    System.out.println("You have found a pokéball.");
                }
                map.removeCharAtLoc(ash.getLocation());
            } else if (event == 'c') {
                System.out.println("You've entered the Saffron City. Where would you like to go?");
                System.out.println("1. POKéMART(store)\n2. POKéMON CENTER(hospital)");
                int trainerOption = CheckInput.getIntRange(1, 2);
                if (trainerOption == 1) {
                    store(ash);
                } else {
                    System.out.println("Welcome to our POKéMON CENTER!\n" +
                            "Okay, I'll take your POKéMON for a few seconds\n" +
                            "Thank you for waiting.\n" +
                            "We've restored your POKéMON to full health.\n" +
                            "We hope to see you again!\n");
                    ash.healAllPokemon();
                }
            } else if (event == 'f'){
                int randomGymLeader = (int) (Math.random() * (4));
                if (randomGymLeader == 1) {
                    System.out.println("Sabrina: I had a vision of your arrival!\n" +
                            "I have had psychic powers since I was a child.\n" +
                            "I first learned how to bend spoons with my mind!\n" +
                            "I dislike fighting, but if you wish, I will show you my powers!\n" +
                            "Gym Leader Sabrina wants to fight!\n");
                } else if (randomGymLeader == 2) {
                    System.out.println("Brock: I'm Brock! I'm Pewter's Gym Leader!\n" +
                            "I believe in rock hard defense and determination!" +
                            "Gym Leader Brock wants to fight!\n");
                } else if (randomGymLeader == 3) {
                    System.out.println("Misty: Hi, you're a new face!\n" +
                            "What's your policy on POKéMON?\n" +
                            "My policy is an all-out offense\n" +
                            "Gym Leader Misty wants to fight!\n");
                }

                Pokemon gymTrainersPokemon = PokemonGenerator.getInstance().generateRandomPokemon(level=+1);
                System.out.println(gymTrainersPokemon.toString() + "\n");

                int gymFighterEncounterChoice;

                while (gymTrainersPokemon.getHp() > 0) {
                    for(int i = 0; i < ash.getNumPokemon(); i++) {
                        if(ash.getPokemon(i).getHp() == 0){
                            System.out.println("All Pokemon your have fainted!\n" +
                                    "All your POKéMON have been restored to full health!\n" +
                                    "And so has the Gym Leader's POKéMON\n" +
                                    "Escaping is not a choice try again!\n");
                            ash.healAllPokemon();
                            gymTrainersPokemon.heal();
                        }
                    }
                    // PRINTS Pokemon Encounter Menu. i.e. 1.)Fight, 2.)Potion, 3.)Ball, 4.)Run
                    System.out.println("What do you want to do?\n1. Fight\n2. Use Potion\n");
                    gymFighterEncounterChoice = CheckInput.getIntRange(1, 2);

                    if (gymFighterEncounterChoice == 1) {   // 1. Fight
                        trainerAttack(ash, gymTrainersPokemon);
                    } else if (gymFighterEncounterChoice == 2) {   // 2. Use Potion
                        // if hasPotion() is true then heal
                        if (ash.hasPotions()) {
                            System.out.println("\nChoose a Pokemon to heal:\n" + ash.getPokemonList());    // Choose Pokemon calls t func
                            int poke = CheckInput.getIntRange(1, ash.getNumPokemon()) - 1;
                            ash.usePotion(poke);
                        } else {
                            System.out.println("You don't have any Potions!\n");
                        }
                    }
                }
                ash.buffAllPokemon();
                System.out.println("You found the finish...\nLoading new area...");
                level++;
                mapType += 1;
                if (mapType == 4) {     // Resets the map count so maps rotate as 1,2,3,1,2,3...
                    mapType = 1;
                }
                map.loadMap(mapType);
            }
        }
    }

    /**
     * Menu for traversing the map or to quit the game
     * @return integer value for choice to move or quit
     */
    public static int mainMenu(){
        System.out.println("Main Menu:\n1. Go North\n" +
                "2. Go South\n3. Go East\n4. Go West\n5. Quit\n");
        int menuChoice = CheckInput.getIntRange(1, 5);
        return menuChoice;
    }



    /**
     * Executes when trainer selects option 1: Fight
     * @param t Trainer object
     * @param wild wild Pokemon encounter
     */
    public static void trainerAttack(Trainer t, Pokemon wild) {
        System.out.println("\nChoose a Pokémon:\n" + t.getPokemonList());    // Poke Index Vals shown starting at 1
        int poke = CheckInput.getIntRange(1, t.getNumPokemon()) - 1;    // checks idx 0 to len(list) and substract 1

        int debuffAshChance = 10;
        int debuffWildChance = 25;
        int rand = (int) (Math.random() * (100+1));     // random number 1-100

        // Attack Player
        if (t.getPokemon(poke).getHp() <= 0) {
            System.out.println(t.getPokemon(poke).getName() + " is unable for battle!");
            t.takeDamage(5);
            System.out.println(wild.getName() + " attacked " + t.getName() + " for 5 Health!\n" + t.toString());
            if (t.getHp() <= 0) {
                System.out.println(t.getName() + " has fainted!\nGame Over!\n");
                System.exit(1);
            }

        } else {
            System.out.println(t.getPokemon(poke).getName() + ", I choose you!");
            // Choose Basic OR Elemental Attack Type Menu
            System.out.println(t.getPokemon(poke).getAttackTypeMenu());
            int atkType = CheckInput.getIntRange(1, t.getPokemon(poke).getNumAttackMenuTypeItems());

            // Call Basic / Elemental Move Menu
            System.out.println(t.getPokemon(poke).getAttackMenu(atkType));
            int move = CheckInput.getIntRange(1, t.getPokemon(poke).getNumAttackMenuItems(atkType));
            System.out.println(t.getPokemon(poke).Attack(wild, atkType, move));

            // Debuffs wild
            if (rand <= debuffWildChance) {
                wild = PokemonGenerator.getInstance().addRandomDebuff(wild);
            }

            // checks if wild hp > 0
            if (wild.getHp() > 0) {
                // wild pokemon attacks back
                int randAtkType = (int) (Math.random() * (2 - 1 + 1)) + 1;  // Random atk type for wild
                int randAtkMove = (int) (Math.random() * (3 - 1 + 1)) + 1;   // Random atk choice for wild

                // wild does random basic OR elem atk
                System.out.println(wild.Attack(t.getPokemon(poke), randAtkType, randAtkMove));

                // Debuffs trainer pokemon
                if (rand <= debuffAshChance){
                    t.debuffPokemon(poke);
                }

                System.out.println(wild.toString() + "\n");     // wild hp after fight
                if (t.getPokemon(poke).getHp() <= 0) {          // checks if trainer pokemon is dead
                    System.out.println(t.getPokemon(poke).getName() + " has fainted!\n");
                }
            } else {
                System.out.println(wild.getName() + " has fainted!\n");
            }
        }
    }

    /**
     * Store executes when trainer location char is 'c'
     * and trainer selects store
     * Allows trainer to buy potions or pokeballs
     * @param t trainer object to check funds and receive items bought
     */
    public static void store(Trainer t) {
        while (true) {
            System.out.println(t.getName() + " currently has $" + t.getMoney() + "\n");
            System.out.println("Hello! What can I help you with?\n" +
                    "1. Buy Potion- $5\n2. Buy Poke Ball - $3\n3. Exit\n");
            int option = CheckInput.getIntRange(1, 3);

            if (option == 1) {
                if (t.getMoney() >= 5) {
                    System.out.println("Here's your potion.\n");
                    t.receivePotion();
                    t.spendMoney(5);
                } else {
                    System.out.println("You don't have enough money to buy a potion\n");
                }
            } else if (option == 2) {
                if (t.getMoney() >= 3) {
                    System.out.println("Here's your Poke ball.\n");
                    t.recievePokeball();
                    t.spendMoney(3);
                } else {
                    System.out.println("You don't have enough money to buy a poke ball\n");
                }
            } else if (option == 3) {
                System.out.println("Thank you, come again soon!\n");
                break;
            }
        }
    }
}