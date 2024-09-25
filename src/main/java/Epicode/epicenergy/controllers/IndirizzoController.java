package Epicode.epicenergy.controllers;


import Epicode.epicenergy.RecordsDTO.NewIndirizzoDTO;
import Epicode.epicenergy.RecordsDTO.NewIndirizzoRespDTO;
import Epicode.epicenergy.services.IndirizzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/indirizzi")
public class IndirizzoController {

    @Autowired
    private IndirizzoService indirizzoService;

    @PostMapping
    public ResponseEntity<NewIndirizzoRespDTO> createIndirizzo(@RequestBody NewIndirizzoDTO dto) {
        NewIndirizzoRespDTO response = indirizzoService.createIndirizzo(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<NewIndirizzoRespDTO>> getAllIndirizzi() {
        return ResponseEntity.ok(indirizzoService.getAllIndirizzi());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewIndirizzoRespDTO> getIndirizzoById(@PathVariable UUID id) {
        return ResponseEntity.ok(indirizzoService.getIndirizzoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewIndirizzoRespDTO> updateIndirizzo(@PathVariable UUID id, @RequestBody NewIndirizzoDTO dto) {
        return ResponseEntity.ok(indirizzoService.updateIndirizzo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIndirizzo(@PathVariable UUID id) {
        indirizzoService.deleteIndirizzo(id);
        return ResponseEntity.noContent().build();
    }
}
