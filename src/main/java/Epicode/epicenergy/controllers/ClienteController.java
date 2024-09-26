package Epicode.epicenergy.controllers;

import Epicode.epicenergy.entities.Cliente;
import Epicode.epicenergy.payloads.ClienteDTO;
import Epicode.epicenergy.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/clienti")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


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

