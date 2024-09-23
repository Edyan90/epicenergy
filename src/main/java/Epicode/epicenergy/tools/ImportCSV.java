package Epicode.epicenergy.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportCSV {
    public List<String[]> importComuni() {
        String filePath = new File("src/main/resources/comuni-italiani.csv").getAbsolutePath();
        boolean isFirstLine = true;
        List<String[]> comuni = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] columns = line.split(";");
                comuni.add(columns);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return comuni;
    }
}
