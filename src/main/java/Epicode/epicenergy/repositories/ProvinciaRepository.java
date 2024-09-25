package Epicode.epicenergy.repositories;

import Epicode.epicenergy.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, UUID> {

    Optional<Provincia> findByProvincia(String provincia);


    Optional<Provincia> findBySigla(String sigla);
}
