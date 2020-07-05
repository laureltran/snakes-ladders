import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Brief description
 * @author      Van Anh Tran <102421412>
 * @version     1      
 * Purpose      Project 2: Snakes & Ladders Game - Game class with logics for game rules and Main method to run game
*/

public class Game extends JFrame
{
    public static final int WIDTH = 600; // Window width
    public static final int HEIGHT = 650; // Window height
    private boolean endTurn = true; // Flag to indicate the end of a turn
    private boolean firstTime = true; // Flag to indicate the first turns when 2 players roll to decide who go first
    private int dieValue; // Value of the die after each roll
    private Human human; // Human player
    private Computer computer; // Computer player
    private Player player; // Player
    private Die die; // Die
    private Board board; // Board
    private Cell[] cells; // Array of all cells in the board
    private int[] ladderStarts = {4, 12}; // array of ladder start cell values
    private int[] ladderEnds = {14, 22}; // array of ladder end cell values
    private int[] snakeStarts = {20, 16}; // array of snake start cell values
    private int[] snakeEnds = {7, 5}; // array of snake end cell values
    
    /**
     * Main method
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.setVisible(true);
    }

    /**
     * Constructor
     */
    public Game() {
        // Create GUI window and set layout
        super("Snakes And Ladders - Van Anh Tran");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Board GUI
        board = new Board();
        cells = board.getCells();
        add(board, BorderLayout.CENTER);

        // Human GUI, Die GUI, Computer GUI
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new GridLayout(1,3));
        human = new Human();
        playerPanel.add(human.getGui());
        die = new Die();
        playerPanel.add(die);
        computer = new Computer();
        playerPanel.add(computer.getGui());
        add(playerPanel, BorderLayout.NORTH);

        
        JPanel buttonPanel = new JPanel();
        // Restart button listens to "mouse click" event
        JButton restart = new JButton("Restart");
        restart.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int restartOption = JOptionPane.showConfirmDialog(new JFrame(),"Do you want to restart the game?", "Restart", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (restartOption == JOptionPane.YES_OPTION) {
                    restart();
                }
            }
        });
        
        // Quit button listens to "mouse click" event
        JButton quit = new JButton("Quit");
        quit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int quitOption = JOptionPane.showConfirmDialog(new JFrame(),"Do you want to quit the game?", "Quit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (quitOption == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        buttonPanel.add(restart);
        buttonPanel.add(quit);
        add(buttonPanel, BorderLayout.SOUTH);

        // Option dialogue to start the game
        int option = JOptionPane.showConfirmDialog(new JFrame(),"Please read carefully:\nIn this game, you and the computer take turn to roll the die and move along the board.\nYou have to roll the die on behalf of the computer.\nThe player who arrives at the last cell first is the winner.\nTo decide who goes first, players have to roll the die until a player gets the higher die value.\n\nDo you want to continue?", "Welcome to Snakes and Ladders", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            playGame();
        }
        else {
            System.exit(0);
        }
    }

    /**
     * Purpose: Method for each player to roll the die and get the die value
     */
    public void playGame() {
        // Inform the human player to roll the die first
        JOptionPane.showMessageDialog(new JFrame(),"You take the first turn. Please click the die to roll.", "It's your turn!", JOptionPane.PLAIN_MESSAGE);
        
        // Die listens to the "mouse click" event to roll die
        die.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    if (endTurn == true) {
                        dieValue = die.roll();

                        // Set die value for player
                        if (human.getFlag() == true) {
                            player = human;
                        }
                        else {
                            player = computer;
                        }
                        // Inform what die value the player gets
                        JOptionPane.showMessageDialog(new JFrame(), player.getName() + " got " + dieValue + "!", player.getName() + " rolled the die.", JOptionPane.PLAIN_MESSAGE);
                        player.setDieValue(dieValue);

                        if (firstTime == true) {
                            goFirst(); // Compare die values of players to decide who goes first
                        }
                        else {
                            endTurn = takeTurn(player); // Get in the main game, each player takes turn
                        }
                    }
                    else {
                        // Inform that player can't roll the die when the turn hasn't ended
                        JOptionPane.showMessageDialog(new JFrame(), player.getName() + " must finish this turn to roll the die.", "Can't roll the die now!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
    }
    
    /**
     * Purpose: Method to indicate which cell each player can move to after rolling the die
     * @param player
     * @return endTurn (boolean)
     */
    public boolean takeTurn(Player player) {
        endTurn = false;

        // Indicate which cell for the player to move to only if the player is not bypassing the last cell
        if (player.getCurrentCellNo() + player.getDieValue() <= board.getNoCells()) {
            player.setCurrentCellNo();
            if (player instanceof Human == true) {
                // Inform player to move to the indicated cell
                JOptionPane.showMessageDialog(new JFrame(),"Please move to cell " + player.getCurrentCellNo() + "!", "It's your turn to move...", JOptionPane.PLAIN_MESSAGE);

                for (Cell cell : cells) {
                    for (ActionListener act : cell.getActionListeners()) {
                        cell.removeActionListener(act);
                    }
                    // Cell listens to the "mouse click" event
                    cell.addActionListener(new ActionListener()
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            if (player.getCurrentCellNo() > 0) {
                                // Allow human to move to the cell if human chose the right cell
                                if (cell.getValue() == player.getCurrentCellNo()) {
                                    move(player, cell);
                                }
                                // Inform that human chose the wrong cell
                                else {
                                    JOptionPane.showMessageDialog(new JFrame(), "You have to move to cell " + player.getCurrentCellNo() + ".", "Wrong move!", JOptionPane.WARNING_MESSAGE);
                                }
                                
                            }
                        }
                    });
                }
            }
            else {
                // Inform human about computer's upcoming automatic move
                JOptionPane.showMessageDialog(new JFrame(),"Computer automatically moves to cell " + player.getCurrentCellNo() + ".", "It's computer's turn to move...", JOptionPane.PLAIN_MESSAGE);
                // Let computer automatically move to the right cell
                for (Cell cell : cells) {
                    if (cell.getValue() == player.getCurrentCellNo()) {
                        move(player, cell);
                    }
                }
            }
        }
        // Inform that player has to roll the exact die value to get to the last cell
        else {
            JOptionPane.showMessageDialog(new JFrame(), player.getName() + " must get the exact die value not to bypass the last cell. Wait for the next turn!", "Cannot bypass the last cell!", JOptionPane.WARNING_MESSAGE);
            switchPlayer();
            endTurn = true;         
        }
        return endTurn;
    }

    /**
     * Purpose: Method to indicate player's position after moving
     * @param player
     * @param cell
     */
    public void move(Player player, Cell cell) {
        if (!endTurn) {
            // Reset colour of the previous cell to indicate player is no longer at that position
            if (player.getCurrentCell() != null) {
                player.getCurrentCell().resetColour(player);
            }
            
            // Make player automatically move to ladder or snake end if encountering a ladder or snake
            if (checkLadder(cell) != null) {
                cell = checkLadder(cell);
                // Inform the ladder incident
                JOptionPane.showMessageDialog(new JFrame(),"A ladder! Move forwards to cell " + cell.getValue() + "!", "Yay, ladder!", JOptionPane.WARNING_MESSAGE);               
            }
            else if (checkSnake(cell) != null) {
                cell = checkSnake(cell);
                // Inform the snake incident
                JOptionPane.showMessageDialog(new JFrame(),"A snake! Move backwards to cell " + cell.getValue() + "!", "Oops, snake!", JOptionPane.WARNING_MESSAGE);               
            }

            // Set colour for the current cell to indicate player's current position
            cell.setColour(player);
            
            // Player wins if landing at the last cell
            if (cell.getValue() == board.getNoCells()) {
                win(player);
            }
            else {
                player.setCurrentCell(cell);
                player.setCurrentCellNo(cell.getValue());
                // Player gets another turn for rolling a 6
                if (player.getDieValue() == 6) {
                    JOptionPane.showMessageDialog(new JFrame(), player.getName() + " got another turn for rolling a 6.", "Another turn!", JOptionPane.PLAIN_MESSAGE);
                }
                else {
                    switchPlayer();               
                }
                endTurn = true;
            }
        }
    }

    /**
     * Method to decide who goes first
     */
    public void goFirst() {
        // Compare human and computer's die value after both have rolled for the first time
        if (human.getDieValue() > 0 && computer.getDieValue() > 0) {

            // Human goes first scenario
            if (human.getDieValue() > computer.getDieValue()) {
                human.setFlag(true);
                computer.setFlag(false);
                // Inform message
                JOptionPane.showMessageDialog(new JFrame(),"You go first! Please roll the die.", "You got the higher die value!", JOptionPane.PLAIN_MESSAGE);
                firstTime = false;
            }

            // Computer goes first scenario
            else if (human.getDieValue() < computer.getDieValue()) {
                computer.setFlag(true);
                human.setFlag(false);
                // Inform message
                JOptionPane.showMessageDialog(new JFrame(),"Computer goes first! Please roll the die on behalf of the computer.", "Computer got the higher die value!", JOptionPane.PLAIN_MESSAGE);
                firstTime = false;
            }

            // Tie result scenario, two players roll again
            else {
                human.setDieValue(0);
                computer.setDieValue(0);
                // Inform message
                JOptionPane.showMessageDialog(new JFrame(),"It's a tie result. Players have to roll again.", "It's a tie!", JOptionPane.PLAIN_MESSAGE);
                switchPlayer();
            }
        }
        else {
            switchPlayer();
        }
    }

    /**
     * Method to switch player's turn
     */
    public void switchPlayer() {
        human.switchFlag();
        computer.switchFlag();
    }

    /**
     * Method to check if the cell is a ladder start, if yes, return its ladder-end cell
     * @param cell
     * @return ladderEnd (Cell)
     */
    public Cell checkLadder(Cell cell) {
        Cell ladderEnd = null;
        for (int i = 0; i < ladderStarts.length; i++) {
            if (cell.getValue() == ladderStarts[i]) {
                ladderEnd = cells[ladderEnds[i]-1];
                break;
            }
        }
        return ladderEnd;
    }

    /**
     * Method to check if the cell is a snake start, if yes, return its snake-end cell
     * @param cell
     * @return snakeEnd (Cell)
     */
    public Cell checkSnake(Cell cell) {
        Cell snakeEnd = null;
        for (int i = 0; i < snakeStarts.length; i++) {
            if (cell.getValue() == snakeStarts[i]) {
                snakeEnd = cells[snakeEnds[i]-1];
                break;
            }
        }
        return snakeEnd;
    }

    /**
     * Method for the player to win
     * @param player
     */
    public void win(Player player) {
        // Inform message
        int option = JOptionPane.showConfirmDialog(new JFrame(), player.getName() + " won!\nDo you want to restart?", "WIN", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        // Option dialogue to restart or exit game
        if (option == JOptionPane.YES_OPTION) {
            restart();
        }
        else {
            System.exit(0);
        }
    }

    /**
     * Method to restart game
     */
    public void restart() {
        // Reset all attributes to initial values then play game
        endTurn = true;
        firstTime = true;
        dieValue = 0;
        die.reset();
        human.reset();
        computer.reset();
        for (Cell cell : cells) {
            cell.resetColour(human);
            cell.resetColour(computer);
        }
        playGame();
    }
}