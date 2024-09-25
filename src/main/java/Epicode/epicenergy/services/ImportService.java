package Epicode.epicenergy.services;

import Epicode.epicenergy.entities.Comune;
import Epicode.epicenergy.entities.Provincia;
import Epicode.epicenergy.repositories.ComuneRepository;
import Epicode.epicenergy.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImportService {

    @Autowired
    private ComuneRepository comuneRepository;

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public void importaDati(List<String[]> datiProvincie, List<String[]> datiComuni) {
        importaProvincie(datiProvincie);
        importaComuni(datiComuni);
    }

    public void importaProvincie(List<String[]> datiProvincie) {
        for (String[] record : datiProvincie) {
            Provincia provincia = new Provincia();
            provincia.setSigla(record[0].trim());    // Colonna 1: Sigla
            provincia.setProvincia(record[1].trim()); // Colonna 2: Nome Provincia
            provincia.setRegione(record[2].trim());  // Colonna 3: Regione

            provinciaRepository.save(provincia);
        }
    }

    public void importaComuni(List<String[]> datiComuni) {
        for (String[] record : datiComuni) {
            if (record.length < 4) {
                System.err.println("Record non valido per comune: " + String.join(", ", record));
                continue;
            }

            Comune comune = new Comune();
            comune.setCodiceProvincia(record[0].trim());

            try {
                comune.setProgressivoComune(Integer.parseInt(record[1].trim()));
            } catch (NumberFormatException e) {
                System.err.println("Errore nel progressivo comune per il comune: " + record[2] + ". Valore trovato: " + record[1]);
                continue;
            }

            comune.setDenominazione(record[2].trim());


            Optional<Provincia> provinciaOpt = provinciaRepository.findBySigla(record[0].trim());


            if (provinciaOpt.isEmpty()) {
                System.err.println("Provincia non trovata per sigla: " + record[0] + ". Provo a cercare per denominazione: " + record[3]);
                provinciaOpt = provinciaRepository.findByProvincia(record[3].trim());
            }


            if (provinciaOpt.isEmpty()) {
                System.err.println("Provincia non trovata per comune: " + record[2] + ". Codice provincia: " + record[0] + ", Denominazione provincia: " + record[3]);
                continue;
            }

            Provincia provincia = provinciaOpt.get();
            comune.setProvincia(provincia);

            comuneRepository.save(comune);
        }
    }
}
