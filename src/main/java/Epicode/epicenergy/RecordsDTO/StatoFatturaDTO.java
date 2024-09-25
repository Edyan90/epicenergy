package Epicode.epicenergy.RecordsDTO;

import Epicode.epicenergy.enums.StatoFatturaEnum;
import jakarta.validation.constraints.NotNull;

public record StatoFatturaDTO(

        @NotNull(message = "Il numero della fattura è obbligatorio")
        Integer numeroFattura,

        @NotNull(message = "Lo stato della fattura è obbligatorio")
        StatoFatturaEnum stato
) {
}
