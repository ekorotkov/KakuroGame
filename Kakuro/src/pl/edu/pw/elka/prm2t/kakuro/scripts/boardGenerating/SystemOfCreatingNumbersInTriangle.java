package pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating;

import java.util.ArrayList;
import java.util.List;

public class SystemOfCreatingNumbersInTriangle {

    public static ArrayList<List<Integer>> finalBoardMaker(int [][] board, Integer gridSize){
        ArrayList<List<Integer>> finalBorder = new ArrayList<>();
        for(int row = 0; row < gridSize; row++){
            for(int column = 0; column < gridSize; column++) {
                if (board[row][column] == 0) {
                    finalBorder.add(new ArrayList<>(List.of(0, 0))); // pola czarne
                }
                if (board[row][column] > 0) {
                    finalBorder.add(new ArrayList<>(List.of(-2, -2))); //pola do wpisywania
                }
                if (board[row][column] == -1) {// pola z trójkątami
                    int sumOfRow;
                    int sumOfColumn;
                    if (board[row][column + 1] != -1 || board[row][column + 1] != 0) {
                        sumOfRow = counterOfValueInRow(board, row, column, gridSize);
                    } else {
                        sumOfRow = 0;
                    }
                    if (board[row + 1][column] != -1 || board[row + 1][column] != 0) {
                        sumOfColumn = counterOfValueInColumn(board, row, column, gridSize);
                    } else {
                        sumOfColumn = 0;
                    }
                    finalBorder.add(new ArrayList<>(List.of(sumOfRow, sumOfColumn)));
                }
            }
        }
        return finalBorder;
    }

    public static int counterOfValueInRow(int[][] board,int row, int column, int gridSize){
        int counter = 0;
        for(int i=column+1; i < gridSize; i++){
            if(board[row][i] == 0 || board[row][i] == -1){
                break;
            }else{
                counter = counter + board[row][i];
            }
        }return counter;
    }
    public static int counterOfValueInColumn(int[][] board,int row, int column, int gridSize){
        int counter = 0;
        for(int i=row+1; i < gridSize; i++){
            if(board[i][column] == 0 || board[i][column] == -1){
                break;
            }else{
                counter = counter + board[i][column];
            }
        }return counter;
    }
}