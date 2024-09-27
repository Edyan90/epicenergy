package Epicode.epicenergy.services;

<<<<<<< Updated upstream

import Epicode.epicenergy.entities.Utente;
import Epicode.epicenergy.exceptions.UnauthorizedEx;
=======
import Epicode.epicenergy.entites.Utente;
import Epicode.epicenergy.exceptions.UnauthorizedException;
>>>>>>> Stashed changes
import Epicode.epicenergy.payloads.UtenteLoginDTO;
import Epicode.epicenergy.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(UtenteLoginDTO body) {

        Utente found = this.utenteService.findByUsername(body.username());
        if (bcrypt.matches(body.password(), found.getPassword())) {

            return jwtTools.createToken(found);
        } else {

<<<<<<< Updated upstream
            throw new UnauthorizedEx("Credenziali errate!");
=======
            throw new UnauthorizedException("Credenziali errate!");
>>>>>>> Stashed changes
        }

    }
}
