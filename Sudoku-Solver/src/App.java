public class App {

    // size of the 9x9 Sudoku board
    private static final int gridSize = 9;
    public static void main(String[] args) throws Exception {
        // Sudoku board represented in 2D array. Values must be put manually.
        // Empty squres are represented by 0
        int[][] board = {
            {0,0,0,0,6,0,9,0,0},
            {0,0,0,3,0,0,0,0,8},
            {0,0,0,0,0,0,2,0,5},
            {0,0,9,0,3,4,0,0,6},
            {0,0,3,0,0,0,4,0,0},
            {0,4,0,2,1,0,0,8,3},
            {8,0,0,9,0,6,3,0,0},
            {3,0,0,0,5,1,0,6,2},
            {0,6,5,0,7,0,0,1,0},
        };

        printBoard(board);

        if (solveBoard(board)){
            System.out.println("Solved Successfully");
        } else {
            System.out.println("Board is unsolvable");
        }
        printBoard(board);

    }

    // Method to print board
    private static void printBoard(int[][] board){
        for (int row = 0; row < gridSize; row++){
            if (row % 3 == 0 && row != 0){
                System.out.println("---------------------");
            }
            for (int column = 0; column < gridSize; column++){
                if (column % 3 == 0  && column != 0){
                    System.out.print("| ");
                }
                System.out.print(board[row][column] + " ");
            }
            System.out.println();
        }
    }

    // This method checks to see if the number is present in the current row
    // takes in the board, the desired number, and which row it resides in (the first array of the board)
    private static boolean isNumberInRow (int[][] board, int number, int row){
        for (int i = 0; i < gridSize; i++){
            if (board[row][i] == number){
                return true;
            }
        }
        return false;
    }

    // This method checks to see if the number is present in the current column
    // takes in the board, the desired number, and which column it resides in (the second array of the board)
    private static boolean isNumberInColumn (int[][] board, int number, int column){
        for (int i = 0; i < gridSize; i++){
            if (board[i][column] == number){
                return true;
            }
        }
        return false;
    }

    // This method checks to see if the number is present in the current box (a 3x3 box in a sudoku board)
    // takes in the board, the desired number, row, and column
    private static boolean isNumberInBox (int[][] board, int number, int row, int column){
        
        // These integers help locate the top left square of any of the 9 boxes 
        int localBoxRow = row - row % 3;
        int localBoxColumn = column - column % 3;

        // Nested for loop checks every square in the 3x3 box for the number 
        for (int i  = localBoxRow; i < localBoxRow + 3; i++){
            for (int j = localBoxColumn; j < localBoxColumn + 3; j++){
                if (board[i][j] == number){
                    return true;
                }
            }
        }
        return false;
    }

    // This method checks to see if the square is a valid placement by using the 3 methods created above
    private static boolean isValidPlacement(int[][] board, int number, int row, int column){
        // uses all the 3 methods to check. Checking to see if all 3 return false which makes the placement valid.
        // this explains the use of ! (the not operator). If one of the methods return true than the placement isn't valid. 
        return !isNumberInRow(board, number, row) &&
        !isNumberInColumn(board, number, column) &&
        !isNumberInBox(board, number, row, column);
    }

    // This method solves the board using recursion
    private static boolean solveBoard(int[][] board){
        
        // nested for loop to traverse through the whole board
        for (int row = 0; row < gridSize; row++){
            for (int column = 0; column < gridSize; column++){
                // attemps to check valid placements when it stumbles upon a 0 (empty square)
                if (board[row][column] == 0){
                    for (int numberToTry = 1; numberToTry <= gridSize; numberToTry++){
                        if (isValidPlacement(board, numberToTry, row, column)){
                            board[row][column] = numberToTry;

                            if (solveBoard(board)){
                                return true;
                            } else {
                                board[row][column] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
