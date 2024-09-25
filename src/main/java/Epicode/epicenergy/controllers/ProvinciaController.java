package Epicode.epicenergy.controllers;


import Epicode.epicenergy.RecordsDTO.NewProvinciaDTO;
import Epicode.epicenergy.RecordsDTO.NewProvinciaRespDTO;
import Epicode.epicenergy.services.ProvinciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/province")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;

    @PostMapping
    public ResponseEntity<NewProvinciaRespDTO> createProvincia(@RequestBody NewProvinciaDTO dto) {
        NewProvinciaRespDTO response = provinciaService.createProvincia(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<NewProvinciaRespDTO>> getAllProvince() {
        return ResponseEntity.ok(provinciaService.getAllProvince());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewProvinciaRespDTO> getProvinciaById(@PathVariable UUID id) {
        return ResponseEntity.ok(provinciaService.getProvinciaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewProvinciaRespDTO> updateProvincia(@PathVariable UUID id, @RequestBody NewProvinciaDTO dto) {
        return ResponseEntity.ok(provinciaService.updateProvincia(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvincia(@PathVariable UUID id) {
        provinciaService.deleteProvincia(id);
        return ResponseEntity.noContent().build();
    }
}
