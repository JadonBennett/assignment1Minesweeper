/*
 * @author Joanna Duran
 * Minesweeper Assignment 1
 * TCSS 360 - SUMMER 2026
 * Individual Solution
 */

    import java.util.Scanner;

    public class Minesweeper {

        public static void main(String[] args) {

            Scanner input = new Scanner(System.in);
            //track test cases
            int fieldNumber = 1;

            while (true) {

                int rows = input.nextInt();
                int cols = input.nextInt();

                if (rows == 0 && cols == 0) {
                    break;
                }

                //create board
                char[][] board = new char[rows][cols];

                for (int i = 0; i < rows; i++) {
                    board[i] = input.next().toCharArray();
                }

                //print ln in between fields
                if (fieldNumber > 1) {
                    System.out.println();
                }

                System.out.println("Field #" + fieldNumber + ":");

                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {

                        if (board[i][j] == '*') {
                            System.out.print("*");
                        } else {
                            System.out.print(countAdjacentMines(board, i, j));
                        }
                    }
                    System.out.println();
                }

                fieldNumber++;
            }

            input.close();
        }

        public static int countAdjacentMines(char[][] board, int row, int col) {

            //num of mines around cell
            int count = 0;

            //offsets for neighbors
            int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

            for (int i = 0; i < 8; i++) {

                int nr = row + dr[i];
                int nc = col + dc[i];

                //check if neighbors are in bounds/mines
                if (nr >= 0 && nr < board.length &&
                        nc >= 0 && nc < board[0].length &&
                        board[nr][nc] == '*') {

                    //if mine found increase
                    count++;
                }
            }

            return count;
        }
    }