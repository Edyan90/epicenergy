package Epicode.epicenergy.repositories;

import Epicode.epicenergy.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

//    @Query("SELECT c FROM Cliente c WHERE " +
//            "(:fatturatoMin IS NULL OR c.fatturatoAnnuale >= :fatturatoMin) AND " +
//            "(:fatturatoMax IS NULL OR c.fatturatoAnnuale <= :fatturatoMax) AND " +
//            "(:dataInserimento IS NULL OR c.dataInserimento = :dataInserimento) AND " +
//            "(:dataUltimoContatto IS NULL OR c.dataUltimoContatto = :dataUltimoContatto) AND " +
//            "(:parteNome IS NULL OR LOWER(c.ragioneSociale) LIKE LOWER(CONCAT('%', :parteNome, '%')))")
//    Page<Cliente> findWithFilters(Double fatturatoMin, Double fatturatoMax, LocalDate dataInserimento, LocalDate dataUltimoContatto, String parteNome, Pageable pageable);

    Optional<Cliente> findById(UUID id);

    // odina per nome
    @Query("SELECT c FROM Cliente c ORDER BY c.nomeContatto ASC")
    List<Cliente> ordinaPerNome();

    // ordina per fatturato
    @Query("SELECT c FROM Cliente c ORDER BY c.fatturatoAnnuale ASC")
    List<Cliente> ordinaPerFatturato();

    // ordina per data di inserimento
    @Query("SELECT c FROM Cliente c ORDER BY c.dataInserimento ASC")
    List<Cliente> ordinaPerDataInserimento();

    // ordina per data di ultimo contatto
    @Query("SELECT c FROM Cliente c ORDER BY c.dataUltimoContatto ASC")
    List<Cliente> ordinaPerDataUltimoContatto();

    // ordina per provincia ?

    //filtri
    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale BETWEEN :fatturatoMinimo AND :fatturatoMassimo")
    List<Cliente> filtroFatturatoAnnuale(@Param("fatturatoMinimo") Double fatturatoMinimo, @Param("fatturatoMassimo") Double fatturatoMassimo);

    @Query("SELECT c FROM Cliente c WHERE c.dataInserimento BETWEEN :primaData AND :secondaData")
    List<Cliente> filtroDataInserimento(@Param("primaData") LocalDate primaData, @Param("secondaData") LocalDate secondaData);

    @Query("SELECT c FROM Cliente c WHERE c.dataUltimoContatto BETWEEN :primaData AND :secondaData")
    List<Cliente> filtroDataUltimoContatto(@Param("primaData") LocalDate primaData, @Param("secondaData") LocalDate secondaData);

    @Query("SELECT c FROM Cliente c WHERE c.nomeContatto LIKE %:parteDelNome%")
    List<Cliente> filtroNome(@Param("parteDelNome") String parteDelNome);

//    @Query("SELECT c FROM Cliente c WHERE c.fatturatoAnnuale = :fatturatoAnnuale")
//    List<Cliente> filtroDataInserimento(@Param("dataInserimento") Double fatturatoAnnuale);


}


