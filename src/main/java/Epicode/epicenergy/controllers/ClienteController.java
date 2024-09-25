package Epicode.epicenergy.controllers;

import Epicode.epicenergy.entities.Cliente;
import Epicode.epicenergy.payloads.ClienteDTO;
import Epicode.epicenergy.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Page<Cliente> trovaTuttiClienti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(required = false) Double fatturatoMin,
            @RequestParam(required = false) Double fatturatoMax,
            @RequestParam(required = false) LocalDate dataInserimento,
            @RequestParam(required = false) LocalDate dataUltimoContatto,
            @RequestParam(required = false) String parteNome) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return clienteService.findWithFilters(fatturatoMin, fatturatoMax, dataInserimento, dataUltimoContatto, parteNome, pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente creaCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.mappaDTOaCliente(clienteDTO);
        return clienteService.salva(cliente);
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
}

