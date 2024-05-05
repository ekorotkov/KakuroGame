package pl.edu.pw.elka.prm2t.kakuro.gui.parts;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalToggleButtonUI;
import java.awt.*;

import static pl.edu.pw.elka.prm2t.kakuro.gui.utils.Constants.EASY_COLOR;

public class CustomToggleButton extends JToggleButton {
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.setBorderPainted(this.hasFocus());
    }

    public CustomToggleButton(String text){
        setText(text);
        setFont(new Font("Open Sans", Font.BOLD, 20));
        setForeground(Color.WHITE);
        setOpaque(true);
        setFocusPainted(false);
        setBorderPainted(false);
        setUI(new MetalToggleButtonUI() {
            @Override
            protected Color getSelectColor() {
                return getBackground().darker().darker();
            }
        });

    }


}
