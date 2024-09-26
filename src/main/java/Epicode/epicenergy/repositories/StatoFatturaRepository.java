package Epicode.epicenergy.repositories;

import Epicode.epicenergy.entities.StatoFattura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StatoFatturaRepository extends JpaRepository<StatoFattura, UUID> {

    Optional<StatoFattura> findStatoFatturaById(UUID id);


}
