package pl.edu.pw.elka.prm2t.kakuro.scripts.gameSaving;

import java.io.File;

public class SaveRemover {
    public static void RemoveSave(String filePath){
        File myObj = new File(filePath);
        myObj.delete();
    }
}
