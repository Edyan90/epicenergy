package Epicode.epicenergy.RecordsDTO;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


public record FatturaDTO(

        @NotNull(message = "La data è obbligatoria")
        LocalDate data,

        @NotNull(message = "L'importo' è obbligatorio")
        BigDecimal importo,

        @NotNull(message = "Il numero di fattura è obbligatorio")
        Integer numeroFattura,

        @NotNull(message = "L'id del cliente è obbligatorio")
        UUID clienteId

) {
}
