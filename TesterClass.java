/**
 * @author      Uddam Bhathal <Uddam.Bhathal@student.tdsb.on.ca>
 * @version     1
 * @since       1
 */
//imports
import java.io.FileNotFoundException;
import java.util.Scanner;

//TesterClass
public class TesterClass {
    //Initializing static methods for username1, password1, and hintsRemaining
    static String username1 = "";
    static String password1 = "";
    static int hintsBefore = 0;
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        Scanner in = new Scanner(System.in);
        //Creating an object s and taking in 3 hints remaining for the SettingArray subclass
        SettingArray s = new SettingArray(3);

        //printing the rules
        System.out.println("//RULES//");
        Thread.sleep(1000);
        System.out.println("Welcome\t\tto\t\tEscape\t\tArcade!!");
        Thread.sleep(1000);
        System.out.println("Currently, we only have one game but more are soon to come!");
        Thread.sleep(1000);
        System.out.println("Mine sweeper, a classic, but with a twist...");
        Thread.sleep(1000);
        System.out.println("You must reach the edge of the board, without stepping on any mines!");
        Thread.sleep(1000);
        System.out.println("Every time you move, a shadow follows you and is formed.");
        Thread.sleep(1000);
        System.out.println("You may also become the shadow by going into it using directions that include North, East, South, and West.");
        Thread.sleep(1000);
        System.out.println("But be careful, staying as a shadow may cause you to lose.");
        Thread.sleep(1000);
        System.out.println("Luckily, you can also get out from the shadow state using directions including North, East, South, and West.");
        Thread.sleep(1000);
        System.out.println("If the shadow reaches the edge of the board before you do, then there is a chance that you lose, even if you are in the shadow.");
        Thread.sleep(1000);
        System.out.println("At the start of the game, you will be prompted to create a username and password.");
        Thread.sleep(1000);
        System.out.println("This info will be handy later on when you need to access the hidden arcade shop!");
        Thread.sleep(1000);
        System.out.println("During your mine sweeper quest, you will have a total of three hints.");
        Thread.sleep(1000);
        System.out.println("Each time you use a hint, your score increases. You want the lowest score possible to get the best of three endings!\nUsing a hint also displays the position of 4-5 random mines each time.");
        Thread.sleep(1000);
        System.out.println("To make things interesting, the more moves you make over the board to reach the edge, the higher your balance will be!");
        Thread.sleep(1000);
        System.out.println("A higher balance means you can buy the best items from the hidden arcade in the future!");
        Thread.sleep(1000);
        System.out.println("Good luck on your adventure, safe travels...");
        Thread.sleep(1000);

        //printing info on user and pass and taking those inputs
        System.out.println();
        System.out.println("Now that we have the rules out of the way, let's set up your ATM info!");
        System.out.println("Choose a username for your ATM, this will be used after your game to redeem prizes!");
        username1 = in.nextLine();
        System.out.println("Now choose a password for luck!");
        password1 = in.nextLine();

        //Generating total number of mines
        System.out.println("Total Mines: " + s.totalMines());

        //Generating random position for the mines
        s.getRandomValue();

        //Setting up the initial array
        s.getInitialArray();

        //Displaying Pivot Point
        s.displayPivot();

        //Printing the array
        s.printArray();

        //Getting number of Hints
        s.displayHints();
        String choiceHint = in.nextLine();
        while(true){
            if(!choiceHint.equals("N") && !choiceHint.equals("Y")){
                System.out.println("Please enter a valid response.");
                s.displayHints();
                choiceHint = in.nextLine();
            }
            else{
                break;
            }
        }

        //Setting up the initial array
        s.getInitialArray();

        //Displaying Pivot Point
        s.displayPivot();

        //Showing five of the hidden mines and reducing the hints remaining by 1
        if (choiceHint.equals("Y") && s.hintsRemaining() > 0) {
            s.showFiveHints();
        }

        //Printing the array
        s.printArray();

        //Printing the score
        System.out.println("SCORE: " + s.score());

        //Getting direction from user
        System.out.println("Which direction does pacman want to proceed towards? (N - North; E - East; S - South; W - West; end - end game");
        String pos = in.nextLine();
        //while loop used to check if the user does not enter any of the correct inputs
        while(true){
            if(!pos.equals("N") && !pos.equals("E") && !pos.equals("S") && !pos.equals("W") && !pos.equals("end")){
                System.out.println("Please enter a valid response.");
                System.out.println("Which direction does pacman want to proceed towards? (N - North; E - East; S - South; W - West; end - end game");
                pos = in.nextLine();
            }
            else{
                break;
            }
        }

        //setting the current position to the last position
        String lastPos = pos;

