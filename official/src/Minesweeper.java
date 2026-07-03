package src;

import java.util.Scanner;

/**
 * TCSS 360 - Assignment 1: Minesweeper
 * Reads an unknown number of minesweeper fields and
 * prints each field with the number of mines near the cell
 *
 * @author Jadon Bennett
 * @version 1
 */
public class Minesweeper {
    /** Character representing a mine in the input and output. */
    private static final char MINE_CHAR = '*';

    /**
     * Reads fields from input until the terminator is found,
     * solves each one, then prints it.
     *
     */
    public static void main(final String[] theArgs) {
        final Scanner scanner;
        try {
            if (theArgs.length == 1) {
                scanner = new Scanner(new java.io.File(theArgs[0]));
            } else {
                scanner = new Scanner(System.in);
            }
        } catch (java.io.FileNotFoundException e) {
            System.err.println("File not found: " + theArgs[0]);
            return;
        }

        int fieldNumber = 0;

        while (scanner.hasNextInt()) {
            final int n = scanner.nextInt();
            final int m = scanner.nextInt();
            scanner.nextLine();

            if (n == 0 && m == 0) {
                break;
            }

            fieldNumber++;

            // if (fieldNumber > 0) {
            //     System.out.println();
            // }

            final String[] lines = new String[n];
            for (int row = 0; row < n; row++) {
                lines[row] = scanner.nextLine();
            }

            final char[][] grid = constructGrid(lines, n, m);
            final int[][] counts = countAdjacentMines(grid, n, m);
            final char[][] solved = buildOutputGrid(grid, counts, n, m);

            System.out.println("Field #" + fieldNumber + ":");
            for (int row = 0; row < n; row++) {
                System.out.println(new String(solved[row]));
            }

            if (scanner.hasNextInt()) {
                System.out.println();
            }
        }
        scanner.close();
    }

    /**
     * turns grid lines into a 2D array.
     *
     * @param theLines the String line of characters
     * @param theRows  number of rows
     * @param theCols  number of columns
     * @return a 2D char array of size theRows by theCols
     */
    public static char[][] constructGrid(final String[] theLines, final int theRows, final int theCols) {
        final char[][] grid = new char[theRows][theCols];

        for (int row = 0; row < theRows; row++) {
            for (int col = 0; col < theCols; col++) {
                grid[row][col] = theLines[row].charAt(col);
            }
        }

        return grid;
    }

    /**
     * Compute the number of adjacent mines for every square in the grid.
     *
     * @param theGrid the original field
     * @param theRows number of rows
     * @param theCols number of columns
     * @return a 2D int array where each cell holds the count of mines in
     *         the up-to-8 squares surrounding that position
     */
    public static int[][] countAdjacentMines(final char[][] theGrid, final int theRows, final int theCols) {
        final int[][] counts = new int[theRows][theCols];

        for (int row = 0; row < theRows; row++) {
            for (int col = 0; col < theCols; col++) {
                if (theGrid[row][col] == MINE_CHAR) {
                    addMineToNeighbors(counts, row, col, theRows, theCols);
                }
            }
        }

        return counts;
    }

    /**
     * Increment the count for every valid neighbor of a mine.
     *
     * @param theCounts the 2D int array being accumulated, modified in place
     * @param theRow    row index of the mine
     * @param theCol    column index of the mine
     * @param theRows   number of rows in the grid
     * @param theCols   number of columns in the grid
     */
    public static void addMineToNeighbors(final int[][] theCounts, final int theRow, final int theCol,
            final int theRows, final int theCols) {
        for (int rowChange = -1; rowChange <= 1; rowChange++) {
            for (int colChange = -1; colChange <= 1; colChange++) {
                if (rowChange == 0 && colChange == 0) {
                    continue;
                }

                final int neighborRow = theRow + rowChange;
                final int neighborCol = theCol + colChange;

                if (neighborRow >= 0 && neighborRow < theRows
                        && neighborCol >= 0 && neighborCol < theCols) {
                    theCounts[neighborRow][neighborCol]++;
                }
            }
        }
    }

    /**
     * Build the printable representation of a populated field.
     *
     * @param theGrid   the original field
     * @param theCounts the grid with adjacency counts
     * @param theRows   number of rows
     * @param theCols   number of columns
     * @return a 2D array with mines shown as '*' and safe squares
     *         shown as their adjacency count
     */
    public static char[][] buildOutputGrid(final char[][] theGrid, final int[][] theCounts,
            final int theRows, final int theCols) {
        final char[][] output = new char[theRows][theCols];

        for (int row = 0; row < theRows; row++) {
            for (int col = 0; col < theCols; col++) {
                if (theGrid[row][col] == MINE_CHAR) {
                    output[row][col] = MINE_CHAR;
                } else {
                    output[row][col] = Character.forDigit(theCounts[row][col], 10);
                }
            }
        }

        return output;
    }
}