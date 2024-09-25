package Epicode.epicenergy.controllers;

import Epicode.epicenergy.RecordsDTO.FatturaDTO;
import Epicode.epicenergy.entities.Fattura;
import Epicode.epicenergy.enums.StatoFatturaEnum;
import Epicode.epicenergy.services.ClienteService;
import Epicode.epicenergy.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fatture")
public class FatturaController {

    @Autowired
    private FatturaService fatturaService;

    @Autowired
    private ClienteService clienteService;

    // converte fatturaDTO in Fattura
    private Fattura convertToEntity(FatturaDTO fatturaDTO) {
        Fattura fattura = new Fattura();
        fattura.setData(fatturaDTO.data());
        fattura.setImporto(fatturaDTO.importo());
        fattura.setNumeroFattura(fatturaDTO.numeroFattura());

        return fattura;
    }

    private FatturaDTO convertToDTO(Fattura fattura) {
        return new FatturaDTO(
                fattura.getData(),
                fattura.getImporto(),
                fattura.getNumeroFattura(),
                fattura.getCliente().getId()
        );
    }


    @PostMapping
    public Fattura creaFattura(@RequestBody @Validated FatturaDTO fatturaDTO) {
//        Cliente cliente = this.clienteService.trovaPerId(fatturaDTO.clienteId());
//        Fattura nuovaFattura = new Fattura(fatturaDTO.data(), fatturaDTO.importo(), fatturaDTO.numeroFattura(), cliente);
        Fattura salvataFattura = fatturaService.saveFattura(fatturaDTO);


        return salvataFattura;
    }


    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<FatturaDTO>> getFattureByCliente(@PathVariable UUID clienteId) {
        List<Fattura> fatture = fatturaService.getFattureByClienteId(clienteId);
        List<FatturaDTO> fattureDTO = fatture.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(fattureDTO);
    }


    @GetMapping("/stato/{stato}")
    public ResponseEntity<List<FatturaDTO>> getFattureByStato(@PathVariable StatoFatturaEnum stato) {
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
            @RequestParam UUID clienteId, @RequestParam StatoFatturaEnum stato,
            @RequestParam BigDecimal minImporto, @RequestParam BigDecimal maxImporto,
            @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<Fattura> fatture = fatturaService.getFattureFiltrate(clienteId, stato, minImporto, maxImporto, startDate, endDate);
        List<FatturaDTO> fattureDTO = fatture.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(fattureDTO);
    }
}
