package pl.edu.pw.elka.prm2t.kakuro.gui.parts;
import pl.edu.pw.elka.prm2t.kakuro.gui.utils.Constants;
import pl.edu.pw.elka.prm2t.kakuro.gui.utils.Tools;
import pl.edu.pw.elka.prm2t.kakuro.scripts.DataForGameBoard;
import pl.edu.pw.elka.prm2t.kakuro.scripts.gameSaving.GameReader;
import pl.edu.pw.elka.prm2t.kakuro.scripts.gameSaving.GameSaver;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.BoardPanelGenerator;
import pl.edu.pw.elka.prm2t.kakuro.scripts.boardGenerating.MainBoardFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static pl.edu.pw.elka.prm2t.kakuro.gui.utils.Constants.*;

public class MenuGuiFrame extends JFrame {
    Integer gridSize;

    public Integer getGridSize() {
        return gridSize;
    }

    public MenuGuiFrame(){
        //frame setup
        super("Kakuro");
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
        addGuiComponents();
    }

    private void addGuiComponents(){
        //kakuro text img
        JLabel kakuroImage = Tools.loadImage(KAKURO_TEXT_PATH);
        if (kakuroImage!=null){
            kakuroImage.setBounds(
                    245,
                    50,
                    KAKURO_TEXT_SIZE.width, KAKURO_TEXT_SIZE.height

            );
        }


        // ----------- ELEMENTS SETUP ------------

        //buttons - start, level difficulties, exit

        //startButton
        CustomButton startButton = new CustomButton("START NEW GAME");
        startButton.setBounds(90, 220,400,100);
        startButton.setFont(new Font("Open Sans", Font.BOLD, 25));
        startButton.setBackground(START_COLOR);

        //easyButton
        CustomToggleButton easyButton = new CustomToggleButton("EASY");
        easyButton.setBounds(295, 405,130,75);
        easyButton.setBackground(EASY_COLOR);

        //mediumButton
        CustomToggleButton mediumButton = new CustomToggleButton("MEDIUM");
        mediumButton.setBounds(442, 405,130,75);
        mediumButton.setBackground(MEDIUM_COLOR);

        //hardButton
        CustomToggleButton hardButton = new CustomToggleButton("HARD");
        hardButton.setBounds(589, 405,130,75);
        hardButton.setBackground(EXIT_COLOR);

        //exitButton
        CustomButton exitButton = new CustomButton("EXIT");
        exitButton.setBounds(295, 520,424,100);
        exitButton.setBackground(EXIT_COLOR);

        //continue button
        CustomButton continueButton = new CustomButton("CONTINUE PREVIOUS GAME");
        continueButton.setBounds(525, 220,400,100);
        continueButton.setFont(new Font("Open Sans", Font.BOLD, 25));
        continueButton.setBackground(PRINT_BUTTON_COLOR);


        //footer
        JLabel namesText = new JLabel("ADAM DOŁOWY | CEZARY CHARUBA | ERNEST KOROTKOV | WITOLD WĄSAK | TOMASZ ZĘBALA");
        namesText.setBounds(175,610,1000,100);
        namesText.setFont(new Font("Open Sans", Font.BOLD, 14));
        namesText.setForeground(OPACITY_COLOR);;

        JLabel yearText = new JLabel("2023 PW EITI");
        yearText.setBounds(465,640,624,100);
        yearText.setFont(new Font("Open Sans", Font.BOLD, 14));
        yearText.setForeground(OPACITY_COLOR);
        yearText.setOpaque(false);

        //Select difficulty text
        JLabel selectText = new JLabel("SELECT DIFFICULTY");
        selectText.setBounds(390,310,624,100);
        selectText.setFont(new Font("Open Sans", Font.BOLD, 25));
        selectText.setForeground(Color.WHITE);
        selectText.setOpaque(false);


        ButtonGroup difficultyGroup = new ButtonGroup();

        difficultyGroup.add(easyButton);
        difficultyGroup.add(mediumButton);
        difficultyGroup.add(hardButton);

        //------------- ACTIONS -----------------

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //Akcje przycisku Start Game
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Files.notExists(Path.of("resources/games"))){
                    new File("resources/games").mkdirs();
                }
                if(easyButton.isSelected()){
                    //Generowanie łatwej planszy
                    gridSize = 9;
                    int[][] easy = DataForGameBoard.getEasyTable();
                    BoardPanelGenerator board = MainBoardFrame.boardMaker(easy, gridSize);
                    GameSaver.saveActualValueTable(easy);
                    new GameGuiFrame(board, easy).setVisible(true);
                    dispose();

                } else if (mediumButton.isSelected()) {
                    gridSize = 13;
                    int[][] medium = DataForGameBoard.getMediumTable();
                    BoardPanelGenerator board = MainBoardFrame.boardMaker(medium, gridSize);
                    GameSaver.saveActualValueTable(medium);
                    new GameGuiFrame(board, medium).setVisible(true);
                    dispose();

                } else if (hardButton.isSelected()) {
                    gridSize = 19;
                    int[][] hard = DataForGameBoard.getHardTable();
                    BoardPanelGenerator board = MainBoardFrame.boardMaker(hard, gridSize);
                    GameSaver.saveActualValueTable(hard);
                    new GameGuiFrame(board, hard).setVisible(true);
                    dispose();
                } else{
                    //Warunek, który wyrzuca informację, o braku wyboru poziomu trudności.
                    JOptionPane.showMessageDialog(
                            getContentPane(),
                            "SELECT DIFFICULTY!",
                            "No difficulty selected",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(easyButton.hasFocus())
                         easyButton.setBorder(new LineBorder(SELECTED_FRAME_COLOR, 3));
                else{
                    easyButton.setBorder(new LineBorder(Color.WHITE, 0));
                }
            }
        });

        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mediumButton.hasFocus())
                    mediumButton.setBorder(new LineBorder(SELECTED_FRAME_COLOR, 3));
                else{
                    mediumButton.setBorder(new LineBorder(Color.WHITE, 0));
                }
            }
        });

        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(hardButton.hasFocus())
                    hardButton.setBorder(new LineBorder(SELECTED_FRAME_COLOR, 3));
                else{
                    hardButton.setBorder(new LineBorder(Color.WHITE, 0));
                }
            }
        });

        //Akcje przycisku kontynuowania gry
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Files.notExists(Path.of("resources/games/ActualValueTable.txt"))){
                    continueButton.setEnabled(false);
                }else{
                    GameReader reader = new GameReader();
                    int[][] values = reader.getValuesTable();
                    BoardPanelGenerator board = reader.newGame();
                    new GameGuiFrame(board, values).setVisible(true);
                    dispose();
                }
            }
        });


        //-------------- ADDING CONTENT TO PANE --------------------

        getContentPane().setBackground(BG_COLOR);
        if (kakuroImage!=null){
            getContentPane().add(kakuroImage);
        }

        getContentPane().add(startButton);
        getContentPane().add(easyButton);
        getContentPane().add(mediumButton);
        getContentPane().add(hardButton);
        getContentPane().add(exitButton);
        getContentPane().add(namesText);
        getContentPane().add(yearText);
        getContentPane().add(selectText);
        getContentPane().add(continueButton);
    }
}
