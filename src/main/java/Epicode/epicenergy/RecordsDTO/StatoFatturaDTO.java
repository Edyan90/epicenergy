package Epicode.epicenergy.RecordsDTO;

import Epicode.epicenergy.enums.StatoFatturaEnum;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record StatoFatturaDTO(

        @NotNull(message = "L'id della fattura è obbligatorio")
        UUID idFattura,

        @NotNull(message = "Lo stato della fattura è obbligatorio")
        StatoFatturaEnum stato
) {
}
