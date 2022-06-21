/**
 * @author      Uddam Bhathal <Uddam.Bhathal@student.tdsb.on.ca>
 * @version     1
 * @since       1
 */
//imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//subclass of Functions
public class SettingArray extends Functions{
    //Initializing variables
    public static int totalMoves;
    public static int score;
    //Creating arraylist for new mine position
    static ArrayList<Integer> minePos = new ArrayList<>();
    //Initializing variables
    public static int hintsRemaining = 3;
    public static int xPos = 0;
    public static int originalYPos = 5;
    public static int originalXPos = 12;
    public static int northPelletCountY = 0;
    public static int northPelletCountX = 0;
    public static int southPelletCountY = 0;
    public static int southPelletCountX = 0;
    static int newPelletXPos = 0;
    static int newPelletYPos = 0;
    public static int originalPelletXPos = 12;
    public static int originalPelletYPos = 5;
    public static int tempX;
    public static int tempY;
    static int yVar = 0;
    static int xVar = 0;
    //end variable used to end the game when true
    public static boolean end = false;
    //Arraylist used for storing directions inputted by the user and using
    // those directions throughout the code for other functions
    static ArrayList<String> directions = new ArrayList<>();
    //Arraylist used for showing the five hints and deducting them to show the new hints after each input
    static ArrayList<Integer> mineDeduct;

    //Default constructor taking number of hints remaining
    public SettingArray(int hintsRemaining){
        //calling super class to set all the variables to their designated abstract methods
        //this also prevents a GUI bug from happening when the mouse button is pressed
        super();
        //setting the hints remaining
        SettingArray.hintsRemaining = hintsRemaining;
    }

    //readAscii class reads through the Ascii file and prints the contents in file
    public static void readAscii() throws FileNotFoundException, InterruptedException {
        Scanner file = new Scanner(new File("Ascii"));
        //looping through the file and printing its contents
        while (file.hasNext()) {
            String l1 = file.nextLine();
            System.out.println(l1);
            Thread.sleep(50);
        }
    }

    //Generating random position for the mines
    public void getRandomValue(){
        //looping through total mines and setting a random position for each mine
        for (int i = 0; i < totalMines(); i++) {
            arr[mineYPos()][mineXPos()] = "0";
            //adding the x and y position of the mines to the arraylist
            minePos.add(mineYPos());
            minePos.add(mineXPos());
        }
    }

    //Setting up the initial array
    public void getInitialArray(){
        //looping through array and setting all indexes to *
        for (int i = 0; i < arr.length; i++) {
            for (int x = 0; x < arr[i].length; x++) {
                arr[i][x] = "*";
            }
        }
    }

    //Displaying Pivot Point
    public void displayPivot(){
        arr[columnLength() / 2][rowLength() / 2] = "O";
    }

    //Printing the array
    public void printArray(){
        //looping through array and printing its contents
        for (int i = 0; i < arr.length; i++) {
            for (int x = 0; x < arr[i].length; x++) {
                System.out.print(arr[i][x]);
            }
            System.out.println();
        }
    }

    //Getting number of Hints
    public void displayHints(){
        //Number of Hints
        System.out.println("Would you like to use a hint? Y - Yes; N - No" + "(" + hintsRemaining + ")");
        //IN TESTER CLASS// //String choiceHint = in.nextLine();
        mineDeduct = minePos;
    }

    //Showing five of the hidden mines and reducing the hints remaining by 1
    public void showFiveHints(){
        hintsRemaining--;
        score += 100;
        //looping five times to show 4-5 hints
        for (int i = 0; i < 5; i++) {
            //going in super class and setting abstract methods to the first index in the array list
            super.getTempYReduce = mineDeduct.get(0);
            mineDeduct.remove(0);
            super.getTempXReduce = mineDeduct.get(0);
            mineDeduct.remove(0);
            //setting the pivot point
            arr[super.getTempYReduce][super.getTempXReduce] = "0";
        }
    }

