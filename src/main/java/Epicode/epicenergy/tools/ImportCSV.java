package Epicode.epicenergy.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ImportCSV {
    public void importComuni(){
        String filePath= new File("./").getAbsolutePath();
        boolean isFirstLine=true;

        try(BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath)))) {
            String line;

        }
    }
}
