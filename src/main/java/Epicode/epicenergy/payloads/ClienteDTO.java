package Epicode.epicenergy.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ClienteDTO(
        @NotEmpty(message = "La ragione sociale è obbligatoria")
        @Size(min = 2, max = 50, message = "La ragione sociale deve essere compresa tra 2 e 50 caratteri")
        String ragioneSociale,

        @NotEmpty(message = "La partita IVA è obbligatoria")
        String partitaIva,

        @NotEmpty(message = "Email obbligatoria")
        @Email(message = "Email inserita non è valida")
        String email,

        String telefono,
        String pec,
        Double fatturatoAnnuale,
        LocalDate dataInserimento,
        LocalDate dataUltimoContatto,

        String nomeContatto,
        String cognomeContatto,
        String telefonoContatto,
        String provinciaSedeLegale
) {
}

