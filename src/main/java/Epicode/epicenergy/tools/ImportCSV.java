package Epicode.epicenergy.tools;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImportCSV {
    private String normalizeProvince(String vecchiaDenominazione) {
        switch (vecchiaDenominazione) {
            case "Verbania":
                return "Verbano-Cusio-Ossola";
            case "Aosta":
                return "Valle d'Aosta/Vallèee d'Aoste";
            case "Monza-Brianza":
                return "Monza e della Brianza";
            case "Bolzano":
                return "Bolzano/Bozen";
            case "La-Spezia":
                return "La Spezia";
            case "Reggio-Emilia":
                return "Reggio nell'Emilia";
            case "Forli-Cesena":
                return "Forlì-Cesena";
            case "Pesaro-Urbino":
                return "Pesaro e Urbino";
            case "Ascoli-Piceno":
                return "Ascoli Piceno";
            case "Reggio-Calabria":
                return "Reggio Calabria";
            case "Vibo-Valentia":
                return "Vibo Valentia";
            case "Carbonia Iglesias", "Medio Campidano":
                return "Sud Sardegna";
            case "Olbia Tempio":
                return "Sassari";
            case "Ogliastra":
                return "Nuoro";
            default:
                return vecchiaDenominazione;
        }
    }

    public List<String[]> importComuni() {
        String filePath = new File("src/main/resources/comuni-italiani.csv").getAbsolutePath();
        boolean isFirstLine = true;
        List<String[]> comuni = new ArrayList<>();
        int counter = 1;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] columns = line.split(";");

                for (int i = 0; i < columns.length; i++) {
                    if (columns[i].equals("#RIF!")) {
                        columns[i] = String.format("%03d", counter);
                        counter++;
                    }
                }
                comuni.add(columns);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return comuni;
    }

    public List<String[]> importProvincie() {
        String filePath = new File("src/main/resources/province-italiane.csv").getAbsolutePath();
        boolean isFirstLine = true;
        List<String[]> provincie = new ArrayList<>();
        int counter = 1;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] columns = line.split(";");
                
                for (int i = 0; i < columns.length; i++) {
                    if (columns[i].equals("#RIF!")) {
                        columns[i] = String.format("%03d", counter);
                        counter++;
                    }
                }
                provincie.add(columns);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return provincie;
    }


}
