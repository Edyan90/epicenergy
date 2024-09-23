package Epicode.epicenergy.controllers;

import Epicode.epicenergy.RecordsDTO.FatturaDTO;
import Epicode.epicenergy.entities.Fattura;
import Epicode.epicenergy.enums.StatoFattura;
import Epicode.epicenergy.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;


    private FatturaDTO convertToDTO(Fattura fattura) {
        return new FatturaDTO(
                fattura.getId(),
                fattura.getData(),
                fattura.getImporto(),
                fattura.getNumeroFattura(),
                fattura.getStato(),
                // Il cliente ID può essere null se la relazione non è stata impostata
                fattura.getCliente() != null ? fattura.getCliente().getId() : null
        );
    }


    private Fattura convertToEntity(FatturaDTO fatturaDTO) {
        Fattura fattura = new Fattura();
        fattura.setData(fatturaDTO.getData());
        fattura.setImporto(fatturaDTO.getImporto());
        fattura.setNumeroFattura(fatturaDTO.getNumeroFattura());
        fattura.setStato(fatturaDTO.getStato());
        return fattura;
    }


    @PostMapping
    public ResponseEntity<FatturaDTO> creaFattura(@RequestBody FatturaDTO fatturaDTO) {
        Fattura nuovaFattura = convertToEntity(fatturaDTO);
        Fattura salvataFattura = fatturaService.saveFattura(nuovaFattura);
        return ResponseEntity.ok(convertToDTO(salvataFattura));
    }


    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<FatturaDTO>> getFattureByCliente(@PathVariable UUID clienteId) {
        List<Fattura> fatture = fatturaService.getFattureByClienteId(clienteId);
        List<FatturaDTO> fattureDTO = fatture.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(fattureDTO);
    }


    @GetMapping("/stato/{stato}")
    public ResponseEntity<List<FatturaDTO>> getFattureByStato(@PathVariable StatoFattura stato) {
        List<Fattura> fatture = fatturaService.getFattureByStato(stato);
        List<FatturaDTO> fattureDTO = fatture.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(fattureDTO);
    }

    @GetMapping("/data")
    public ResponseEntity<List<FatturaDTO>> getFattureByDateRange(
            @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<Fattura> fatture = fatturaService.getFattureByDateRange(startDate, endDate);
        List<FatturaDTO> fattureDTO = fatture.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(fattureDTO);
    }


    @GetMapping("/importo")
    public ResponseEntity<List<FatturaDTO>> getFattureByImportoRange(
            @RequestParam BigDecimal minImporto, @RequestParam BigDecimal maxImporto) {
        List<Fattura> fatture = fatturaService.getFattureByImportoRange(minImporto, maxImporto);
        List<FatturaDTO> fattureDTO = fatture.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(fattureDTO);
    }


    @GetMapping("/filtrate")
    public ResponseEntity<List<FatturaDTO>> getFattureFiltrate(
            @RequestParam UUID clienteId, @RequestParam StatoFattura stato,
            @RequestParam BigDecimal minImporto, @RequestParam BigDecimal maxImporto,
            @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<Fattura> fatture = fatturaService.getFattureFiltrate(clienteId, stato, minImporto, maxImporto, startDate, endDate);
        List<FatturaDTO> fattureDTO = fatture.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(fattureDTO);
    }
}
