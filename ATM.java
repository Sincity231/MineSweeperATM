/**
 * @author      Uddam Bhathal <Uddam.Bhathal@student.tdsb.on.ca>
 * @version     1
 * @since       1
 */
//Separate class that is not in the hierarchy
public class ATM{
    //static variable keeps track of arcade points based on total moves made in the game
    public static int arcadePoints = SettingArray.totalMoves*50;

    //String method displays info on sea lions based on if the user wants to purchase one
    public String buyBabySeaLion(){
        //info
        System.out.print("Sea lions! Deposit 150 for a beautiful baby sea lion!");
        //if the user has >= 150 arcade points, 150 arcade points are deducted
        if(arcadePoints >= 150) {
            arcadePoints -= 150;
            return " Payment Complete! This baby sea lion is all yours :)\n" + "Balance remaining: $" + arcadePoints;
        }
        else{
            return "Not enough arcade points.. But there's some other great items waiting to be yours!\n" + "Balance remaining: $" + arcadePoints;
        }
    }
    //String method displays info on MST Cake based on if the user wants to purchase one
    public String buyMSTCAKE(){
        //info
        System.out.print("MST Cake... this must be something big!");
        //if the user has >= 300 arcade points, 300 arcade points are deducted
        if(arcadePoints >= 300){
            arcadePoints-=300;
            return " MST Cake best cake (Mrs Hwang's favourite!)\n" + "Balance remaining: $" + arcadePoints;
        }
        else{
            return " Not enough arcade points.. But there's some other great items waiting to be yours!\n" + "Balance remaining: $" + arcadePoints;
        }
    }
    //String method displays info on IceCream Sandwich based on if the user wants to purchase one
    public String buyIceCreamSandwich(){
        //info
        System.out.print("More ice cream is always a good choice!");
        //if the user has >= 50 arcade points, 50 arcade points are deducted
        if(arcadePoints >= 50){
            arcadePoints -= 50;
            return " Ice cream, you scream, we all scream for ICE CREAM MAN!\n" + "Balance remaining: $" + arcadePoints;
        }
        else{
            return " Not enough arcade points.. But there's some other great items waiting to be yours!\n" + "Balance remaining: $" + arcadePoints;
        }
    }
    //String method displays info on hiring a clown based on if the user wants one lol
    public String hireClown(){
        //info
        System.out.print("Clowns might be scary, but not this one! (I think...)");
        //if the user has >= 1 arcade point, 1 arcade point is deducted
        if(arcadePoints >= 1){
            arcadePoints -= 1;
            return " Want to hear a joke? Penny-wise loves balloons!!\n" + "Balance remaining: $" + arcadePoints;
        }
        else{
            return " Not enough arcade points.. But there's some other great items waiting to be yours!\n" + "Balance remaining: $" + arcadePoints;
        }
    }
    //String method displays the final balance
    public String getReceipt(){
        return " Final Balance: $" + arcadePoints;
    }
}