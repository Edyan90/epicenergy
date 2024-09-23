package Epicode.epicenergy.RecordsDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record NewIndirizzoDTO(
        @NotEmpty(message = "La via è obbligatoria")
        String via,

        @NotEmpty(message = "Il civico è obbligatorio")
        String civico,

        @NotEmpty(message = "La località è obbligatoria")
        String località,

        @NotEmpty(message = "Il CAP è obbligatorio")
        @Pattern(regexp = "\\d{5}", message = "Il CAP deve essere composto da 5 cifre")
        String cap,

        @NotEmpty(message = "Il comune è obbligatorio")
        String comune
) {
}
