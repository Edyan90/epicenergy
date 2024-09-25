package Epicode.epicenergy.controllers;


import Epicode.epicenergy.entities.Utente;
import Epicode.epicenergy.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    @GetMapping("/{id}")
    public Utente findById(@PathVariable UUID id) {
        return this.utenteService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID id) {
        this.utenteService.findByIdAndDelete(id);
    }

    @PostMapping("/{id}/immagineProfilo")
    public void uploadImmagineProfilo(@PathVariable UUID id, @RequestParam("immagineProfilo") MultipartFile image) throws IOException {
        utenteService.uploadAvatar(id, image);
    }

}
