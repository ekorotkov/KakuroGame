package pl.edu.pw.elka.prm2t.kakuro.scripts.buttonActions;

import pl.edu.pw.elka.prm2t.kakuro.scripts.KakuroMoveRecorder;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.BoardPanelGenerator;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.CellFieldsGenerator;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.MainBoardFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GiveHint extends BoardPanelGenerator {
    public GiveHint(ArrayList<ArrayList<List<Integer>>> board, KakuroMoveRecorder moveRecorder, int[][] numbers) {
        super(board, moveRecorder, numbers);
    }
    public static void generateHint() {
        int row;
        int col;
        CellFieldsGenerator[][] cells;
        cells = BoardPanelGenerator.getCells();
        do {
            Random num = new Random();
            row = num.nextInt(MainBoardFrame.getLength()-2)+1;
            col = num.nextInt(MainBoardFrame.getLength()-2)+1;
        } while (!cells[row][col].isFocusable() || Objects.equals(cells[row][col].getText(), Integer.toString(cells[row][col].number)));
        cells[row][col].setForeground(Color.BLUE);
        cells[row][col].setText(Integer.toString(cells[row][col].number));
        cells[row][col].setEditable(false);
    }
}