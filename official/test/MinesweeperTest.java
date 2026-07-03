import org.junit.jupiter.api.Test;
import src.Minesweeper;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for the Minesweeper solution.
 * Tests all key methods with edge cases and general cases.
 *
 * @author Jadon, Joanna, Nicholas, Sean
 * @version 1.0
 */
class MinesweeperTest
{
    private static final char MINE = '*';
    private static final char SAFE = '.';

    @Test
    void testConstructGrid_SmallField()
    {
        String[] lines = {"*..", "...", ".*."};
        char[][] expected = {
            {'*', '.', '.'},
            {'.', '.', '.'},
            {'.', '*', '.'}
        };

        char[][] result = Minesweeper.constructGrid(lines, 3, 3);

        assertArrayEquals(expected, result);
    }

    @Test
    void testConstructGrid_SingleCell()
    {
        String[] lines = {"*"};
        char[][] expected = {{'*'}};

        char[][] result = Minesweeper.constructGrid(lines, 1, 1);

        assertArrayEquals(expected, result);
    }

    @Test
    void testConstructGrid_SingleRow()
    {
        String[] lines = {"*.*.*"};
        char[][] expected = {{'*', '.', '*', '.', '*'}};

        char[][] result = Minesweeper.constructGrid(lines, 1, 5);

        assertArrayEquals(expected, result);
    }

    @Test
    void testConstructGrid_SingleColumn()
    {
        String[] lines = {"*", ".", "*"};
        char[][] expected = {{'*'}, {'.'}, {'*'}};

        char[][] result = Minesweeper.constructGrid(lines, 3, 1);

        assertArrayEquals(expected, result);
    }

    @Test
    void testConstructGrid_AllMines()
    {
        String[] lines = {"**", "**"};
        char[][] expected = {
            {'*', '*'},
            {'*', '*'}
        };

        char[][] result = Minesweeper.constructGrid(lines, 2, 2);

        assertArrayEquals(expected, result);
    }

    @Test
    void testConstructGrid_NoMines()
    {
        String[] lines = {"..", ".."};
        char[][] expected = {
            {'.', '.'},
            {'.', '.'}
        };

        char[][] result = Minesweeper.constructGrid(lines, 2, 2);

        assertArrayEquals(expected, result);
    }

    @Test
    void testCountAdjacentMines_Example1()
    {
        char[][] grid = {
            {'*', '.', '.', '.'},
            {'.', '.', '.', '.'},
            {'.', '*', '.', '.'},
            {'.', '.', '.', '.'}
        };
        int[][] expected = {
            {0, 1, 0, 0},
            {2, 2, 1, 0},
            {1, 0, 1, 0},
            {1, 1, 1, 0}
        };

        int[][] result = Minesweeper.countAdjacentMines(grid, 4, 4);

        assertArrayEquals(expected, result);
    }

    @Test
    void testCountAdjacentMines_Example2()
    {
        char[][] grid = {
            {'*', '*', '.', '.', '.'},
            {'.', '.', '.', '.', '.'},
            {'.', '*', '.', '.', '.'}
        };
        int[][] expected = {
            {1, 1, 1, 0, 0},
            {3, 3, 2, 0, 0},
            {1, 0, 1, 0, 0}
        };

        int[][] result = Minesweeper.countAdjacentMines(grid, 3, 5);

        assertArrayEquals(expected, result);
    }

    @Test
    void testCountAdjacentMines_SingleMine()
    {
        char[][] grid = {{'*'}};
        int[][] expected = {{0}};

        int[][] result = Minesweeper.countAdjacentMines(grid, 1, 1);

        assertArrayEquals(expected, result);
    }

    @Test
    void testCountAdjacentMines_SingleSafe()
    {
        char[][] grid = {{'.'}};
        int[][] expected = {{0}};

        int[][] result = Minesweeper.countAdjacentMines(grid, 1, 1);

        assertArrayEquals(expected, result);
    }

    @Test
    void testCountAdjacentMines_AllMines()
    {
        char[][] grid = {
            {'*', '*', '*'},
            {'*', '*', '*'},
            {'*', '*', '*'}
        };
        int[][] expected = {
            {3, 5, 3},
            {5, 8, 5},
            {3, 5, 3}
        };

        int[][] result = Minesweeper.countAdjacentMines(grid, 3, 3);

        assertArrayEquals(expected, result);
    }

    @Test
    void testCountAdjacentMines_NoMines()
    {
        char[][] grid = {
            {'.', '.', '.'},
            {'.', '.', '.'},
            {'.', '.', '.'}
        };
        int[][] expected = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };

        int[][] result = Minesweeper.countAdjacentMines(grid, 3, 3);

