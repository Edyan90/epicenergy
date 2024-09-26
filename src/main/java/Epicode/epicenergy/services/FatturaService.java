package Epicode.epicenergy.services;

import Epicode.epicenergy.RecordsDTO.FatturaDTO;
import Epicode.epicenergy.RecordsDTO.StatoFatturaDTO;
import Epicode.epicenergy.entities.Cliente;
import Epicode.epicenergy.entities.Fattura;
import Epicode.epicenergy.entities.StatoFattura;
import Epicode.epicenergy.enums.StatoFatturaEnum;
import Epicode.epicenergy.repositories.ClienteRepository;
import Epicode.epicenergy.repositories.FatturaRepository;
import Epicode.epicenergy.repositories.StatoFatturaRepository;
import jakarta.persistence.EntityNotFoundException;
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

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private StatoFatturaRepository statoFatturaRepository;


    public Fattura saveFattura(FatturaDTO fattura) {
//        StatoFattura stato = statoFatturaRepository.findById(fatturaDTO.getId())
//                .orElseThrow(() -> new RuntimeException("Stato della fattura non trovato: " + fatturaDTO.getNumeroFattura()));

        Fattura nuovaFattura = new Fattura();
        nuovaFattura.setData(fattura.data());
        nuovaFattura.setImporto(fattura.importo());
        nuovaFattura.setNumeroFattura(fattura.numeroFattura());


        Cliente cliente = clienteRepository.findById(fattura.clienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente non trovato: " + fattura.clienteId()));

        nuovaFattura.setCliente(cliente);

        return fatturaRepository.save(nuovaFattura);
    }

    public StatoFattura saveStatoFattura(StatoFatturaDTO statoFatturaDTO) {
        StatoFattura nuovoStatoFattura = new StatoFattura();
        nuovoStatoFattura.setStato(statoFatturaDTO.stato());
        nuovoStatoFattura.setId(statoFatturaDTO.idFattura());

        return statoFatturaRepository.save(nuovoStatoFattura);

    }


    public List<Fattura> getFattureByClienteId(UUID clienteId) {
        return fatturaRepository.findByClienteId(clienteId);
    }


    public List<Fattura> getFattureByStato(StatoFatturaEnum stato) {
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


    public List<Fattura> getFattureFiltrate(UUID clienteId, StatoFatturaEnum stato, BigDecimal minImporto, BigDecimal maxImporto, LocalDate startDate, LocalDate endDate) {
        return fatturaRepository.findByClienteIdAndStatoAndImportoBetweenAndDataBetween(clienteId, stato, minImporto, maxImporto, startDate, endDate);
    }
}
