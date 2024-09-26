package Epicode.epicenergy.controllers;

import Epicode.epicenergy.RecordsDTO.FatturaDTO;
import Epicode.epicenergy.RecordsDTO.StatoFatturaDTO;
import Epicode.epicenergy.entities.Fattura;
import Epicode.epicenergy.entities.StatoFattura;
import Epicode.epicenergy.enums.StatoFatturaEnum;
import Epicode.epicenergy.services.ClienteService;
import Epicode.epicenergy.services.FatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

        Fattura salvataFattura = fatturaService.saveFattura(fatturaDTO);


        return salvataFattura;
    }

    @PostMapping
    @RequestMapping("/stato")
    public StatoFattura creaStatoFattura(@RequestBody @Validated StatoFatturaDTO statoFatturaDTO) {

        StatoFattura statoFatturaSalvato = fatturaService.saveStatoFattura(statoFatturaDTO);

        return statoFatturaSalvato;
    }

    @PutMapping("/{id}")
    public StatoFattura aggiornaStatoFattura(@PathVariable UUID id, @RequestBody @Validated StatoFatturaDTO statoFatturaDTO) {
        return fatturaService.findStatoFatturaByIdAndUpdate(id, statoFatturaDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminaStatoFattura(@PathVariable UUID id) {
        this.fatturaService.findStatoFatturaByIdAndDelete(id);
    }


//    @GetMapping("/cliente/{clienteId}")
//    public ResponseEntity<List<FatturaDTO>> getFattureByCliente(@PathVariable UUID clienteId) {
//        List<Fattura> fatture = fatturaService.getFattureByClienteId(clienteId);
//        List<FatturaDTO> fattureDTO = fatture.stream().map(this::convertToDTO).collect(Collectors.toList());
//        return ResponseEntity.ok(fattureDTO);
//    }
//
//
//    @GetMapping("/stato/{stato}")
//    public ResponseEntity<List<FatturaDTO>> getFattureByStato(@PathVariable StatoFatturaEnum stato) {
//        List<Fattura> fatture = fatturaService.getFattureByStato(stato);
//        List<FatturaDTO> fattureDTO = fatture.stream().map(this::convertToDTO).collect(Collectors.toList());
//        return ResponseEntity.ok(fattureDTO);
//    }
//
//    @GetMapping("/data")
//    public ResponseEntity<List<FatturaDTO>> getFattureByDateRange(
//            @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
//        List<Fattura> fatture = fatturaService.getFattureByDateRange(startDate, endDate);
//        List<FatturaDTO> fattureDTO = fatture.stream().map(this::convertToDTO).collect(Collectors.toList());
//        return ResponseEntity.ok(fattureDTO);
//    }
//
//
//    @GetMapping("/importo")
//    public ResponseEntity<List<FatturaDTO>> getFattureByImportoRange(
//            @RequestParam BigDecimal minImporto, @RequestParam BigDecimal maxImporto) {
//        List<Fattura> fatture = fatturaService.getFattureByImportoRange(minImporto, maxImporto);
//        List<FatturaDTO> fattureDTO = fatture.stream().map(this::convertToDTO).collect(Collectors.toList());
//        return ResponseEntity.ok(fattureDTO);
//    }
//
//
//    @GetMapping("/filtrate")
//    public ResponseEntity<List<FatturaDTO>> getFattureFiltrate(
//            @RequestParam UUID clienteId, @RequestParam StatoFatturaEnum stato,
//            @RequestParam BigDecimal minImporto, @RequestParam BigDecimal maxImporto,
//            @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
//        List<Fattura> fatture = fatturaService.getFattureFiltrate(clienteId, stato, minImporto, maxImporto, startDate, endDate);
//        List<FatturaDTO> fattureDTO = fatture.stream().map(this::convertToDTO).collect(Collectors.toList());
//        return ResponseEntity.ok(fattureDTO);
//    }

    @GetMapping("/filtraFatturePerCliente")
    //filtraFatturePerCliente?clienteId={id}
    public List<Fattura> filtraFatturePerCliente(@RequestParam UUID clienteId) {
        return fatturaService.filtraFatturePerCliente(clienteId);
    }

    @GetMapping("/filtraFatturePerStato")
    //filtraFatturePerStato?stato={stato}"
    public List<StatoFattura> filtraFatturePerStato(@RequestParam StatoFatturaEnum stato) {
        return fatturaService.filtraFatturePerStato(stato);
    }

    @GetMapping("/filtraDataFattura")
    // fatture/filtraDataFattura?primaData= {prima data da filtrare} &secondaData= {seconda data da filtrare}
    public List<Fattura> filtraDataFattura(@RequestParam LocalDate primaData, @RequestParam LocalDate secondaData) {
        return fatturaService.filtroDataFattura(primaData, secondaData);
    }


}
