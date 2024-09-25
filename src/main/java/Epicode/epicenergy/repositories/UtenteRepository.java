package Epicode.epicenergy.repositories;


import Epicode.epicenergy.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UtenteRepository extends JpaRepository<Utente, UUID> {

    Optional<Utente> findByMail(String mail);

    Optional<Utente> findByUsername(String username);

    boolean existsByMail(String mail);

}
