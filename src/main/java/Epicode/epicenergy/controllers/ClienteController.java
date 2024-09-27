package Epicode.epicenergy.controllers;

import Epicode.epicenergy.entities.Cliente;
import Epicode.epicenergy.payloads.ClienteDTO;
import Epicode.epicenergy.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

//    @GetMapping
//    public Page<Cliente> trovaTuttiClienti(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "id") String sortBy,
//            @RequestParam(required = false) Double fatturatoMin,
//            @RequestParam(required = false) Double fatturatoMax,
//            @RequestParam(required = false) LocalDate dataInserimento,
//            @RequestParam(required = false) LocalDate dataUltimoContatto,
//            @RequestParam(required = false) String parteNome) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        return clienteService.findWithFilters(fatturatoMin, fatturatoMax, dataInserimento, dataUltimoContatto, parteNome, pageable);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClientById(@PathVariable UUID id) {
        Cliente cliente = clienteService.getClientById(id);
        return ResponseEntity.ok(cliente);
    }


    @PostMapping
    public ResponseEntity<Cliente> createClient(@RequestBody ClienteDTO newClienteDTO) {
        Cliente cliente = clienteService.createClient(newClienteDTO);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @GetMapping("/{clienteId}")
    public Cliente trovaClientePerId(@PathVariable UUID clienteId) {
        return clienteService.trovaPerId(clienteId);
    }

    @PutMapping("/{clienteId}")
    public Cliente aggiornaCliente(@PathVariable UUID clienteId, @Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente clienteEsistente = clienteService.trovaPerId(clienteId);
        clienteService.aggiornaClienteDaDTO(clienteEsistente, clienteDTO);
        return clienteService.salva(clienteEsistente);
    }

    @DeleteMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancellaCliente(@PathVariable UUID clienteId) {
        clienteService.cancella(clienteId);
    }


    @PostMapping("/{clienteId}/logo")
    @ResponseStatus(HttpStatus.OK)
    public Cliente caricaLogoAziendale(@PathVariable UUID clienteId, @RequestParam("logo") MultipartFile file) throws IOException, IOException {
        return clienteService.uploadLogoAziendale(clienteId, file);
    }

    //
    @GetMapping("/ordina/nome")
    public List<Cliente> ordinaPerNome() {
        return clienteService.ordinaPerNome();
    }

    @GetMapping("/ordina/fatturato")
    public List<Cliente> ordinaPerFatturato() {
        return clienteService.ordinaPerFatturato();
    }

    @GetMapping("/ordina/dataInserimento")
    public List<Cliente> ordinaPerDataInserimento() {
        return clienteService.ordinaPerDataInserimento();
    }

    @GetMapping("/ordina/dataUltimoContatto")
    public List<Cliente> ordinaPerDataUltimoContatto() {
        return clienteService.ordinaPerDataUltimoContatto();
    }

    //filtro
    @GetMapping("/filtraFatturato")
    // clienti/filtraFatturato?fatturatoMinimo= {$tot minimo fatturato da filtrare} &fatturatoMassimo= {$tot massimo fatturato da filtrare}
    public List<Cliente> filtroFatturatoAnnuale(@RequestParam Double fatturatoMinimo, @RequestParam Double fatturatoMassimo) {
        return clienteService.filtroFatturatoAnnuale(fatturatoMinimo, fatturatoMassimo);
    }

    @GetMapping("/filtraDataInserimento")
    // clienti/filtraDataInserimento?primaData= {prima data da filtrare} &secondaData= {seconda data da filtrare}
    public List<Cliente> filtroDataInserimento(@RequestParam LocalDate primaData, @RequestParam LocalDate secondaData) {
        return clienteService.filtroDataInserimento(primaData, secondaData);
    }


    @GetMapping("/filtraUltimoContatto")
    // clienti/filtraUltimoContatto?primaData= {prima data da filtrare} &secondaData= {seconda data da filtrare}
    public List<Cliente> filtroDataUltimoContatto(@RequestParam LocalDate primaData, @RequestParam LocalDate secondaData) {
        return clienteService.filtroDataUltimoContatto(primaData, secondaData);
    }


    @GetMapping("/filtraNome")
    // clienti/filtraNome?parteNome=parteDelNome
    public List<Cliente> filtroNome(@RequestParam String parteDelNome) {
        return clienteService.filtroNome(parteDelNome);
    }


}

