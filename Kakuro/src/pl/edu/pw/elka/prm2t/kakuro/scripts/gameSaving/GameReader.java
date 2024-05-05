package pl.edu.pw.elka.prm2t.kakuro.scripts.gameSaving;

import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.BoardPanelGenerator;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.MainBoardFrame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class GameReader {
    ArrayList<ArrayList<List<Integer>>> tableBoard = new ArrayList<>();
    int[][] valuesTable;

    public ArrayList<ArrayList<List<Integer>>> getTableBoard() {
        return tableBoard;
    }

    public int[][] getValuesTable() {
        return valuesTable;
    }

    public GameReader() {
        tableBoard = tableReader();
        valuesTable = valueTableReader();
    }

    public BoardPanelGenerator newGame() {
        return MainBoardFrame.savedBoardMaker(tableBoard, valuesTable);
    }

    public static ArrayList<ArrayList<List<Integer>>> tableReader() {
        String filePath = "resources/games/newGame.txt";
        try {
            ArrayList<ArrayList<List<Integer>>> nestedList = readNestedListFromFile(filePath);
            return nestedList;
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas odczytywania pliku: " + e.getMessage());
        }
        return null;
    }

    public static int[][] valueTableReader() {
        String filePath = "resources/games/ActualValueTable.txt";
        try {
            int[][] array = readArrayFromFile(filePath);
            // Wyświetlanie odczytanej tablicy
            return array;
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas odczytywania pliku: " + e.getMessage());
        }
        return null;
    }

    private static int[][] readArrayFromFile(String filePath) throws IOException {
        List<int[]> rows = new ArrayList<>();

        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] numbers = line.split(",");
            int[] row = new int[numbers.length];

            for (int i = 0; i < numbers.length; i++) {
                row[i] = Integer.parseInt(numbers[i]);
            }
            rows.add(row);
        }
        bufferedReader.close();
        fileReader.close();

        int[][] array = new int[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            array[i] = rows.get(i);
        }

        return array;
    }

    private static ArrayList<ArrayList<List<Integer>>> readNestedListFromFile(String filePath) throws IOException {
        ArrayList<ArrayList<List<Integer>>> nestedList = new ArrayList<>();

        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line;
        ArrayList<List<Integer>> outerList = new ArrayList<>();

        while ((line = bufferedReader.readLine()) != null) {
            if (line.isEmpty()) {
                // Dodanie odczytanej listy do struktury
                nestedList.add(outerList);
                outerList = new ArrayList<>();
            } else {
                String[] numbers = line.split(",");
                List<Integer> innerList = new ArrayList<>();

                for (String number : numbers) {
                    innerList.add(Integer.parseInt(number));
                }

                outerList.add(innerList);
            }
        }
        bufferedReader.close();
        fileReader.close();
        return nestedList;
    }
}