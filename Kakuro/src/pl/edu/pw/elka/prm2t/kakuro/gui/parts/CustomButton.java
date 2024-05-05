package pl.edu.pw.elka.prm2t.kakuro.gui.parts;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {
    public CustomButton(String text){
        setText(text);
        setFont(new Font("Open Sans", Font.BOLD, 40));
        setForeground(Color.WHITE);
        setOpaque(true);
        setFocusPainted(false);
        setBorderPainted(false);
    }

}
