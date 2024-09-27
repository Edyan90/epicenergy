package Epicode.epicenergy.services;

import Epicode.epicenergy.entities.Fattura;
import Epicode.epicenergy.enums.StatoFattura;
import Epicode.epicenergy.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class FatturaService {

    @Autowired
    private FatturaRepository fatturaRepository;


    public Fattura saveFattura(Fattura fattura) {
        return fatturaRepository.save(fattura);
    }


    public List<Fattura> getFattureByClienteId(UUID clienteId) {
        return fatturaRepository.findByClienteId(clienteId);
    }


    public List<Fattura> getFattureByStato(StatoFattura stato) {
        return fatturaRepository.findByStato(stato);
    }


    public List<Fattura> getFattureByDateRange(LocalDate startDate, LocalDate endDate) {
        return fatturaRepository.findByDataBetween(startDate, endDate);
    }


    public List<Fattura> getFattureByAnno(Integer anno) {
        return fatturaRepository.findByAnno(anno);
    }


    public List<Fattura> getFattureByImportoRange(BigDecimal minImporto, BigDecimal maxImporto) {
        return fatturaRepository.findByImportoBetween(minImporto, maxImporto);
    }


    public List<Fattura> getFattureFiltrate(UUID clienteId, StatoFattura stato, BigDecimal minImporto, BigDecimal maxImporto, LocalDate startDate, LocalDate endDate) {
        return fatturaRepository.findByClienteIdAndStatoAndImportoBetweenAndDataBetween(clienteId, stato, minImporto, maxImporto, startDate, endDate);
    }

    public List<Fattura> getFatturePerAnno(int anno) {
        return fatturaRepository.findByAnno(anno);
    }
    
}
