package pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating;

import java.util.Random;

public class SystemOfCreatingNumbersInWhiteFields {

    public static void tableWriter(int[][] board, int gridSize){
        for(int row = 0; row < gridSize; row++){
            for(int column = 0; column < gridSize; column++) {
                int randomInt;
                if (board[row][column] == -2){ //wdepneliśmy w białe pole
                    do {
                        Random random = new Random();
                        randomInt = random.nextInt(1, 10);
                    }while(!isThatACorrectNumber(board, randomInt, row, column, gridSize));
                    board[row][column] = randomInt;
                }
            }
        }
    }
    public static void printBoard(int [][] board, int gridSize){
        for(int row = 0; row < gridSize; row++){
            for(int column = 0; column < gridSize; column++){
                System.out.print(board[row][column]);
            }
            System.out.println();
        }
    }
    public static boolean isThatACorrectNumber(int[][] board, int number, int row, int column, int gridSize){
        return  isNumberInColumnToDown(board, number, row, column, gridSize) &&
                isNumberInColumnToUp(board, number, row, column, gridSize) &&
                isNumberInRowToLeft(board, number, row, column, gridSize) &&
                isNumberInRowToRight(board, number, row, column, gridSize);
    }

    public static boolean isNumberInRowToRight(int[][] board, int number, int row, int column, int gridSize) {
        for (int i = column + 1; i < gridSize; i++) {
            if (board[row][i] == -1 || board[row][i] == 0) { // wychodzi poza biały obszar
                break;
            }
            else if (board[row][i] == number){
                return false;
            }
        }
        return true;
    }
    public static boolean isNumberInRowToLeft(int[][] board, int number, int row, int column, int gridSize) {
        for (int i = column - 1; i >= 0; i--) {
            if (board[row][i] == -1 || board[row][i] == 0) { // wychodzi poza biały obszar
                break;
            }
            else if (board[row][i] == number){
                return false;
            }
        }
        return true;
    }
    public static boolean isNumberInColumnToUp(int[][] board, int number, int row, int column, int gridSize) {
        for (int i = row + 1; i < gridSize; i++) {
            if (board[i][column] == -1 || board[i][column] == 0) { // wychodzi poza biały obszar
                return true;
            }
            else if (board[i][column] == number){
                return false;
            }
        }
        return true;
    }
    public static boolean isNumberInColumnToDown(int[][] board, int number, int row, int column, int gridSize) {
        for (int i = row - 1; i >= 0; i--) {
            if (board[i][column] == -1 || board[i][column] == 0) { // wychodzi poza biały obszar
                return true;
            }
            else if (board[i][column] == number){
                return false;
            }
        }
        return true;
    }
}