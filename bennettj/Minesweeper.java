
/*
* ALL JAVADOC COMMENTS AUTOMATICALLY GENERATED WITH USE OF AI, with a little bit of edits from yours truly.
 Minesweeper Solving Algorithm
 TCSS 360 Software Engineering
 Summer 2026
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This minesweeper algorithm reads at least one minsweeper fields from
 * System.in or
 * command line argument source and prints each field with the number of
 * adjacent mines to
 * that field so we can more easily find where the mines may be hiding.
 * 
 * @author: Nick Humeniuk Sandberg
 * @version: July 03, 2026
 */

public final class Minesweeper {

    private static final char MINE = '*';

    /**
     * Program entry point. Reads input either from a file specified as a single
     * command-line
     * argument or from standard input. The input consists of one or more
     * Minesweeper fields.
     * Each field begins with two integers n and m (rows and columns). A pair of
     * zeros (0 0)
     * terminates the input. For each field the program reads the board and prints
     * the field
     * with numbers indicating how many adjacent mines each non-mine cell has.
     *
     * @param theArgs command-line arguments; if one argument is provided it is
     *                treated as the
     *                path to an input file to read instead of System.in
     * @throws FileNotFoundException if the file specified in theArgs[0] cannot be
     *                               opened
     */
    public static void main(final String theArgs[]) {

        final Scanner scan;

        try {
            if (theArgs.length == 1) {
                scan = new Scanner(new File(theArgs[0]));
            } else {
                scan = new Scanner(System.in);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + theArgs[0]);
            return;
        }

        int fieldCounter = 1;
        while (scan.hasNextLine()) {
            final int n = scan.nextInt();
            final int m = scan.nextInt();

            if (n == 0 && m == 0) {
                break;
            }
            scan.nextLine();

            gameBoard(scan, n, m, fieldCounter);
            fieldCounter = fieldCounter + 1;
        }
        scan.close();
    }

    /**
     * Reads a Minesweeper board from the provided Scanner. The method expects
     * exactly n lines
     * to follow in the scanner, each containing m characters representing the board
     * row.
     * Each character is copied into a newly allocated 2D char array which is
     * returned.
     *
     * @param theScan the Scanner to read board lines from; must be positioned at
     *                the first board line
     * @param n       number of rows to read
     * @param m       number of columns per row to read
     * @return a 2D char array of size n by m containing the board characters
     */
    private static char[][] readBoard(final Scanner theScan, final int n, final int m) {

        final char[][] board = new char[n][m];

        for (int i = 0; i < n; i++) {
            String line = theScan.nextLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        return board;
    }

    /**
     * Processes a single Minesweeper field: reads the board from the scanner,
     * prints the
     * field header "Field #X:" and then prints the board where each mine ('*') is
     * printed
     * unchanged and each non-mine cell is replaced by the count of adjacent mines.
     *
     * @param theScan         the Scanner to read the board from
     * @param n               number of rows in the board
     * @param m               number of columns in the board
     * @param theFieldCounter the sequential field number used in the printed header
     */
    private static void gameBoard(final Scanner theScan, final int n, final int m, final int theFieldCounter) {

        System.out.println("Field #" + theFieldCounter + ":");
        final char[][] board = readBoard(theScan, n, m);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == MINE) {
                    System.out.print(board[i][j]);
                } else {
                    System.out.print(neighbors(board, i, j));
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Counts the number of mines adjacent to the specified cell in theBoard.
     * Adjacent cells
     * include the eight surrounding positions (horizontal, vertical, and diagonal)
     * when they
     * exist within the board bounds.
     *
     * @param theBoard  the Minesweeper board as a 2D char array
     * @param theRow    the row index of the cell to inspect
     * @param theColumn the column index of the cell to inspect
     * @return the number of adjacent cells that contain a mine character ('*')
     */
    private static int neighbors(final char[][] theBoard, final int theRow, final int theColumn) {

        int nums = 0;

        if (theRow - 1 >= 0 && theBoard[theRow - 1][theColumn] == MINE) {
            nums = nums + 1;
        }
        if (theRow + 1 < theBoard.length && theBoard[theRow + 1][theColumn] == MINE) {
            nums = nums + 1;
        }
        if (theColumn - 1 >= 0 && theBoard[theRow][theColumn - 1] == MINE) {
            nums = nums + 1;
        }
        if (theColumn + 1 < theBoard[theRow].length && theBoard[theRow][theColumn + 1] == MINE) {
            nums = nums + 1;
        }
        if (theRow - 1 >= 0 && theColumn - 1 >= 0 && theBoard[theRow - 1][theColumn - 1] == MINE) {
            nums = nums + 1;
        }
        if (theRow - 1 >= 0 && theColumn + 1 < theBoard[theRow].length && theBoard[theRow - 1][theColumn + 1] == MINE) {
            nums = nums + 1;
        }
        if (theRow + 1 < theBoard.length && theColumn - 1 >= 0 && theBoard[theRow + 1][theColumn - 1] == MINE) {
            nums = nums + 1;
        }
        if (theRow + 1 < theBoard.length && theColumn + 1 < theBoard[theRow].length
                && theBoard[theRow + 1][theColumn + 1] == MINE) {
            nums = nums + 1;
        }
        return nums;
    }
}