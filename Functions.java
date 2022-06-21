/**
 * @author      Uddam Bhathal <Uddamsingh14@gmail.com>
 * @version     1
 * @since       1
 */
//Creating abstract class to be used throughout the program
public abstract class Functions {
    //Initializing array for the board
    public static String[][] arr = new String[10][25];
    //Initializing variables
    public int getTempXReduce;
    public int getTempYReduce;

    //Default constructor used to set variables to their abstract methods
    public Functions() {
        int totalMines = getTotalMines();
        int mineXPos = getMineXPos();
        int mineYPos = getMineYPos();
        int columnLength = arr.length;
        int rowLength = arr[0].length;
        int tempXReduce = tempXReduce();
        int tempYReduce = tempYReduce();
        int hintsRemaining = hintsRemaining();
    }

    //Initializing abstract methods
    public abstract int tempXReduce();
    public abstract int tempYReduce();
    public abstract int hintsRemaining();
    public abstract int score();
    public abstract int mineYPos();
    public abstract int mineXPos();
    public abstract int totalMines();
    public abstract int columnLength();
    public abstract int rowLength();

    //Setting the static variables used throughout the SettingArray class
    private static int getTotalMines(){
        //Getting random number for number of mines on the board (can be adjusted for difficulty)
        return (int) Math.floor(Math.random() * (40 - 35 + 1) + 35);
    }
    private static int getMineXPos(){
        //Getting x position of the mine used
        return (int) Math.floor(Math.random() * (24 + 1) + 0);
    }
    private static int getMineYPos(){
        //Getting y position of the mine used
        return (int) Math.floor(Math.random() * (9 + 1) + 0);
    }
}
