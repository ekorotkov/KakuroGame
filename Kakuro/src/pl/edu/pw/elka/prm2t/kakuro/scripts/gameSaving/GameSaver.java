package pl.edu.pw.elka.prm2t.kakuro.scripts.gameSaving;

import pl.edu.pw.elka.prm2t.kakuro.scripts.DataForGameBoard;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.BoardPanelGenerator;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.CellFieldsGenerator;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.MainBoardFrame;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameSaver {
    static int gridSize = 9;
    static int[][] easy = DataForGameBoard.getEasyTable();

    public GameSaver(ArrayList<ArrayList<List<Integer>>> board){

        CellFieldsGenerator[][] cells = BoardPanelGenerator.getCells();
        for (int row = 0; row < MainBoardFrame.getLength(); ++row) {
            for (int col = 0; col < MainBoardFrame.getLength(); ++col) {
                if(cells[row][col].isFocusable()) {
                    if (!Objects.equals(cells[row][col].getText(), "")) {
                        board.get(row).get(col).set(1, Integer.parseInt(cells[row][col].getText()));
                    }
                }
            }
        }
        tableSaver(board);
    }
    public static void tableSaver(ArrayList<ArrayList<List<Integer>>> nestedList){
        if(!Files.exists(Path.of("/resources/games"))){
            new File("/resources/games").mkdirs();
        }
        String filePath = "resources/games/newGame.txt";
        try {
            FileWriter writer = new FileWriter(filePath);
            writeNestedListToFile(writer, nestedList);
            writer.close();
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisywania do pliku: " + e.getMessage());
        }
    }

    public static void writeNestedListToFile(FileWriter writer, ArrayList<ArrayList<List<Integer>>> nestedList)
            throws IOException {
        for (ArrayList<List<Integer>> outerList : nestedList) {
            for (List<Integer> innerList : outerList) {
                for (int i = 0; i < innerList.size(); i++) {
                    writer.write(String.valueOf(innerList.get(i)));

                    if (i < innerList.size() - 1) {
                        writer.write(",");
                    }
                }
                writer.write(System.lineSeparator());
            }
            writer.write(System.lineSeparator());
        }
    }

    public static void saveActualValueTable(int[][] array){
        try {
            FileWriter writer = new FileWriter("resources/games/ActualValueTable.txt");
            writeArrayToFile(writer, array);
            writer.close();
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisywania do pliku: " + e.getMessage());
        }
    }

    private static void writeArrayToFile(FileWriter writer, int[][] array) throws IOException {
        for (int[] row : array) {
            for (int i = 0; i < row.length; i++) {
                writer.write(String.valueOf(row[i]));

                if (i < row.length - 1) {
                    writer.write(",");
                }
            }
            writer.write(System.lineSeparator());
        }
    }

    public static void print(){
        int gridSize = 9;
        int[][] easy = DataForGameBoard.getEasyTable();
        ArrayList<ArrayList<List<Integer>>> nestedList = MainBoardFrame.boardMakerForSave(easy, gridSize);
        System.out.println(nestedList);
    }
}



