package pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating;

import pl.edu.pw.elka.prm2t.kakuro.gui.utils.Tools;
import pl.edu.pw.elka.prm2t.kakuro.scripts.KakuroMoveRecorder;

import javax.swing.*;
import javax.swing.plaf.InternalFrameUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainBoardFrame extends JInternalFrame {
    static int length;
    static ArrayList<ArrayList<List<Integer>>> board = new ArrayList<>();
    public static ArrayList<ArrayList<List<Integer>>> getBoard() {
        return board;
    }

    //Usunięcie ramki wokół pola gry (niebieska, defaultowa JInternalFrame)
    @Override
    public void setUI(InternalFrameUI ui) {
        super.setUI(ui); // this gets called internally when updating the ui and makes the northPane reappear
        BasicInternalFrameUI frameUI = (BasicInternalFrameUI) getUI();
        if (frameUI != null) frameUI.setNorthPane(null);
    }

    public MainBoardFrame(JComponent board) {

        Container cc = getContentPane();
        cc.setLayout(new BorderLayout());
        cc.add(board, BorderLayout.CENTER);
        setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
        pack();
        EventQueue.invokeLater(() -> setVisible(true));

    }

    public static BoardPanelGenerator boardMaker(int[][] valuesTable, Integer gridSize){
        SystemOfCreatingNumbersInWhiteFields.tableWriter(valuesTable, gridSize);
        //Length jest potrzebny do solvera. Kolejność jest ważna, może się wszystko rozsypać jeśli będzie zła
        length = valuesTable.length;
        ArrayList<List<Integer>> finalBoard = SystemOfCreatingNumbersInTriangle.finalBoardMaker(valuesTable, gridSize);
        ArrayList<ArrayList<List<Integer>>> boardTable = Tools.tableConverter(finalBoard, gridSize);
        board = boardTable;
        KakuroMoveRecorder moveRecorder = new KakuroMoveRecorder();
        BoardPanelGenerator board = new BoardPanelGenerator(boardTable, moveRecorder,valuesTable);
        return board;
    }
    public static BoardPanelGenerator savedBoardMaker(ArrayList<ArrayList<List<Integer>>> tableBoard,int[][] valuesTable){
        KakuroMoveRecorder moveRecorder = new KakuroMoveRecorder();
        length = valuesTable.length;
        board = tableBoard;
        BoardPanelGenerator board = new BoardPanelGenerator(tableBoard, moveRecorder,valuesTable);
        return board;
    }

    /*
    metody do zapisu pliku
     */
    public static ArrayList<ArrayList<List<Integer>>> boardMakerForSave(int[][] valuesTable, Integer gridSize) {
        SystemOfCreatingNumbersInWhiteFields.tableWriter(valuesTable, gridSize);
        //Length jest potrzebny do solvera. Kolejność jest ważna, może się wszystko rozsypać, jeśli będzie zła
        length = valuesTable.length;
        ArrayList<List<Integer>> finalBoard = SystemOfCreatingNumbersInTriangle.finalBoardMaker(valuesTable, gridSize);
        ArrayList<ArrayList<List<Integer>>> boardTable = Tools.tableConverter(finalBoard, gridSize);
        return boardTable;
    }

    //Potrzebny do sprawdzania tablicy z odpowiedziami...
    public static int getLength() {
        return length;
    }
}


        /*
        Musi być obwódka z zer!
        W przypadku tablicy board, która jest podowana na wejściu całej funkcji,
        0 -> czarne pole, -1 -> pole z trójkątami, -2 -> białe pole, do wypełnienia.
        Z każdego rozmiaru będzie po 10 plansz, poziomy trudności róznia sie zakresem losowanych liczb.
        Rozmiary to 6x6, 10x10, 16x16. Całkowity rozmiar macierzy to kolejno 9x9, 13x13, 19x19.
        Jedna plansza jest 8x8 na razie do testu działania metod z klas SystemOf
         */

        //Textfield nie chce pokazywać dolnych indeksów -> naprawione
            /*
            w przypadku listy list [x, y], która pojawia się na wyjściu -> x oznacza sumę w wierszu, y oznacza sumę w kolumnie
            oczywiście ta zasada tyczy się kiedy lista nie jest równa (0,0) -> czarne pole, (-2,-2) -> pole do wypelnienia przez używkownika
             */