    //removing pellets (*)
    public void directionalCheckN(){
        //if the pivot point travels the same direction as the last direction chosen, the following will compute
        if (newPelletXPos == 0) {
            //looping through the total shadows produced in the y-axis and setting a new position for the same shadow
            for (int i = 0; i < northPelletCountY; i++) {
                arr[originalPelletYPos - i][originalPelletXPos] = " ";
                newPelletYPos = originalPelletYPos - i;
            }
            //looping through the total shadows produced in the x-axis and setting a new position for the same shadow
            for (int i = 1; i < northPelletCountX + 1; i++) {
                arr[originalPelletYPos][originalPelletXPos - i] = " ";
            }
        }
        //if the pivot point's last direction differs from the current direction chosen, the following will compute
        else {
            //looping through the arraylist of directions and checking the current direction to set shadows accordingly
            for (int i = 0; i < directions.size(); i++) {
                if (i >= 1) {
                    if (directions.get(i).equals("N") && !directions.get(i - 1).equals("E")
                            && !directions.get(i - 1).equals("S") && !directions.get(i - 1).equals("W")) {
                        // Printing " " in y-direction
                        for (int x = 0; x < northPelletCountY; x++) {
                            arr[originalPelletYPos - x][originalPelletXPos] = " ";
                            newPelletYPos = originalPelletYPos - x;
                        }
                    }
                    //checking if the current position is N and the last position is not E
                    if (directions.get(i).equals("N") && directions.get(i - 1).equals("E")) {
                        // Printing " " in y-direction
                        for (int x = 0; x < northPelletCountY; x++) {
                            arr[originalPelletYPos - x][originalPelletXPos] = " ";
                            newPelletYPos = originalPelletYPos - x;
                        }
                    }
                } else {
                    //checking if the current position is just N
                    if (directions.get(i).equals("N")) {
                        // Printing " " in y-direction
                        for (int x = 0; x < northPelletCountY; x++) {
                            arr[originalPelletYPos - x][originalPelletXPos] = " ";
                            newPelletYPos = originalPelletYPos - x;
                        }
                    }
                }
            }
            // Printing " " in x-direction
            for (int i = 1; i < northPelletCountX + 1; i++) {
                arr[originalPelletYPos][originalPelletXPos - i] = " ";
            }
        }
    }

    //looping through the total pellet counts in both x and y direction to display the shadows
    public void directionalCheckN2(){
        //looping through y-direction
        for (int i = 0; i < northPelletCountY; i++) {
            arr[originalPelletYPos - i][newPelletXPos - 1] = " ";
            newPelletYPos = originalPelletYPos - i;
        }
        //looping through x-direction
        for (int i = 1; i < northPelletCountX + 1; i++) {
            arr[newPelletYPos][originalPelletXPos + i] = " ";
        }
    }

    //Method for checking if the current direction chosen is E
    public void directionalCheckE(){
        //increasing the original x-position by 1 index to the right
        originalXPos += 1;
        //setting regular position of pivot point and spaces
        arr[originalYPos][originalXPos] = "O";
        arr[originalYPos][originalXPos - 1] = " ";

        //increasing the pellet count in the x-direction
        northPelletCountX++;
        //removing pellets (*)
        if (northPelletCountY == 0) {
            //looping through the total pellet count in the x-direction and setting the new shadows formed
            for (int i = 0; i < northPelletCountX; i++) {
                arr[originalYPos][originalPelletXPos + i] = " ";
                newPelletXPos = originalPelletXPos + i;
            }
        } else {
            //looping through both the x and y directions to set the original shadows
            for (int i = 0; i < northPelletCountY; i++) {
                arr[originalPelletYPos - i][originalPelletXPos] = " ";
            }
            for (int i = 0; i < northPelletCountX; i++) {
                arr[newPelletYPos - 1][originalPelletXPos + i] = " ";
                newPelletXPos = originalPelletXPos + i;
            }
        }

        //Printing the array
        printArray();
    }

    //Method for checking if the current direction chosen is S
    public void directionalCheckS(){
        //increasing the original Y-position by 1 index to go down the array
        originalYPos += 1;
        //setting regular position of pivot point
        southPelletCountY++;
        arr[originalYPos][originalXPos] = "O";

        //removing pellets (*)
        for (int i = 0; i < southPelletCountY; i++) {
            //looping through the total pellet count in the y-direction and setting the new shadows formed
            arr[originalPelletYPos + i][originalPelletXPos] = " ";
            newPelletYPos = originalPelletYPos + i;
        }

        //Printing the array
        printArray();
    }