        assertArrayEquals(expected, result);
    }

    @Test
    void testCountAdjacentMines_CenterMine()
    {
        char[][] grid = {
            {'.', '.', '.'},
            {'.', '*', '.'},
            {'.', '.', '.'}
        };
        int[][] expected = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        int[][] result = Minesweeper.countAdjacentMines(grid, 3, 3);

        assertArrayEquals(expected, result);
    }

    @Test
    void testCountAdjacentMines_CornerMine()
    {
        char[][] grid = {
            {'*', '.', '.'},
            {'.', '.', '.'},
            {'.', '.', '.'}
        };
        int[][] expected = {
            {0, 1, 0},
            {1, 1, 0},
            {0, 0, 0}
        };

        int[][] result = Minesweeper.countAdjacentMines(grid, 3, 3);

        assertArrayEquals(expected, result);
    }

    @Test
    void testCountAdjacentMines_MaxAdjacent()
    {
        // Safe cell surrounded by 8 mines
        char[][] grid = {
            {'*', '*', '*'},
            {'*', '.', '*'},
            {'*', '*', '*'}
        };
        int[][] expected = {
            {2, 4, 2},
            {4, 8, 4},
            {2, 4, 2}
        };

        int[][] result = Minesweeper.countAdjacentMines(grid, 3, 3);

        assertArrayEquals(expected, result);
    }

    @Test
    void testBuildOutputGrid_Example1()
    {
        char[][] grid = {
            {'*', '.', '.', '.'},
            {'.', '.', '.', '.'},
            {'.', '*', '.', '.'},
            {'.', '.', '.', '.'}
        };
        int[][] counts = {
            {0, 1, 0, 0},
            {2, 2, 1, 0},
            {1, 0, 1, 0},
            {1, 1, 1, 0}
        };
        char[][] expected = {
            {'*', '1', '0', '0'},
            {'2', '2', '1', '0'},
            {'1', '*', '1', '0'},
            {'1', '1', '1', '0'}
        };

        char[][] result = Minesweeper.buildOutputGrid(grid, counts, 4, 4);

        assertArrayEquals(expected, result);
    }

    @Test
    void testBuildOutputGrid_Example2()
    {
        char[][] grid = {
            {'*', '*', '.', '.', '.'},
            {'.', '.', '.', '.', '.'},
            {'.', '*', '.', '.', '.'}
        };
        int[][] counts = {
            {0, 0, 1, 0, 0},
            {3, 3, 2, 0, 0},
            {1, 0, 1, 0, 0}
        };
        char[][] expected = {
            {'*', '*', '1', '0', '0'},
            {'3', '3', '2', '0', '0'},
            {'1', '*', '1', '0', '0'}
        };

        char[][] result = Minesweeper.buildOutputGrid(grid, counts, 3, 5);

        assertArrayEquals(expected, result);
    }

    @Test
    void testBuildOutputGrid_SingleMine()
    {
        char[][] grid = {{'*'}};
        int[][] counts = {{0}};
        char[][] expected = {{'*'}};

        char[][] result = Minesweeper.buildOutputGrid(grid, counts, 1, 1);

        assertArrayEquals(expected, result);
    }

    @Test
    void testBuildOutputGrid_SingleSafe()
    {
        char[][] grid = {{'.'}};
        int[][] counts = {{0}};
        char[][] expected = {{'0'}};

        char[][] result = Minesweeper.buildOutputGrid(grid, counts, 1, 1);

        assertArrayEquals(expected, result);
    }

    @Test
    void testBuildOutputGrid_AllMines()
    {
        char[][] grid = {
            {'*', '*'},
            {'*', '*'}
        };
        int[][] counts = {
            {0, 0},
            {0, 0}
        };
        char[][] expected = {
            {'*', '*'},
            {'*', '*'}
        };

        char[][] result = Minesweeper.buildOutputGrid(grid, counts, 2, 2);

        assertArrayEquals(expected, result);
    }

    @Test
    void testBuildOutputGrid_NoMines()
    {
        char[][] grid = {
            {'.', '.'},
            {'.', '.'}
        };
        int[][] counts = {
            {0, 0},
            {0, 0}
        };
        char[][] expected = {
            {'0', '0'},
            {'0', '0'}
        };

        char[][] result = Minesweeper.buildOutputGrid(grid, counts, 2, 2);

        assertArrayEquals(expected, result);
    }


    @Test
    void testFullPipeline_SimpleCase()
    {
        // Test the complete workflow from input to output
        String[] lines = {"*..", "..."};
        char[][] grid = Minesweeper.constructGrid(lines, 2, 3);
        int[][] counts = Minesweeper.countAdjacentMines(grid, 2, 3);
        char[][] output = Minesweeper.buildOutputGrid(grid, counts, 2, 3);

        char[][] expected = {
            {'*', '1', '0'},
            {'1', '1', '0'}
        };

        assertArrayEquals(expected, output);
    }

    @Test
    void testFullPipeline_LargerCase()
    {
        // Test with a more complex field
        String[] lines = {
            "**...",
            ".....",
            ".*..."
        };
        char[][] grid = Minesweeper.constructGrid(lines, 3, 5);
        int[][] counts = Minesweeper.countAdjacentMines(grid, 3, 5);
        char[][] output = Minesweeper.buildOutputGrid(grid, counts, 3, 5);

        char[][] expected = {
            {'*', '*', '1', '0', '0'},
            {'3', '3', '2', '0', '0'},
            {'1', '*', '1', '0', '0'}
        };

        assertArrayEquals(expected, output);
    }
}