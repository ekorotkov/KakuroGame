package pl.edu.pw.elka.prm2t.kakuro.gui.utils;
/*
Instancji tej klasy są dla opisu ruchów gracza. Przy nowym ruchu jest
tworzona instancja tej klasy i zapisywana do stosu w klasie KakuroMoveRecorder.
 */

public class KakuroMove {
    private int row;
    private int col;
    private String value;

    public KakuroMove(int row, int col, String value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getValue() {
        return value;
    }
}
