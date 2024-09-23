package Epicode.epicenergy.repositories;

import Epicode.epicenergy.entites.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

    Optional<Utente> findByMail(String mail);

    boolean existsByMail(String mail);

}