    //Method for checking if the current direction chosen is W
    public void directionalCheckW(){
        //decreasing the original X-position by 1 index to go left in the array
        originalXPos -= 1;
        //setting regular position of pivot point
        arr[originalYPos][originalXPos + xPos] = "O";
        southPelletCountX++;

        //removing pellets (*)
        for (int i = 0; i < southPelletCountX; i++) {
            //looping through the total pellet count in the x-direction and setting the new shadows formed
            arr[originalYPos][originalPelletXPos - i] = " ";
            newPelletXPos = originalPelletXPos - i;
        }
        //printing the array
        printArray();
    }

    //Getting the most recent position of the pivot point
    public void mostCurrentPosition(){
        //looping through the array to find the x and y values of the most recent position of the pivot point
        for (int i = 0; i < arr.length; i++) {
            for (int x = 0; x < arr[i].length; x++) {
                if (arr[i][x].equals("O")) {
                    //setting the temp x and temp y variables to the x and y values of the pivots' coordinates
                    tempX = x;
                    tempY = i;
                }
            }
        }
    }

    //Checking if there is a mine at the position of the pivot point
    public void checkMine() {
        //looping through the array to check if the coordinates of the pivot point match coordinates of mine
        for (int i = 0; i < minePos.size(); i += 2) {
            if (tempY == minePos.get(i) && tempX == minePos.get(i + 1)) {
                System.out.println("Oh oh- the mine got us this time :( Another round it is :)");
                //ending the game if this is true
                end = true;
                break;
            }
        }
    }

    //Displaying pivot position through coordinates on the screen
    public void pivotPosition(){
        //looping through the array to constantly display the updated position of the pivot
        for (int i = 0; i < arr.length; i++) {
            for (int x = 0; x < arr[i].length; x++) {
                //checking if the current position is the position of the pivot
                if (arr[i][x].equals("O")) {
                    System.out.println("\n"+"                    y   x");
                    System.out.println("Current Position -> "+i + " : " + x);
                    xVar = x;
                    yVar = i;
                }
            }
        }
    }

    //Checking if the user has won the game and displaying Ascii art with three different endings and gui
    public void winnerCheck() throws FileNotFoundException, InterruptedException {
        System.out.println("Nice Win!!");
        //displaying ascii art
        readAscii();
        //displaying three different endings
        if (score == 0) {
            System.out.println("You truly are a champion!");
        } else if (score == 100 || score == 200) {
            System.out.println("Well played, keep at it partner!");
        } else {
            System.out.println("Hints are always great... But no hints are better!");
        }
        //displaying gui
        GUI.gui();
    }

    //Searching the boundaries of the array to check if the pivot point is there (linear search)
    public static void linearSearch(String[][]arr, String target1, String target2) {
        //looping through array to check if any of the boundaries contain a shadow or the pivot point
        for (int i = 0; i < arr.length; i++) {
            for (int x = 0; x < arr[i].length; x++) {
                //checking conditions mentioned above
                if (arr[i][24].equals(target2) || arr[i][24].equals(target1)
                        || arr[i][0].equals(target2) || arr[i][0].equals(target1)
                        || arr[9][x].equals(target2) || arr[9][x].equals(target1)
                        || arr[0][x].equals(target2) || arr[0][x].equals(target1)) {
                    break;
                }
            }
        }
    }

    //Overriding the abstract methods and returning specified values
    @Override
    public int tempXReduce() {
        return 0;
    }

    @Override
    public int tempYReduce() {
        return 0;
    }

    @Override
    public int hintsRemaining() {
        return hintsRemaining;
    }

    @Override
    public int score() {
        return score;
    }
    @Override
    public int mineYPos(){
        return (int) Math.floor(Math.random() * (9 + 1) + 0);
    }

    @Override
    public int mineXPos(){
        return (int) Math.floor(Math.random() * (24 + 1) + 0);
    }

    @Override
    public int totalMines(){
        return (int) Math.floor(Math.random() * (40 - 35 + 1) + 35);
    }

    @Override
    public int columnLength(){
        return arr.length; //10;
    }

    @Override
    public int rowLength(){
        return arr[0].length; //25;
    }
}