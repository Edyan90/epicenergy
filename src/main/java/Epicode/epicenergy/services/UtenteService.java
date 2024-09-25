package Epicode.epicenergy.services;

import Epicode.epicenergy.entites.Utente;
import Epicode.epicenergy.exceptions.BadRequestException;
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

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private PasswordEncoder bcrypt;

    public Utente saveUtente(NewUtenteDTO body) {

        this.utenteRepository.findByMail(body.mail()).ifPresent(

                user -> {
                    throw new BadRequestEx("La mail " + body.username() + " e' gia' in uso!");
                }
        );
        Utente newUtente = new Utente(body.username(), body.mail(), bcrypt.encode(body.password()), body.nome(), body.cognome());
        List<Ruolo> ruoli = Arrays.stream(body.ruoli().split(","))
                .map(String::trim)
                .map(Ruolo::valueOf)
                .collect(Collectors.toList());
        newUtente.setRuoli(ruoli);
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
        return this.utenteRepository.findById(id).orElseThrow(() -> new NotFoundException(String.valueOf(id)));
    }

    public Utente findByUsername(String username) {
        return utenteRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Utente non trovato con username: " + username));
    }

    public void findByIdAndDelete(UUID id) {
        Utente found = this.findById(id);
        this.utenteRepository.delete(found);
    }

    public void uploadAvatar(UUID id, MultipartFile image) throws IOException {
        Utente utente = utenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dipendente con id " + id + " non trovato."));

        Map<String, Object> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        String immagineCover = uploadResult.get("url").toString();
        System.out.println("URL: " + immagineCover);

        utente.setAvatar(immagineCover);
        utenteRepository.save(utente);
    }

}
