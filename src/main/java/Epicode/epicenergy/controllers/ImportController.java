package Epicode.epicenergy.controllers;

import Epicode.epicenergy.services.ImportService;
import Epicode.epicenergy.tools.ImportCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/import")
public class ImportController {

    @Autowired
    private ImportService importService;

    @Autowired
    private ImportCSV importCSV;

    @PostMapping("/comuni")
    public ResponseEntity<?> importaComuni() {
        List<String[]> comuniData = importCSV.importComuni();
        importService.importaComuni(comuniData);
        return ResponseEntity.ok("Importazione Comuni completata");
    }

    @PostMapping("/provincie")
    public ResponseEntity<?> importaProvincie() {
        List<String[]> provincieData = importCSV.importProvincie();
        importService.importaProvincie(provincieData);
        return ResponseEntity.ok("Importazione Province completata");
    }

    @PostMapping("/dati-completi")
    public ResponseEntity<?> importaDatiCompleti() {
        List<String[]> provincieData = importCSV.importProvincie();
        List<String[]> comuniData = importCSV.importComuni();
        importService.importaDati(provincieData, comuniData);
        return ResponseEntity.ok("Importazione Comuni e Province completata");
    }
}
