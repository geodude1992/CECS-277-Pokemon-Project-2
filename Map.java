import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Point;
/**
 * Aggregation – is the case where class A 'has' one or more references to class B.
 * An instance of class B is created elsewhere and then passed in and the reference
 * is set.  Then, if class A is ever destroyed, any objects of type B that class A
 * referenced will continue to exist wherever they were created.
 *
 * @author Jovanni Garcia
 * @created 10/06/2021 - 06:37 PM
 * @project 277 Project 1
 */
/*
    Singleton: Restricts the instantiation of a class to only ONE single instance.
    Design pattern: Hide the constructor of the class, to ensure that only one
        instance of the singleton class ever exists.

    Provides global access to that instance; an operation that returns the sole
        instance of the class.

    Implementation:
        Declaring all constructors of the class to be private.
        Providing a static method that returns a reference of the instance.
 */
public class Map {

    /**
     * map - Map of dungeon
     */
    private char[][] map;

    /**
     * Map of what spaces the user can see while playing game
     */
    private boolean [][] revealed;

    /**
     * Instance variable that contains an instance of a Map object
     */
    private static Map INSTANCE = null;     // Lazy Initialisation (single thread):

    /**
     * Hidden constructor so only one Map is instantiated.
     */
    private Map(){
        final int MAP_DIMENSION = 5;
        final int STARTING_MAP = 1;

        map = new char[MAP_DIMENSION][MAP_DIMENSION];   // Used to store file
        revealed = new boolean[MAP_DIMENSION][MAP_DIMENSION];   // Used to display file
        loadMap(STARTING_MAP);
    }

    /**
     *
     * @return a reference of the instance
     */
    public static Map getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Map();
            }
        return INSTANCE;
    }
    /**
     * Map read contents file and store the contents of the area files in the 2D map array
     * @param mapNum integer value that corresponds to the map that will be displayed
     */
    public void loadMap(int mapNum){

        if (mapNum == 1) {
            // 1. then also initialize the revealed array to all false.
            for(int i=0; i < revealed.length; i++) {
                for(int j=0; j < revealed[0].length; j++) {
                    revealed[i][j] = false;
                }
            }
            // Declare the Scanner and File –
            // Java requires that a FileNotFoundException be handled,
            // so a try/catch block is needed.
            try {
                // Create a File object representing your required file.
                File file = new File("Area1.txt");
                // Create a Scanner object by passing the above created file object.
                Scanner readFile = new Scanner(file);
                // StringBuffer to store the contents
                //StringBuffer storeInStr = new StringBuffer();
                // The hasNext() verifies whether the file has another line
                while (readFile.hasNextLine()) {
                    // the nextLine() method reads and returns the next line in the file.
                    for (int row = 0; row < map.length; row++) {
                        String line = readFile.nextLine();
                        line = line.replace(" ", "");
                        for (int col = 0; col < map[0].length; col++) {
                            map[row][col] = line.charAt(col);
                        }
                    }
                }
            } catch (FileNotFoundException fnf) {
                System.out.println("File was not found.");
            }
        } else if (mapNum == 2) {
            for(int i=0; i < revealed.length; i++) {
                for(int j=0; j < revealed[0].length; j++) {
                    revealed[i][j] = false;
                }
            }
            try {
                File file = new File("Area2.txt");
                Scanner readFile = new Scanner(file);
                while (readFile.hasNextLine()) {
                    for(int row=0; row < map.length; row++) {
                        String line = readFile.nextLine();
                        line = line.replace(" ", "");
                        for (int col = 0; col < map[0].length; col++) {
                            map[row][col] = line.charAt(col);
                        }
                    }
                }
            } catch(FileNotFoundException fnf) {
                System.out.println("File was not found.");
            }
        } else if (mapNum == 3) {
            for(int i=0; i < revealed.length; i++) {
                for(int j=0; j < revealed[0].length; j++) {
                    revealed[i][j] = false;
                }
            }
            try {
                File file = new File("Area3.txt");
                Scanner readFile = new Scanner(file);
                while (readFile.hasNextLine()) {
                    for(int row=0; row<map.length; row++) {
                        String line = readFile.nextLine();
                        line = line.replace(" ", "");
                        for (int col = 0; col < map[0].length; col++) {
                            map[row][col] = line.charAt(col);
                        }
                    }
                }
            } catch(FileNotFoundException fnf) {
                System.out.println("File was not found.");
            }
        }
    }

    /**
     * Takes in a point and returns the char at that location
     * @param p Point
     * @return char
     */
    public char getCharAtLoc(Point p) {
        return map[p.x][p.y];
    }


    /**
     * Use the boolean 2D revealed array to decide whether a space on the
     * map is shown or hidden when writing the mapToString method
     * (which also displays the trainer’s position as a * using the
     * Point location passed in as a parameter)
     * @param p Point location of player
     * @return String Grid of map
     */
    public String mapToString(Point p) {
        // take in a point x, y
        // returns
        StringBuilder mapStr = new StringBuilder();
        for(int i=0; i < map.length; i++) {         // num of rows
            for(int j=0; j < map[0].length; j++) {  // num of colms
                //if reveal[i][j] is true
                if (i == p.x && j == p.y) {
                    mapStr.append("*" + " ");
                } else if (revealed[i][j]) {
                    mapStr.append(map[i][j] + " ");
                } else { //else append z
                    // 1. The contents of the map is
                    //initially hidden from the player
                    mapStr.append("x" + " ");
                }   // 2. is revealed as the trainer explores the map by calling
                // the reveal method
            }
            mapStr.append("\n");
        }
        // set point to revealed
        reveal(p);
        return mapStr.toString();
    }

    /**
     * Returns the starting point for Trainer
     * @return point
     */
    public Point findStart() {
        Point start = new Point(0,0);
        for(int i=0; i < map.length; i++) {
            for(int j=0; j < map[0].length; j++) {
                if(map[i][j] == 's') {
                    start = new Point(i, j);
                    return start;
                }
            }
        }
        reveal(start);
        return start;
    }

    /**
     * Sets the reveal array point as true
     * @param p Point
     */
    public void reveal(Point p) {
        // reveal players location
        // sets revealed array position to true when called
       revealed[p.x][p.y] = true;
    }

    /**
     * Sets the point char to nothing
     * @param p Point
     */
    public void removeCharAtLoc(Point p) {
        map[p.x][p.y] = 'n';
    }
}
