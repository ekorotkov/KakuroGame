package pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating;

import pl.edu.pw.elka.prm2t.kakuro.scripts.KakuroMoveRecorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BoardPanelGenerator extends JPanel {
    public static final int CELL_SIZE = 60;
    private static KakuroMoveRecorder moveRecorder;
    private final CellFieldsGenerator[][] cells;
    protected static final CellFieldsGenerator[][] temp = new CellFieldsGenerator[30][30];

    public static CellFieldsGenerator[][] getCells() {
        return temp;
    }
    private void paste(){
        for (int row = 0; row < cells.length; ++row) {
            for (int col = 0; col < cells.length; ++col) {
                temp[row][col] = cells[row][col];
            }
        }
    }
    public BoardPanelGenerator(ArrayList<ArrayList<List<Integer>>> board, KakuroMoveRecorder moveRecorder, int[][]numbers) {
        this.moveRecorder = moveRecorder;
        List<Integer> dimension = getDimension(board);
        int rows = dimension.get(0);
        int cols = dimension.get(1);
        cells = cellMaker(board);
        super.setLayout(new GridLayout(rows, cols));
        cellAllocator(cells,board,rows,cols,numbers);
        cellMovement(cells,rows,cols);
        paste();
    }


    private CellFieldsGenerator[][] cellMaker(ArrayList<ArrayList<List<Integer>>> board) {
        int rows = board.size();
        int cols = board.get(0).size();
        return new CellFieldsGenerator[rows][cols];
    }

    private List<Integer> getDimension(ArrayList<ArrayList<List<Integer>>> board) {
        int rows = board.size();
        int cols = board.get(0).size();
        return List.of(rows, cols);
    }
    private void cellAllocator(CellFieldsGenerator[][] cells, ArrayList<ArrayList<List<Integer>>> board, int rows, int cols, int[][] numbers){
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                cells[row][col] = new CellFieldsGenerator(row, col);
                super.add(cells[row][col]);
            }
        }
        super.setPreferredSize(new Dimension(CELL_SIZE * cols, CELL_SIZE * rows));
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                cells[row][col].newGame(board.get(row).get(col).get(0), board.get(row).get(col).get(1),numbers[row][col]);
            }
        }
    }
    //focus listener do zmiany kolorów i do poruszania się po planszy
    private void cellMovement(CellFieldsGenerator[][] cells, int rows, int cols){
        CellInputListener listener = new CellInputListener();
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (cells[row][col].isFocusable()) {
                    cells[row][col].addFocusListener(listener);
                    int finalRow = row;
                    int finalCol = col;
                    cells[row][col].getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"PressedR");
                    cells[row][col].getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"PressedL");
                    cells[row][col].getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"PressedD");
                    cells[row][col].getInputMap().put(KeyStroke.getKeyStroke("UP"),"PressedU");
                    cells[row][col].getActionMap().put("PressedU", new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(cells[finalRow -1][finalCol].isFocusable()) {
                                cells[finalRow -1][finalCol].grabFocus();
                            }
                        }
                    });

                    cells[row][col].getActionMap().put("PressedD", new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(cells[finalRow +1][finalCol].isFocusable()) {
                                cells[finalRow +1][finalCol].grabFocus();
                            }
                        }
                    });
                    cells[row][col].getActionMap().put("PressedL", new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(cells[finalRow][finalCol-1].isFocusable()) {
                                cells[finalRow][finalCol-1].grabFocus();
                            }
                        }
                    });
                    cells[row][col].getActionMap().put("PressedR", new AbstractAction() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(cells[finalRow][finalCol+1].isFocusable()) {
                                cells[finalRow][finalCol + 1].grabFocus();
                            }
                        }
                    });
                }
            }
        }
    }

    private static class CellEndGameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            CellFieldsGenerator sourceCell = (CellFieldsGenerator)e.getSource();
            if(Objects.equals(sourceCell.getText(), Integer.toString(sourceCell.number))){
                sourceCell.setBackground(Color.GREEN);
            }
            else{
                sourceCell.setBackground(Color.RED);
            }
        }
    }

    private static class CellInputListener implements FocusListener {

        //kliknięte pole ustawia kolor i kursor na początkowym indeksie (jedna cyfra, a więc łatwo to zrobić)
        //EDIT: zmienione tak, że dla danego pola nie trzeba robić backspace'a aby zmienić cyfrę
        @Override
        public void focusGained(FocusEvent e) {
            CellFieldsGenerator sourceCell = (CellFieldsGenerator)e.getSource();
            sourceCell.setBackground(Color.GRAY);
            sourceCell.setCaretColor(Color.GRAY);
            //sourceCell.setCaretPosition(1);


            sourceCell.getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("1"),"Pressed1");
            sourceCell.getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("2"),"Pressed2");
            sourceCell.getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("3"),"Pressed3");
            sourceCell.getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("4"),"Pressed4");
            sourceCell.getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("5"),"Pressed5");
            sourceCell.getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("6"),"Pressed6");
            sourceCell.getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("7"),"Pressed7");
            sourceCell.getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("8"),"Pressed8");
            sourceCell.getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("9"),"Pressed9");
            sourceCell.getInputMap(WHEN_FOCUSED).put(KeyStroke.getKeyStroke("BACK_SPACE"),"Delete");

            sourceCell.getActionMap().put("Delete", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(sourceCell.isEditable()) {
                        sourceCell.setText("");
                        moveRecorder.recordMove(sourceCell.getRow(), sourceCell.getCol(), "");
                    }
                }
            });
            sourceCell.getActionMap().put("Pressed1", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(sourceCell.isEditable()) {
                            sourceCell.setText("1");
                            moveRecorder.recordMove(sourceCell.getRow(), sourceCell.getCol(), "1");
                        }
                    }
                });
                sourceCell.getActionMap().put("Pressed2", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(sourceCell.isEditable()) {
                            sourceCell.setText("2");
                            moveRecorder.recordMove(sourceCell.getRow(), sourceCell.getCol(), "2");
                        }
                    }
                });
                sourceCell.getActionMap().put("Pressed3", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(sourceCell.isEditable()) {
                            sourceCell.setText("3");
                            moveRecorder.recordMove(sourceCell.getRow(), sourceCell.getCol(), "3");
                        }
                    }
                });
                sourceCell.getActionMap().put("Pressed4", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(sourceCell.isEditable()) {
                            sourceCell.setText("4");
                            moveRecorder.recordMove(sourceCell.getRow(), sourceCell.getCol(), "4");
                        }
                    }
                });
                sourceCell.getActionMap().put("Pressed5", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(sourceCell.isEditable()) {
                            sourceCell.setText("5");
                            moveRecorder.recordMove(sourceCell.getRow(), sourceCell.getCol(), "5");
                        }
                    }
                });
                sourceCell.getActionMap().put("Pressed6", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(sourceCell.isEditable()) {
                            sourceCell.setText("6");
                            moveRecorder.recordMove(sourceCell.getRow(), sourceCell.getCol(), "6");
                        }
                    }
                });
                sourceCell.getActionMap().put("Pressed7", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(sourceCell.isEditable()) {
                            sourceCell.setText("7");
                            moveRecorder.recordMove(sourceCell.getRow(), sourceCell.getCol(), "7");
                        }
                    }
                });
                sourceCell.getActionMap().put("Pressed8", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(sourceCell.isEditable()) {
                            sourceCell.setText("8");
                            moveRecorder.recordMove(sourceCell.getRow(), sourceCell.getCol(), "8");
                        }
                    }
                });
                sourceCell.getActionMap().put("Pressed9", new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(sourceCell.isEditable()) {
                            sourceCell.setText("9");
                            moveRecorder.recordMove(sourceCell.getRow(), sourceCell.getCol(), "9");
                            //makeGreenTriangle(sourceCell.getRow(), sourceCell.getCol()) -> przykład
                        }
                    }
                });
        }

        @Override
        public void focusLost(FocusEvent e) {
            CellFieldsGenerator sourceCell = (CellFieldsGenerator)e.getSource();
            sourceCell.setBackground(Color.WHITE);
            sourceCell.setCaretColor(Color.WHITE);
        }
    }
    public static boolean isSolved() {
        for (int row = 0; row < MainBoardFrame.length; ++row) {
            for (int col = 0; col < MainBoardFrame.length; ++col) {
                    if (!Objects.equals(temp[row][col].getText(), Integer.toString(temp[row][col].number))&&temp[row][col].isEditable()) {
                        return false;
                    }
            }
        }
        return true;
    }
}