import javax.swing.JButton;

/**
 * Brief description
 * @author      Van Anh Tran <102421412>
 * @version     1      
 * Purpose      Project 2: Snakes & Ladders Game - Computer class to represent Computer object, extends Player class
*/

public class Computer extends Player {

    /**
     * Constructor
     */
    public Computer() {
        super();
        // Set default initial flag
        this.flag = false;
        // Create GUI and set colour
        this.gui = new JButton("Computer turn");
        this.name = "Computer";
        setGuiColour();
    }

    /**
     * Method to reset all attributes to initial
     */
    public void reset() {
        super.reset();
        this.flag = false;
        setGuiColour();
    }
}