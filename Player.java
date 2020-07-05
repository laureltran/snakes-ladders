import java.awt.Color;
import javax.swing.JButton;

/**
 * Brief description
 * @author      Van Anh Tran <102421412>
 * @version     1      
 * Purpose      Project 2: Snakes & Ladders Game - Player class to represent player object
*/

public class Player {
    protected boolean flag; // Flag to indicate when it's player's turn
    protected JButton gui; // GUI component to represent the player
    protected int dieValue; // Die value got after rolling the die
    protected Cell currentCell; // Player's position is at this cell
    protected int currentCellNo; // Index of the current cell
    protected String name; // Name of player

    /**
     * Contructor
     */
    public Player() {
        this.currentCellNo = 0;
        this.currentCell = null;
        this.dieValue = 0;
    }

    /**
     * Method to return player's name
     * @return name (String)
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method to set colour for GUI to indicate player's turn
     */
    public void setGuiColour() {  
        if (this.flag == true) {
            gui.setBackground(Color.GREEN);
            gui.setOpaque(true);
            gui.setBorderPainted(false);
        }
        else {
            gui.setBackground(Color.WHITE);
            gui.setOpaque(true);
            gui.setBorderPainted(false);
        }
    }

    /**
     * Method to switch the flag value to indicate player's turn
     */
    public void switchFlag() {
        if (this.flag == true) {
            this.flag = false;
            setGuiColour();
        }
        else {
            this.flag = true;
            setGuiColour();
        }
    }

    /**
     * Method to set a specific flag value to indicate player's turn
     * @param flag
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
        setGuiColour();
    }

    /**
     * Method to get the flag value
     * @return flag (boolean)
     */
    public boolean getFlag() {
        return this.flag;
    }

    /**
     * Method to set a specific die value
     * @param value (int)
     */
    public void setDieValue(int value) {
        this.dieValue = value;
    }

    /**
     * Method to return the die value
     * @return dieValue (int)
     */
    public int getDieValue() {
        return dieValue;
    }

    /**
     * Method to return the GUI
     * @return gui (JButton)
     */
    public JButton getGui() {
        return this.gui;
    }

    /**
     * Method to return the current cell's index
     * @return currentCellNo (int)
     */
    public int getCurrentCellNo() {
        return this.currentCellNo;
    }

    /**
     * Method to set the current cell's index with a specific value
     * @param value (int)
     */
    public void setCurrentCellNo(int value) {
        this.currentCellNo = value;
    }

    /**
     * Method to set the current cell's index by adding the die value
     */
    public void setCurrentCellNo() {
        this.currentCellNo += this.dieValue;
    }

    /**
     * Method to return current cell
     * @return currentCell (Cell)
     */
    public Cell getCurrentCell() {
        return this.currentCell;
    }

    /**
     * Method to set current cell
     * @param cell (Cell)
     */
    public void setCurrentCell(Cell cell) {
        this.currentCell = cell;
    }

    /**
     * Method to reset all attributes to initial
     */
    public void reset() {
        dieValue = 0;
        currentCellNo = 0;
        currentCell = null;
    }
}