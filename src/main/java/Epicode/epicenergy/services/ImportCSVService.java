package Epicode.epicenergy.services;

import Epicode.epicenergy.entities.Comune;
import Epicode.epicenergy.entities.Provincia;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImportCSVService {
    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private ComuneService comuneService;

    @Transactional
    public void importProvince() {
        String filePath = new File("/mnt/data/province-italiane.csv").getAbsolutePath();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = bufferedReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] columns = line.split(";");
                String sigla = columns[0];
                String provinciaName = columns[1];
                String regione = columns[2];

                Provincia provincia = new Provincia();
                provincia.setSigla(sigla);
                provincia.setProvincia(provinciaName);
                provincia.setRegione(regione);

                provinciaService.save(provincia);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Transactional
    public void importComuni() {
        String filePath = new File("/mnt/data/comuni-italiani.csv").getAbsolutePath();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = bufferedReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] columns = line.split(";");
                String codiceProvincia = columns[0];
                Integer progressivoComune = Integer.parseInt(columns[1]);
                String denominazione = columns[2];
                String siglaProvincia = columns[3];


                Optional<Provincia> provinciaOptional = provinciaService.findBySigla(siglaProvincia);
                if (provinciaOptional.isPresent()) {
                    Provincia provincia = provinciaOptional.get();

                    Comune comune = new Comune();
                    comune.setCodiceProvincia(codiceProvincia);
                    comune.setProgressivoComune(progressivoComune);
                    comune.setDenominazione(denominazione);
                    comune.setProvincia(provincia);

                    comuneService.salva(comune);
                } else {
                    System.out.println("Provincia non trovata per la sigla: " + siglaProvincia);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

