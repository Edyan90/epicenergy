package Epicode.epicenergy.services;

import Epicode.epicenergy.entities.Utente;
import Epicode.epicenergy.exceptions.BadRequestEx;
import Epicode.epicenergy.exceptions.NotFoundEx;
import Epicode.epicenergy.exceptions.NotFoundException;
import Epicode.epicenergy.payloads.NewUtenteDTO;
import Epicode.epicenergy.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public Utente saveUtente(NewUtenteDTO body) {

        this.utenteRepository.findByMail(body.mail()).ifPresent(

                user -> {
                    throw new BadRequestEx("La mail " + body.username() + " e' gia' in uso!");
                }
        );
        Utente newUtente = new Utente(body.username(), body.mail(), bcrypt.encode(body.password()), body.nome(), body.cognome(), Collections.singletonList(body.ruolo()));
        Utente savedUser = this.utenteRepository.save(newUtente);


        System.out.println("L'utente " + savedUser + " e' stato salvato con successo!");

        return savedUser;

    }

    public Page<Utente> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.utenteRepository.findAll(pageable);
    }

    public Utente findById(UUID id) {
        return this.utenteRepository.findById(id).orElseThrow(() -> new NotFoundEx(id));
    }

    public Utente findByMail(String mail) {
        return utenteRepository.findByMail(mail)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con mail: " + mail));
    }

    public void findByIdAndDelete(int id) {
        Utente found = this.findById(id);
        this.utenteRepository.delete(found);
    }

}
