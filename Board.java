import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.*;

/**
 * Brief description
 * @author      Van Anh Tran <102421412>
 * @version     1      
 * Purpose      Project 2: Snakes & Ladders Game - Board class to represent the board object
*/

public class Board extends JPanel {
    public static final int NO_CELLS = 30; // number of cells in the board
    public static final int MIN_CELL_IN_BOARD = 1; // minimum value of cell in the board
    public static final int NO_COLUMNS = 6; // number of columns in the board
    public static final int NO_ROWS = NO_CELLS/NO_COLUMNS; // number of rows in the board
    public static final int CELL_SIZE = 100; // size of a cell
    public static final int BOARD_WIDTH = NO_COLUMNS * CELL_SIZE; // width of the board
    public static final int BOARD_HEIGHT = NO_ROWS * CELL_SIZE; // height of the board
    private JPanel[] rows = new JPanel[NO_ROWS]; // array of all JPanel rows in the board
    private Cell[] cells = new Cell[NO_CELLS]; // array of all cells in the board
    private int[] ladderStarts = {4, 12}; // array of ladder start cell values
    private int[] ladderEnds = {14, 22}; // array of ladder end cell values
    private int[] snakeStarts = {20, 16}; // array of snake start cell values
    private int[] snakeEnds = {7, 5}; // array of snake end cell values

    /** 
     * Purpose: Constructor
    */
	public Board() {
        // Initialise GUI
        setLayout(new GridLayout(NO_ROWS,1));
        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setVisible(true);

        // Create rows that carries cells
        for (int i=0; i<rows.length; i++) {
            int minCell = MIN_CELL_IN_BOARD + i*NO_COLUMNS;
            int maxCell = minCell + NO_COLUMNS - 1;
            rows[i] = ((i+1) % 2 == 0) ? drawEvenRows(minCell, maxCell) : drawOddRows(minCell, maxCell);
        }

        // Reverse the rows' array to display rows from bottom to top
        Collections.reverse(Arrays.asList(rows));

        // Add rows to GUI
        for (JPanel row : rows) {
            add(row);
        }
    }

    /**
     * Purpose: Create cell with text, indicate which cells are ladder/snake start/end
     * @param index (int)
     * @return cell (Cell)
     */
    public Cell createCell(int index) {
        Cell cell = new Cell(index);
        String text = setCellText(index, ladderStarts, "LS") + setCellText(index, ladderEnds, "LE") +  setCellText(index, snakeStarts, "SS") + setCellText(index, snakeEnds, "SE");
        cell.setText(String.valueOf(index) + text);
        cells[index-1] = cell;
        return cell;
    }

    /**
     * Purpose: Set text for cell
     * @param index (int)
     * @param arr (int[])
     * @param string (String)
     * @return text (String)
     */
    public String setCellText(int index, int[] arr, String string) {
        String text = "";
        for (int i=0; i<arr.length; i++) {
            if (index == arr[i]) {
                text = " - " + string + String.valueOf(i+1);
            }
        }
        return text;
    }

    /**
     * Purpose: Draw GUI of the rows with odd index
     * @param minCell (int)
     * @param maxCell (int)
     * @return row (JPanel)
     */
    public JPanel drawOddRows (int minCell, int maxCell) {
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(1,NO_COLUMNS));
        for (int i=minCell; i<=maxCell; i++) {
            Cell cell = createCell(i);
            row.add(cell);
        }
        return row;
    }

    /**
     * Purpose: Draw GUI of the rows with even index
     * @param minCell (int)
     * @param maxCell (int)
     * @return row (JPanel)
     */
    public JPanel drawEvenRows (int minCell, int maxCell) {
        JPanel row = new JPanel();
        row.setLayout(new GridLayout(1,NO_COLUMNS));
        for (int i=maxCell; i>=minCell; i--) {
            Cell cell = createCell(i);
            row.add(cell);
        }
        return row;
    }

    /**
     * Purpose: Return the array of all cells in the board
     * @return cells (Cell[])
     */
    public Cell[] getCells() {
        return this.cells;
    }

    /**
     * Purpose: Return the number of cells in the board
     * @return NO_CELLS (int)
     */
    public int getNoCells() {
        return NO_CELLS;
    }
}