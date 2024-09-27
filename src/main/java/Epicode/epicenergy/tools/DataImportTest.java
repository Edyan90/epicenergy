package Epicode.epicenergy.tools;

import Epicode.epicenergy.services.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataImportTest implements CommandLineRunner {

    @Autowired
    private ImportService importService;

    @Autowired
    private ImportCSV importCSV;

    @Override
    public void run(String... args) throws Exception {
        startImport();
    }

    public void startImport() {
        try {
            // Importa province
            List<String[]> provinceData = importCSV.importProvincie();
            importService.importaProvincie(provinceData);
            System.out.println("Province importate correttamente!");

            // Importa comuni
            List<String[]> comuniData = importCSV.importComuni();
            importService.importaComuni(comuniData);
            System.out.println("Comuni importati correttamente!");

            System.out.println("Importazione completata!");
        } catch (Exception e) {
            System.err.println("Si è verificato un errore durante l'importazione: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
