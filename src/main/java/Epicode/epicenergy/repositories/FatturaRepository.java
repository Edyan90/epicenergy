package Epicode.epicenergy.repositories;

import Epicode.epicenergy.entities.Fattura;
import Epicode.epicenergy.entities.StatoFattura;
import Epicode.epicenergy.enums.StatoFatturaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, UUID> {

//    // Filtro per cliente
//    List<Fattura> findByClienteId(UUID clienteId);
//
//    // Filtro per stato
//    List<Fattura> findByStato(StatoFatturaEnum stato);
//
//    // Filtro per data tra due date
//    List<Fattura> findByDataBetween(LocalDate startDate, LocalDate endDate);
//
//    // Filtro per anno specifico (usando una funzione JPA per estrarre l'anno)
////    @Query("SELECT f FROM Fattura f WHERE YEAR(f.data) = ?1")
////    List<Fattura> findByAnno(Integer anno);
//
//    // Filtro per importo tra min e max
//    List<Fattura> findByImportoBetween(BigDecimal minImporto, BigDecimal maxImporto);
//
//    // Filtro combinato per cliente, stato, range di importi e date
//    List<Fattura> findByClienteIdAndStatoAndImportoBetweenAndDataBetween(UUID clienteId, StatoFatturaEnum stato, BigDecimal minImporto, BigDecimal maxImporto, LocalDate startDate, LocalDate endDate);

    @Query("SELECT f FROM Fattura f WHERE f.cliente.id = :clienteId")
    List<Fattura> filtraFatturePerCliente(@Param("clienteId") UUID clienteId);

    @Query("SELECT f FROM StatoFattura f WHERE f.stato = :stato")
    List<StatoFattura> filtraFatturePerStato(@Param("stato") StatoFatturaEnum stato);

    @Query("SELECT f FROM Fattura f WHERE f.data BETWEEN :primaData AND :secondaData")
    List<Fattura> filtroDataFattura(@Param("primaData") LocalDate primaData, @Param("secondaData") LocalDate secondaData);

    // Filtro per importo tra min e max
    List<Fattura> findByImportoBetween(BigDecimal minImporto, BigDecimal maxImporto);

    // Filtro combinato per cliente, stato, range di importi e date
    List<Fattura> findByClienteIdAndStatoAndImportoBetweenAndDataBetween(UUID clienteId, StatoFattura stato, BigDecimal minImporto, BigDecimal maxImporto, LocalDate startDate, LocalDate endDate);

    @Query("SELECT f FROM Fattura f WHERE YEAR(f.data) = :anno")
    List<Fattura> findByAnno(@Param("anno") int anno);
    
}
