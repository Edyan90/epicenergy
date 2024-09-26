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
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<Cliente>> getAllClients(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) Double fatturatoMin,
            @RequestParam(required = false) Double fatturatoMax,
            @RequestParam(required = false) LocalDate dataInserimentoMin,
            @RequestParam(required = false) LocalDate dataInserimentoMax,
            @RequestParam(required = false) LocalDate dataUltimoContattoMin,
            @RequestParam(required = false) LocalDate dataUltimoContattoMax,
            @RequestParam(required = false) String provinciaSedeLegale,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            Pageable pageable) {


        Page<Cliente> clienti = clienteService.getAllClients(nome, fatturatoMin, fatturatoMax,
                dataInserimentoMin, dataInserimentoMax,
                dataUltimoContattoMin, dataUltimoContattoMax,
                provinciaSedeLegale, sortBy, direction, pageable);
        return ResponseEntity.ok(clienti);
    }

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

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateClient(@PathVariable UUID id, @RequestBody ClienteDTO clienteDTO) {
        Cliente clienteAggiornato = clienteService.updateClient(id, clienteDTO);
        return ResponseEntity.ok(clienteAggiornato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clienteService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/logo")
    public ResponseEntity<Cliente> uploadLogoAziendale(@PathVariable UUID id,
                                                       @RequestParam("file") MultipartFile file) throws IOException {
        Cliente cliente = clienteService.uploadLogoAziendale(id, file);
        return ResponseEntity.ok(cliente);
    }
}

