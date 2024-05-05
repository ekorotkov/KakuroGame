package pl.edu.pw.elka.prm2t.kakuro.scripts.buttonActions;

import javax.swing.*;
import java.util.Enumeration;

public class DisablingButtonsAfterGameEnds {
    public DisablingButtonsAfterGameEnds(ButtonGroup buttons) {
        for(Enumeration<AbstractButton> buttonEnum = buttons.getElements(); buttonEnum.hasMoreElements();){
                AbstractButton button = buttonEnum.nextElement();
                button.setEnabled(false);
        }
    }
}
