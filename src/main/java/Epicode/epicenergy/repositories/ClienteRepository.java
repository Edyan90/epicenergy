package Epicode.epicenergy.repositories;

import Epicode.epicenergy.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE " +
            "(:fatturatoMin IS NULL OR c.fatturatoAnnuale >= :fatturatoMin) AND " +
            "(:fatturatoMax IS NULL OR c.fatturatoAnnuale <= :fatturatoMax) AND " +
            "(:dataInserimento IS NULL OR c.dataInserimento = :dataInserimento) AND " +
            "(:dataUltimoContatto IS NULL OR c.dataUltimoContatto = :dataUltimoContatto) AND " +
            "(:parteNome IS NULL OR LOWER(c.ragioneSociale) LIKE LOWER(CONCAT('%', :parteNome, '%')))")
    Page<Cliente> findWithFilters(Double fatturatoMin, Double fatturatoMax, LocalDate dataInserimento, LocalDate dataUltimoContatto, String parteNome, Pageable pageable);
}


