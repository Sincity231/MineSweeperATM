/**
 * @author      Uddam Bhathal <Uddamsingh14@gmail.com>
 * @version     1
 * @since       1
 */
//imports
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//GUI class implements actionListener method for its use in button presses
public class GUI implements ActionListener {
    //Initializing static variables used throughout the methods
    static boolean login = false;
    private static JLabel label;
    private static JTextField textField;
    private static JLabel secondLabel;
    private static JTextField passwordField;
    private static JLabel valid;

    public static void gui(){
        //Creating a frame
        JFrame frame = new JFrame();
        //Creating a panel
        JPanel panel = new JPanel();

        //Setting size of frame to 100px by 100px
        frame.setSize(500, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Putting panel on top of frame
        frame.add(panel);

        //setting layout of panel without rows/columns
        panel.setLayout(null);

        //Creating a label (setting text inside label)
        label = new JLabel("  Username");
        //adding padding to the panel for visuals
        label.setBounds(10, 20, 80, 25);
        //adding label to panel
        panel.add(label);

        //Creating text field (setting length of text field)
        textField = new JTextField(20);
        textField.setBounds(110, 20, 170, 30);
        //adding text field to the panel
        panel.add(textField);

        //Creating second label (setting text inside the label)
        secondLabel = new JLabel("Password ");
        secondLabel.setBounds(15, 50, 80, 30);
        panel.add(secondLabel);

        //Creating second text field (setting length of text field)
        passwordField = new JPasswordField();
        passwordField.setBounds(110, 50, 170, 30);
        panel.add(passwordField);

        //Creating a button
        JButton button = new JButton("Sign Up");
        //Adding action to button
        button.addActionListener(new GUI());
        panel.add(button);
        button.setBounds(15, 85, 85, 30);

        //Adding Label for successful attempt
        valid = new JLabel("");
        valid.setBounds(15, 110, 500, 30);
        panel.add(valid);

        //Allowing frame to be visible
        frame.setVisible(true);
    }

    //When button is pressed, actionPerformed class will run
    @Override
    public void actionPerformed(ActionEvent e) {
        //creating a user and password field on the panel
        String user = textField.getText();
        String password = passwordField.getText();

        //checking if the correct password and username is entered
        if(user.equals(TesterClass.username1) && password.equals(TesterClass.password1)){
            valid.setText("Login Confirmed! You may return to your console. (Don't close the GUI)");
            login = true;

            TesterClass.userInput();
        }
        else{
            valid.setText("Try again! (Don't close the GUI :D (or else the game will end lol))");
        }
    }
}
