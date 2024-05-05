package pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating;
import pl.edu.pw.elka.prm2t.kakuro.gui.utils.JTextFieldLimit;
import pl.edu.pw.elka.prm2t.kakuro.gui.utils.Tools;

import javax.swing.*;
import java.awt.*;

public class CellFieldsGenerator extends JTextField {
    public static final Color BLACK = Color.BLACK;
    public static final Color WHITE = Color.WHITE;
    public static final Color GREEN = Color.GREEN;
    public static final Font FONT_NUMBERS = new Font("Arial", Font.PLAIN, 25);
    public static final Font FONT_SMALL = new Font("Dialog", Font.PLAIN, 18);
    int row, col;
    int numberHorizontal;
    int numberVertical;
    public int number;

    public CellFieldsGenerator(int row, int col) {
        super();
        this.row = row;
        this.col = col;
        //super.setFont(FONT_NUMBERS);
    }
    public void newGame(int numberHorizontal,int numberVertical, int number) {
        this.number = number;
        this.numberHorizontal = numberHorizontal;
        this.numberVertical = numberVertical;
        paint(numberHorizontal, numberVertical);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getNumberHorizontal() {
        return numberHorizontal;
    }

    public int getNumberVertical() {
        return numberVertical;
    }

    public void paint(int numberHorizontal, int numberVertical){
        if(numberHorizontal == 0 & numberVertical ==0){
            super.setEditable(false);
            super.setFocusable(false);
            super.setHorizontalAlignment(JTextField.CENTER);
            super.setBackground(BLACK);
            super.setForeground(BLACK);
            super.setFont(FONT_NUMBERS);
        }
        else if(numberHorizontal==-2){
            super.setEditable(true);
            super.setFocusable(true);
            super.setHorizontalAlignment(JTextField.CENTER);
            super.setBackground(WHITE);
            super.setForeground(BLACK);
            super.setFont(FONT_NUMBERS);
            super.setDocument(new JTextFieldLimit(1));
            if(numberVertical>0){
                super.setText(Integer.toString(numberVertical));
            }
        }
        else{
            super.setEditable(false);
            super.setFocusable(false);
            super.setHorizontalAlignment(JTextField.CENTER);
            super.setBackground(BLACK);
            super.setForeground(WHITE);
            super.setText(Tools.diagonalFraction(numberHorizontal,numberVertical));
            super.setFont(FONT_SMALL);
            super.isValid();//żeby był warunek do szukania trójkątów
        }
    }
}
