package pl.edu.pw.elka.prm2t.kakuro.gui.parts;

import javax.swing.*;
import javax.swing.plaf.InternalFrameUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionStoperInternalFrame extends JInternalFrame {

    //Usunięcie ramki wokół pola gry (niebieska, defaultowa JInternalFrame)
    @Override
    public void setUI(InternalFrameUI ui) {
        super.setUI(ui); // this gets called internally when updating the ui and makes the northPane reappear
        BasicInternalFrameUI frameUI = (BasicInternalFrameUI) getUI();
        if (frameUI != null) frameUI.setNorthPane(null);
    }
    private final JLabel timeLabel;
    private final Timer timer;
    private int elapsedTime;
    private boolean isRunning;

    public ActionStoperInternalFrame() {
        setTitle("Stopwatch");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 40));
        add(timeLabel);

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                elapsedTime += 1000;
                updateTimeLabel();
            }
        });

        isRunning = false;
        EventQueue.invokeLater(() -> setVisible(true));
    }

    public void start() {
        if (!isRunning) {
            timer.start();
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            timer.stop();
            isRunning = false;
        }
    }
    private void updateTimeLabel() {
        int hours = elapsedTime / 3600000;
        int minutes = (elapsedTime / 60000) % 60;
        int seconds = (elapsedTime / 1000) % 60;

        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timeLabel.setText(time);
    }

    public int getElapsedTime() {
        return elapsedTime;
    }
}
