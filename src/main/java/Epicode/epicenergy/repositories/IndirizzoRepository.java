package Epicode.epicenergy.repositories;

import Epicode.epicenergy.entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, UUID> {
}
