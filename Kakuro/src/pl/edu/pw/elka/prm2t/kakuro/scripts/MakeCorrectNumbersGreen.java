package pl.edu.pw.elka.prm2t.kakuro.scripts;

import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.BoardPanelGenerator;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.CellFieldsGenerator;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.MainBoardFrame;

import java.awt.*;

public class MakeCorrectNumbersGreen {

    public MakeCorrectNumbersGreen() {
        CellFieldsGenerator[][] cells = BoardPanelGenerator.getCells();
        for(int row =0; row< MainBoardFrame.getLength(); row++) {
            for (int col = 0; col < MainBoardFrame.getLength(); col++) {
                if(cells[row][col].isFocusable()) {
                    cells[row][col].setEditable(false);
                    cells[row][col].setFocusable(false);
                    cells[row][col].setBackground(Color.GREEN);
                }
            }
        }
    }
}
