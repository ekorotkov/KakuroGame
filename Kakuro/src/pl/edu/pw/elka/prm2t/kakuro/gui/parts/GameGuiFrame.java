package pl.edu.pw.elka.prm2t.kakuro.gui.parts;

import pl.edu.pw.elka.prm2t.kakuro.gui.utils.Constants;
import pl.edu.pw.elka.prm2t.kakuro.scripts.*;
import pl.edu.pw.elka.prm2t.kakuro.scripts.buttonActions.ActionGiveUp;
import pl.edu.pw.elka.prm2t.kakuro.scripts.buttonActions.BoardPrinter;
import pl.edu.pw.elka.prm2t.kakuro.scripts.buttonActions.DisablingButtonsAfterGameEnds;
import pl.edu.pw.elka.prm2t.kakuro.scripts.buttonActions.GiveHint;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.BoardPanelGenerator;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.MainBoardFrame;
import pl.edu.pw.elka.prm2t.kakuro.scripts.gameSaving.GameSaver;
import pl.edu.pw.elka.prm2t.kakuro.scripts.gameSaving.SaveRemover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static pl.edu.pw.elka.prm2t.kakuro.gui.utils.Constants.*;

public class GameGuiFrame extends JFrame {
    public GameGuiFrame(JComponent board, int[][] valuesTable){
        //frame setup
        super("Kakuro - Game");
        setSize(Constants.FRAME_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        //icon setup
        try {
            File icon = new File(ICON_PATH);
        }catch(Exception e){
            System.out.println("Error: " + e);
        }finally{
            ImageIcon imgicon = new ImageIcon(ICON_PATH);
            super.setIconImage(imgicon.getImage());
        }
        //adding components
        addGuiComponents(board, valuesTable);
    }

    private void addGuiComponents(JComponent board, int[][] valuesTable){

        //Inicjacja pola gry
        MainBoardFrame mainBoard = new MainBoardFrame(board);
        mainBoard.setResizable(false);
        mainBoard.setLocation(0,0);
        mainBoard.setSize(728,728);

        //Check
        CustomButton checkButton = new CustomButton("CHECK");
        checkButton.setBounds(790, 150,150,50);
        checkButton.setFont(new Font("Open Sans", Font.BOLD, 30));
        checkButton.setBackground(EASY_COLOR);

        //Hint
        CustomButton hintButton = new CustomButton("HINT");
        hintButton.setBounds(790, 225,150,50);
        hintButton.setFont(new Font("Open Sans", Font.BOLD, 30));
        hintButton.setBackground(MEDIUM_COLOR);

        //Save button
        CustomButton saveButton = new CustomButton("SAVE & EXIT");
        saveButton.setBounds(790, 300,150,50);
        saveButton.setFont(new Font("Open Sans", Font.BOLD, 18));
        saveButton.setBackground(START_COLOR);

        //Print button
        CustomButton printButton = new CustomButton("PRINT");
        printButton.setBounds(790, 375,150,50);
        printButton.setFont(new Font("Open Sans", Font.BOLD, 30));
        printButton.setBackground(PRINT_BUTTON_COLOR);

        //Give up
        CustomButton giveUpButton = new CustomButton("GIVE UP");
        giveUpButton.setBounds(790, 625,150,50);
        giveUpButton.setFont(new Font("Open Sans", Font.BOLD, 28));
        giveUpButton.setBackground(EXIT_COLOR);

        ButtonGroup buttons = new ButtonGroup();
        buttons.add(hintButton);
        buttons.add(checkButton);
        buttons.add(printButton);
        buttons.add(saveButton);
        buttons.add(giveUpButton);

        //stoper
        ActionStoperInternalFrame timer = new ActionStoperInternalFrame();
        timer.setBounds(728,0,280,65);
        timer.start();
        timer.setResizable(false);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(BoardPanelGenerator.isSolved()) {
                    //Wyłączenie przycisków - kod przeniesiony i zedytowany w klasie DisablingButtonsAfterGameEnds
                    new DisablingButtonsAfterGameEnds(buttons);
                    //Zmiana wszystkich pól na zielone - bo wszystkie są poprawne
                    new MakeCorrectNumbersGreen();
                    //Zatrzymanie stopera
                    timer.stop();
                    //Wyświetlenie się informacji o wygranej
                    WinFrame winFrame = new WinFrame(timer.getElapsedTime());
                    winFrame.setVisible(true);
                    //Zamknięcie się okna GameGuiFrame razem z klinięciem New Game na winFrame
                    winFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            dispose();
                        }
                    });
                    //Usunięcie zapisów gry po odkryciu rozwiązania
                    SaveRemover.RemoveSave("resources/games/ActualValueTable.txt");
                    SaveRemover.RemoveSave("resources/games/newGame.txt");
                }
                else{
                    JOptionPane.showMessageDialog(
                            getContentPane(),
                            "Check your puzzle again",
                            "Wrong answer",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GiveHint.generateHint();
                if(BoardPanelGenerator.isSolved()){
                    hintButton.setEnabled(false);
                }
            }
        });

        giveUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Wyłączenie przycisków - kod przeniesiony i zedytowany w klasie DisablingButtonsAfterGameEnds
                new DisablingButtonsAfterGameEnds(buttons);
                ActionGiveUp.generateGiveUp();
                //Zatrzymanie stopera
                timer.stop();
                //Wyświetlanie się ramki informującej o poddaniu się
                GiveUpFrame giveUpFrame = new GiveUpFrame(timer.getElapsedTime());
                giveUpFrame.setVisible(true);
                //Zamknięcie się okna GameGuiFrame razem z klinięciem New Game na giveUpFrame
                giveUpFrame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        dispose();
                    }
                });
                SaveRemover.RemoveSave("resources/games/ActualValueTable.txt");
                SaveRemover.RemoveSave("resources/games/newGame.txt");
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameSaver save = new GameSaver(MainBoardFrame.getBoard());
                System.exit(0);
            }
        });

        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedImage emptyBoard = BoardPrinter.EmptyBoardGetter(GameGuiFrame.this);
                    BoardPrinter.EmptyBoardPrinter(emptyBoard);
                } catch (AWTException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        getContentPane().setBackground(BG_COLOR);
        getContentPane().add(mainBoard);
        getContentPane().add(checkButton);
        getContentPane().add(hintButton);
        getContentPane().add(giveUpButton);
        getContentPane().add(timer);
        getContentPane().add(saveButton);
        getContentPane().add(printButton);
    }
}
