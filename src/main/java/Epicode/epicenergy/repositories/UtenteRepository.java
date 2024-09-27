package Epicode.epicenergy.repositories;

<<<<<<< Updated upstream

import Epicode.epicenergy.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UtenteRepository extends JpaRepository<Utente, UUID> {

    Optional<Utente> findByMail(String mail);

    Optional<Utente> findByUsername(String username);

    boolean existsByMail(String mail);

=======
import Epicode.epicenergy.entites.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    boolean existsByMail(String mail);

    Optional<Utente> findByUsername(String username);

>>>>>>> Stashed changes
}
