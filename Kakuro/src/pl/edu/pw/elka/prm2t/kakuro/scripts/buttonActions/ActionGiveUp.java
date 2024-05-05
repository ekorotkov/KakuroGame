package pl.edu.pw.elka.prm2t.kakuro.scripts.buttonActions;

import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.BoardPanelGenerator;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.CellFieldsGenerator;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.MainBoardFrame;

import java.awt.*;
import java.util.Objects;

public class ActionGiveUp {
    public static void generateGiveUp(){
        CellFieldsGenerator[][] cells = BoardPanelGenerator.getCells();
        for(int row = 0; row< MainBoardFrame.getLength(); row++) {
            for(int col=0; col< MainBoardFrame.getLength();col++) {
                if(cells[row][col].isFocusable()) {
                    cells[row][col].setEditable(false);
                    cells[row][col].setFocusable(false);
                    if (Objects.equals(cells[row][col].getText(), Integer.toString(cells[row][col].number))) {
                        cells[row][col].setBackground(Color.GREEN);
                    } else {
                        cells[row][col].setBackground(Color.RED);
                        cells[row][col].setText(Integer.toString(cells[row][col].number));
                    }
                }
            }
        }
    }
}
