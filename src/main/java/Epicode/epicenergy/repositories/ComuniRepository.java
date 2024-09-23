package Epicode.epicenergy.repositories;

import Epicode.epicenergy.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComuniRepository extends JpaRepository<Comune, UUID> {
}
