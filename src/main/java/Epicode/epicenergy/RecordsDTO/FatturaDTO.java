package Epicode.epicenergy.RecordsDTO;

import Epicode.epicenergy.enums.StatoFattura;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FatturaDTO {

    private UUID id;
    private LocalDate data;
    private BigDecimal importo;
    private Integer numeroFattura;
    private StatoFattura stato;
    private UUID clienteId;
}
