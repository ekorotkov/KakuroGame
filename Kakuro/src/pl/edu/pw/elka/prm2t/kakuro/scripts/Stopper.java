package pl.edu.pw.elka.prm2t.kakuro.scripts;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopper {
    private long startTime;
    private Timer timer;
    private JLabel timeLabel;

    public Stopper(JLabel timeLabel) {
        this.startTime = 0;
        this.timeLabel = timeLabel;
        this.timer = new Timer(1000, new TimerActionListener());
    }

    public void start() {
        if (!timer.isRunning()) {
            startTime = System.currentTimeMillis();
            timer.start();
        }
    }

    public void reset() {
        startTime = 0;
        timer.stop();
        timeLabel.setText("Time: 0 seconds");
    }

    private void updateElapsedTime() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        timeLabel.setText("Time: " + elapsedSeconds + " seconds");
    }

    private class TimerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateElapsedTime();
        }
    }
}
