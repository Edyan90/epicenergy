package Epicode.epicenergy.controllers;

import Epicode.epicenergy.exceptions.BadRequestException;
import Epicode.epicenergy.payloads.NewUtenteDTO;
import Epicode.epicenergy.payloads.NewUtenteRespDTO;
import Epicode.epicenergy.payloads.UtenteLoginDTO;
import Epicode.epicenergy.payloads.UtenteLoginRespDTO;
import Epicode.epicenergy.services.AuthService;
import Epicode.epicenergy.services.UtenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public UtenteLoginRespDTO login(@RequestBody UtenteLoginDTO payload) {
        return new UtenteLoginRespDTO(this.authService.checkCredentialsAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO save(@RequestBody @Valid NewUtenteDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {
            String messages = validationResult.getAllErrors().stream()
                    .map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload. " + messages);
        } else {
            return new NewUtenteRespDTO(this.utenteService.saveUtente(body).getId());
        }
    }

}
