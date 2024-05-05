package pl.edu.pw.elka.prm2t.kakuro.scripts.buttonActions;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BoardPrinter {

    public static void EmptyBoardPrinter(BufferedImage emptyBoard) {
        //tworzenie katalogu na plansze do wydruku
        if(Files.notExists(Path.of("resources/savedBoards"))){
            new File("resources/savedBoards").mkdirs();
        }
        try {
            File outputfile = new File("resources/savedBoards/savedBoard.png");
            ImageIO.write(emptyBoard, "png", outputfile);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static BufferedImage EmptyBoardGetter(JFrame frame) throws AWTException {
        Robot robot = new Robot();
        int x = frame.getX()+15;
        int y = frame.getY()+37;
        int width = 713;
        int height = 713;
        Rectangle area = new Rectangle(x, y, width, height);
        return robot.createScreenCapture(area);
    }
}
