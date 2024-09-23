package Epicode.epicenergy.RecordsDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewProvinciaDTO(
        @NotEmpty(message = "La sigla è obbligatoria")
        @Size(min = 2, max = 2, message = "La sigla deve contenere massimo 2 caratteri!")
        String sigla,

        @NotEmpty(message = "Il nome della provincia è obbligatorio")
        String provincia,

        @NotEmpty(message = "La regione è obbligatoria")
        String regione
) {
}
