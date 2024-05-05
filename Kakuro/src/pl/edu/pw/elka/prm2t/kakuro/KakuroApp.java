package pl.edu.pw.elka.prm2t.kakuro;

import pl.edu.pw.elka.prm2t.kakuro.gui.parts.MenuGuiFrame;

import javax.swing.*;

public class KakuroApp{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuGuiFrame().setVisible(true);
            }
        });
    }
}
