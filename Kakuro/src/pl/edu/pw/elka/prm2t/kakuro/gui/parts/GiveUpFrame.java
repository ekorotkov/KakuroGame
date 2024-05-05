package pl.edu.pw.elka.prm2t.kakuro.gui.parts;
import pl.edu.pw.elka.prm2t.kakuro.gui.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import static pl.edu.pw.elka.prm2t.kakuro.gui.utils.Constants.*;

public class GiveUpFrame extends JFrame{
    public GiveUpFrame(int time){
        //frame setup
        super("End Of The Game");
        setSize(Constants.ENDGAME_FRAME_SIZE);
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
        addGuiComponents(time);
    }

    private void addGuiComponents(int time) {
        //Congratulations text
        JLabel congratsText = new JLabel("YOU GAVE UP! :(");
        congratsText.setBounds(70,15,400,30);
        congratsText.setFont(new Font("Open Sans", Font.BOLD, 30));
        congratsText.setForeground(Color.WHITE);
        congratsText.setOpaque(false);

        //You solved the puzzle text
        JLabel solvedPuzzleText = new JLabel("GOOD LUCK NEXT TIME!");
        solvedPuzzleText.setBounds(77,55,400,30);
        solvedPuzzleText.setFont(new Font("Open Sans",Font.PLAIN, 20));
        solvedPuzzleText.setForeground(Color.WHITE);
        solvedPuzzleText.setOpaque(false);

        //New game button
        CustomButton newGameButton = new CustomButton("NEW GAME");
        newGameButton.setBounds(40, 145,150,50);
        newGameButton.setFont(new Font("Open Sans", Font.BOLD, 20));
        newGameButton.setBackground(EASY_COLOR);


        //Exit game button
        CustomButton exitGameButton = new CustomButton("EXIT");
        exitGameButton.setBounds(200, 145,150,50);
        exitGameButton.setFont(new Font("Open Sans", Font.BOLD, 20));
        exitGameButton.setBackground(EXIT_COLOR);

        //Time result text
        int hours = time / 3600000;
        int minutes = (time/ 60000) % 60;
        int seconds = (time / 1000) % 60;

        JLabel timeText = new JLabel(String.format("%02d:%02d:%02d", hours, minutes, seconds));
        timeText.setBounds(135,95,400,30);
        timeText.setFont(new Font("Open Sans", Font.BOLD, 30));
        timeText.setForeground(Color.WHITE);
        timeText.setOpaque(false);

        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuGuiFrame().setVisible(true);
                dispose();
            }
        });

        getContentPane().setBackground(BG_COLOR);
        getContentPane().add(congratsText);
        getContentPane().add(solvedPuzzleText);
        getContentPane().add(newGameButton);
        getContentPane().add(exitGameButton);
        getContentPane().add(timeText);
    }
}
