import javax.swing.JButton;

/**
 * Brief description
 * @author      Van Anh Tran <102421412>
 * @version     1      
 * Purpose      Project 2: Snakes & Ladders Game - Human class to represent Human object, extends Player class
*/

public class Human extends Player {

    /**
     * Constructor
     */
    public Human() {
        super();
        // Set default initial flag
        this.flag = true;
        // Create GUI and set colour
        this.gui = new JButton("Your turn");
        this.name = "You";
        setGuiColour();
    }

    /**
     * Method to reset all attributes to initial
     */
    public void reset() {
        super.reset();
        this.flag = true;
        setGuiColour();
    }
}