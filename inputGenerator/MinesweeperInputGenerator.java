import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * TCSS 360 - Assignment 1: Minesweeper Input Generator
 * Generates comprehensive test cases for the Minesweeper problem.
 *
 * Generates edge cases, the problem statement examples, and random test cases
 * for testing minesweeper solutions.
 *
 * @author Team Assignment 1
 * @version 1.0
 */
public class MinesweeperInputGenerator
{
    private static final char MINE = '*';
    private static final char SAFE = '.';
    private static final Random RANDOM = new Random(42); // Fixed seed for reproducibility

    public static void main(String[] args)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter("../team_minesweeper_input.txt")))
        {
            System.out.println("Generating minesweeper test cases...");

            // Example 1 from problem statement
            writeField(writer, new String[]{
                "*...",
                "....",
                ".*..",
                "...."
            });

            // Example 2 from problem statement
            writeField(writer, new String[]{
                "**...",
                ".....",
                ".*..."
            });

            // Edge case: 1x1 with mine
            writeField(writer, new String[]{"*"});

            // Edge case: 1x1 no mine
            writeField(writer, new String[]{"."});

            // Edge case: 1x100 with some mines
            writeField(writer, generate1xN(100, 0.2));

            // Edge case: 100x1 with some mines
            writeField(writer, generateNx1(100, 0.3));

            // Edge case: 100x100 all mines
            writeField(writer, generateField(100, 100, 1.0));

            // Edge case: 100x100 no mines
            writeField(writer, generateField(100, 100, 0.0));

            // Edge case: 100x100 with 50% mines
            writeField(writer, generateField(100, 100, 0.5));

            // Edge case: 100x100 with 10% mines
            writeField(writer, generateField(100, 100, 0.1));

            // Edge case: 100x100 with 90% mines
            writeField(writer, generateField(100, 100, 0.9));

            // Medium test cases
            writeField(writer, generateField(10, 10, 0.15));
            writeField(writer, generateField(20, 30, 0.25));
            writeField(writer, generateField(50, 50, 0.2));

            // Small random cases
            writeField(writer, generateField(5, 8, 0.3));
            writeField(writer, generateField(3, 3, 0.4));

            // Edge case: single row
            writeField(writer, new String[]{"*.*.*.*.*"});

            // Edge case: single column
            writeField(writer, new String[]{"*", ".", "*", ".", "*"});

            // Terminator
            writer.println("0 0");

            System.out.println("Successfully generated team_minesweeper_input.txt");
            System.out.println("Total test cases: 18 (plus terminator)");
        }
        catch (IOException e)
        {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    /**
     * Writes a single field to the output file.
     *
     * @param writer the PrintWriter to write to
     * @param field array of strings representing the field rows
     */
    private static void writeField(PrintWriter writer, String[] field)
    {
        int rows = field.length;
        int cols = field[0].length();

        writer.println(rows + " " + cols);
        for (String row : field)
        {
            writer.println(row);
        }
    }

    /**
     * Generates a random minefield.
     *
     * @param rows number of rows
     * @param cols number of columns
     * @param minePercentage percentage of cells that should be mines (0.0 to 1.0)
     * @return array of strings representing the field
     */
    private static String[] generateField(int rows, int cols, double minePercentage)
    {
        String[] field = new String[rows];

        for (int i = 0; i < rows; i++)
        {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < cols; j++)
            {
                if (RANDOM.nextDouble() < minePercentage)
                {
                    row.append(MINE);
                }
                else
                {
                    row.append(SAFE);
                }
            }
            field[i] = row.toString();
        }

        return field;
    }

    /**
     * Generates a 1xN field (single row).
     *
     * @param cols number of columns
     * @param minePercentage percentage of cells that should be mines
     * @return array with single string
     */
    private static String[] generate1xN(int cols, double minePercentage)
    {
        return generateField(1, cols, minePercentage);
    }

    /**
     * Generates an Nx1 field (single column).
     *
     * @param rows number of rows
     * @param minePercentage percentage of cells that should be mines
     * @return array of strings, each containing one character
     */
    private static String[] generateNx1(int rows, double minePercentage)
    {
        return generateField(rows, 1, minePercentage);
    }
}