        //if the position does not equal "end", the loop continues
        if (!pos.equals("end")) {
            do {
                try {
                    if (pos.equals("N")) {
                        SettingArray.directions.add("N");
                        //Setting up the initial array
                        s.getInitialArray();
                        //Decrementing y-position by 1
                        SettingArray.originalYPos -= 1;
                        Functions.arr[SettingArray.originalYPos][SettingArray.originalXPos] = "O";

                        //removing pellets (*)
                        SettingArray.northPelletCountY++;
                        if (lastPos.equals(pos)) {
                            //if pivot point travels the same direction as the last direction chosen, the following will compute
                            s.directionalCheckN();
                        } else {
                            if (lastPos.equals("S")) {
                                SettingArray.northPelletCountY++;
                                SettingArray.newPelletXPos = 13;
                            }
                            //removing pellets (*) if last direction is south
                            s.directionalCheckN2();
                        }
                        //Printing the array
                        s.printArray();
                        SettingArray.totalMoves++;
                    }
                    //if the position is "E", E will be added to directions array list and the shadows will be displayed
                    else if (pos.equals("E")) {
                        SettingArray.directions.add("E");
                        s.getInitialArray();
                        s.directionalCheckE();
                        SettingArray.totalMoves++;
                    }
                    //if the position is "S", S will be added to directions array list and the shadows will be displayed
                    else if (pos.equals("S")) {
                        SettingArray.directions.add("S");
                        s.getInitialArray();
                        s.directionalCheckS();
                        SettingArray.totalMoves++;
                    }
                    //if the position is "W", W will be added to directions array list and the shadows will be displayed
                    else if (pos.equals("W")) {
                        SettingArray.directions.add("W");
                        s.getInitialArray();
                        s.directionalCheckW();
                        SettingArray.totalMoves++;
                    }

                    //Taking most current position of pivot (O)
                    s.mostCurrentPosition();

                    //Checking if user has stepped over the mine
                    s.checkMine();

                    if (SettingArray.end) {
                        break;
                    }

                    //Displaying pivot position
                    s.pivotPosition();

                    if (SettingArray.yVar == 0 || SettingArray.yVar == 9 || SettingArray.xVar == 0 || SettingArray.xVar == 24) {
                        //Checking if the user has won
                        s.winnerCheck();
                        break;
                    }
                    //Checking boundaries for pivot point or shadow
                    SettingArray.linearSearch(Functions.arr, "O", " ");

                    //prompting user for hints
                    if (SettingArray.hintsRemaining > 0) {
                        System.out.println("Would you like to use a hint? Y - Yes; N - No" + "(" + SettingArray.hintsRemaining + ")");
                        choiceHint = in.nextLine();
                        //checking if the user does not enter any of the listed inputs
                        while(true){
                            if(!choiceHint.equals("N") && !choiceHint.equals("Y")){
                                System.out.println("Please enter a valid response.");
                                s.displayHints();
                                choiceHint = in.nextLine();
                            }
                            else{
                                break;
                            }
                        }
                    }

                    //setting the pivot point ot the new coordinates after the user has moved
                    Functions.arr[SettingArray.tempY][SettingArray.tempX] = "O";

                    //Showing five of the hidden mines and reducing the hints remaining by 1
                    if (choiceHint.equals("Y") && SettingArray.hintsRemaining > 0) {
                        s.showFiveHints();
                    }

                    //Printing the array
                    if(SettingArray.hintsRemaining>=0&&hintsBefore!=SettingArray.hintsRemaining){
                        hintsBefore = SettingArray.hintsRemaining;
                        s.printArray();
                    }

                    //Displaying score
                    System.out.println("SCORE:" + SettingArray.score);

                    //setting current position to last position
                    lastPos = pos;
                    //prompting user for direction
                    System.out.println("Which direction does pacman want to proceed towards? (N - North; E - East; S - South; W - West; end - end game");
                    pos = in.nextLine();
                    //Checking if the user does not enter one of the listed inputs
                    while(true){
                        if(!pos.equals("N") && !pos.equals("E") && !pos.equals("S") && !pos.equals("W") && !pos.equals("end")){
                            System.out.println("Please enter a valid response.");
                            System.out.println("Which direction does pacman want to proceed towards? (N - North; E - East; S - South; W - West; end - end game");
                            pos = in.nextLine();
                        }
                        else{
                            break;
                        }
                    }

                    //ending the program if the user enters "end"
                    if (pos.equals("end")) {
                        break;
                    }
                } catch (ArrayIndexOutOfBoundsException e) { //catch block will execute the following if an error occurs
                    s.printArray();
                    System.out.println("Nice try..One more game? :)");
                    break;
                }
                if (SettingArray.end) {
                    break;
                }
            } while (true); //do loop repeats
        }
    }
    //userInput class is tied with the ATM class
    public static void userInput(){
        Scanner in = new Scanner(System.in);
        //Creating a new ATM object
        ATM a = new ATM();

        //info
        System.out.println("Current Balance: $" + ATM.arcadePoints);

        System.out.println("Sea Lions($150): 1; MST Cake($300): 2; Ice Cream Sandwich($50): 3; Hire Clown($1): 4; Print Final Balance: 5; Exit Arcade: 6");

        //prompting user for their choice of prize
        int choice = in.nextInt();
        while(true){
            //Checking if the user does not enter any of the listed inputs
            if(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=5 && choice!=6){
                System.out.println("Please enter a valid response.");
                System.out.println("Sea Lions($150): 1; MST Cake($300): 2; Ice Cream Sandwich($50): 3; Hire Clown($1): 4; Print Final Balance: 5; Exit Arcade: 6");
                choice = in.nextInt();
            }
            else{
                break;
            }
        }

        //while the user does not want to exit the arcade
        while (choice != 6) {
            //if they enter "1", the BabySeaLion method is called to print additional info
            if (choice == 1) {
                System.out.println(a.buyBabySeaLion());
                System.out.println("Sea Lions($150): 1; MST Cake($300): 2; Ice Cream Sandwich($50): 3; Hire Clown($1): 4; Print Final Balance: 5; Exit Arcade: 6");
                choice = in.nextInt();
                //Checking if the user does not enter any of the listed inputs
                while(true){
                    if(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=5 && choice!=6){
                        System.out.println("Please enter a valid response.");
                        System.out.println("Sea Lions($150): 1; MST Cake($300): 2; Ice Cream Sandwich($50): 3; Hire Clown($1): 4; Print Final Balance: 5; Exit Arcade: 6");
                        choice = in.nextInt();
                    }
                    else{
                        break;
                    }
                }
            }
            //if they enter "2", the buyMSTCAKE method is called to print additional info
            if (choice == 2) {
                System.out.println(a.buyMSTCAKE());
                System.out.println("Sea Lions($150): 1; MST Cake($300): 2; Ice Cream Sandwich($50): 3; Hire Clown($1): 4; Print Final Balance: 5; Exit Arcade: 6");
                choice = in.nextInt();
                //Checking if the user does not enter any of the listed inputs
                while(true){
                    if(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=5 && choice!=6){
                        System.out.println("Please enter a valid response.");
                        System.out.println("Sea Lions($150): 1; MST Cake($300): 2; Ice Cream Sandwich($50): 3; Hire Clown($1): 4; Print Final Balance: 5; Exit Arcade: 6");
                        choice = in.nextInt();
                    }
                    else{
                        break;
                    }
                }
            }
            //if they enter "3", the buyIceCreamSandwich method is called to print additional info
            if (choice == 3) {
                System.out.println(a.buyIceCreamSandwich());
                System.out.println("Sea Lions($150): 1; MST Cake($300): 2; Ice Cream Sandwich($50): 3; Hire Clown($1): 4; Print Final Balance: 5; Exit Arcade: 6");
                choice = in.nextInt();
                //Checking if the user does not enter any of the listed inputs
                while(true){
                    if(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=5 && choice!=6){
                        System.out.println("Please enter a valid response.");
                        System.out.println("Sea Lions($150): 1; MST Cake($300): 2; Ice Cream Sandwich($50): 3; Hire Clown($1): 4; Print Final Balance: 5; Exit Arcade: 6");
                        choice = in.nextInt();
                    }
                    else{
                        break;
                    }
                }
            }
            //if they enter "4", the hireClown method is called to print additional info
            if (choice == 4) {
                System.out.println(a.hireClown());
                System.out.println("Sea Lions($150): 1; MST Cake($300): 2; Ice Cream Sandwich($50): 3; Hire Clown($1): 4; Print Final Balance: 5; Exit Arcade: 6");
                choice = in.nextInt();
                //Checking if the user does not enter any of the listed inputs
                while(true){
                    if(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=5 && choice!=6){
                        System.out.println("Please enter a valid response.");
                        System.out.println("Sea Lions($150): 1; MST Cake($300): 2; Ice Cream Sandwich($50): 3; Hire Clown($1): 4; Print Final Balance: 5; Exit Arcade: 6");
                        choice = in.nextInt();
                    }
                    else{
                        break;
                    }
                }
            }
            //if they enter "5", the getReceipt method is called to print additional info
            if (choice == 5) {
                System.out.println(a.getReceipt());
                System.out.println("Sea Lions($150): 1; MST Cake($300): 2; Ice Cream Sandwich($50): 3; Hire Clown($1): 4; Print Final Balance: 5; Exit Arcade: 6");
                choice = in.nextInt();
                //Checking if the user does not enter any of the listed inputs
                while(true){
                    if(choice!=1 && choice!=2 && choice!=3 && choice!=4 && choice!=5 && choice!=6){
                        System.out.println("Please enter a valid response.");
                        System.out.println("Sea Lions($150): 1; MST Cake($300): 2; Ice Cream Sandwich($50): 3; Hire Clown($1): 4; Print Final Balance: 5; Exit Arcade: 6");
                        choice = in.nextInt();
                    }
                    else{
                        break;
                    }
                }
            }
        }
        //exiting the game and closing the program
        System.out.println("ATM closed. Thanks, come again!");
        System.exit(0);
    }
}