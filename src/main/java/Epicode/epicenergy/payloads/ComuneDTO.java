package Epicode.epicenergy.payloads;

import jakarta.validation.constraints.NotEmpty;

public record ComuneDTO(
        @NotEmpty(message = "La denominazione è obbligatoria")
        String denominazione,

        String codiceProvincia,
        Integer progressivoComune,
        String provincia
) {
}
