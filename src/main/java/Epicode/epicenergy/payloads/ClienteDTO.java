package Epicode.epicenergy.payloads;

import Epicode.epicenergy.RecordsDTO.NewIndirizzoDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ClienteDTO(
        @NotEmpty(message = "La ragione sociale è obbligatoria")
        String ragioneSociale,

        @NotEmpty(message = "La partita IVA è obbligatoria")
        String partitaIva,

        @Email(message = "Email non valida")
        String email,

        List<NewIndirizzoDTO> indirizzi
) {
}
