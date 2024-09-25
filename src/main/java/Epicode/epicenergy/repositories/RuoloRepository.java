package Epicode.epicenergy.repositories;

import Epicode.epicenergy.entities.Ruolo;
import Epicode.epicenergy.enums.RuoloEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RuoloRepository extends JpaRepository<Ruolo, UUID> {

    Optional<Ruolo> findByRuoloE(RuoloEnum ruoloE);
    
}
