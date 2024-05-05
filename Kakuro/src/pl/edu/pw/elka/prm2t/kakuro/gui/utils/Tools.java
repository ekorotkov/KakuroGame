package pl.edu.pw.elka.prm2t.kakuro.gui.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.sqrt;

public class Tools {

    //image loader
    public static JLabel loadImage(String resource){
        BufferedImage image;
        try{
            InputStream inputStream = Tools.class.getResourceAsStream(resource);
            image = ImageIO.read(inputStream);
            return new JLabel(new ImageIcon(image));
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static String diagonalFraction(int horizontal, int vertical) {
        char horizontalDig[] = new char[]{
                '⁰','¹','²','³','⁴',
                '⁵','⁶','⁷','⁸','⁹'};

        char verticalDig[] = new char[]{
                '₀','₁','₂','₃','₄',
                '₅','₆','₇','₈','₉'};
        //char fractionSlash = '∖';
        char fractionSlash = '\\';
        StringBuilder horizontalStr = new StringBuilder();
        while(horizontal > 0){
            horizontalStr.insert(0, horizontalDig[horizontal % 10]);
            horizontal = horizontal / 10;
        }
        StringBuilder verticalStr = new StringBuilder();
        while(vertical > 0){
            verticalStr.insert(0, verticalDig[vertical % 10]);
            vertical = vertical / 10;
        }

        return verticalStr.toString() + fractionSlash + horizontalStr.toString();
    }

    public static ArrayList<ArrayList<List<Integer>>> tableConverter(ArrayList<List<Integer>> input, int gridSize){
        ArrayList<ArrayList<List<Integer>>> board = new ArrayList<>();
        for(int i=0; i<gridSize;i++){//to do: wartość 10 do zmiany
            ArrayList<List<Integer>> temp = new ArrayList<>();
            board.add(temp);
        }
        for(int l=0; l<sqrt(input.size()); l++) {
            int k = l*gridSize;
            for (int i = 0; i < gridSize; i++) {
                board.get(l).add(input.get(i+k));
            }
        }
        return board;
    }








}
