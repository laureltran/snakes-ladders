import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;

/**
 * Brief description
 * @author      Van Anh Tran <102421412>
 * @version     1      
 * Purpose      Project 2: Snakes & Ladders Game - Cell class to represent the cell (in the board) object
*/

public class Cell extends JButton {
    private int value; // The number on the cell
    private JPanel humanPanel; // To represent human piece
    private JPanel computerPanel; // To represent computer piece

    /**
     * Purpose: Constructor
     * @param index (int)
     */
    public Cell(int index) {
        this.value = index;
        // Set GUI layout
        setLayout(new BorderLayout());
        // Add human piece and computer piece onto cell
        humanPanel = new JPanel();
        computerPanel = new JPanel();
        humanPanel.setOpaque(false);
        computerPanel.setOpaque(false);
        add(humanPanel, BorderLayout.NORTH);
        add(computerPanel, BorderLayout.SOUTH);
    }

    /**
     * Purpose: Return cell's value
     * @return value (int)
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Purpose: Set colour for cell
     * @param player (Player)
     */
    public void setColour(Player player) {
        if (player instanceof Human == true) {
            humanPanel.setBackground(Color.RED);
            humanPanel.setOpaque(true);
        }
        else {
            computerPanel.setBackground(Color.BLUE);
            computerPanel.setOpaque(true);
        }
    }

    /**
     * Purpose: Reset colour for cell (back to initialised colour)
     * @param player (Player)
     */
    public void resetColour(Player player) {
        if (player instanceof Human == true) {
            humanPanel.setBackground(Color.WHITE);
            humanPanel.setOpaque(true);
        }
        else {
            computerPanel.setBackground(Color.WHITE);
            computerPanel.setOpaque(true);
        }
    }
}