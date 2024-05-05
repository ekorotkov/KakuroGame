package pl.edu.pw.elka.prm2t.kakuro.gui.utils;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;


//Klasa ograniczająca to, co można wpisać do pól
public class JTextFieldLimit extends PlainDocument {

    //Zmienna limitu znaków
    private final int limit;

    //Lista liczb, które są akceptowane w polach
    String[] numbers = {"","1", "2", "3", "4", "5", "6", "7", "8", "9"};
    boolean isAccepted = false;

    public JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;

        //Kod sprawdzający, czy wprowadzany znak spełnia kryteria
        for (String thisnumber : numbers) {
            isAccepted = str.equals(thisnumber);
            if (isAccepted) {
                if ((getLength() + str.length()) <= limit) {
                    super.insertString(offset, str, attr);
                }
            }
        }
    }
}
