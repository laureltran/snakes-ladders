import javax.swing.JButton;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;

/**
 * Brief description
 * @author      Van Anh Tran <102421412>
 * @version     1      
 * Purpose      Project 2: Snakes & Ladders Game - Die class to represent die object
*/

public class Die extends JButton {

    /**
     * Constructor
     */
    public Die() {
        // Initialise the die image when it hasn't been rolled
        super(new ImageIcon("media/die/0.jpg"));
    }

    /**
     * Method to roll the die
     * @return intValue (int)
     */
    public int roll() {
        // Get a random number from 1 to 6
        Random rand = new Random();
        int intValue = rand.nextInt(6) + 1;
        // Set the corresponding die image
        String textValue = String.valueOf(intValue);
        this.setIcon(new ImageIcon("media/die/" + textValue + ".jpg"));
        return intValue;
    }

    /**
     * Method to reset all attributes to initial
     */
    public void reset() {
        this.setIcon(new ImageIcon("media/die/0.jpg"));
        for (ActionListener act : this.getActionListeners()) {
            this.removeActionListener(act);
        }
    }
}