package Epicode.epicenergy.payloads;

import Epicode.epicenergy.enums.Ruolo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUtenteDTO(

        @NotNull(message = "Lo username è obbligatorio")
        @Size(min = 3, max = 10, message = "Username deve contenere minimo 3 caratteri e massimo 10")
        String username,

        @NotNull(message = "La mail non può essere nulla")
        @Email(message = "Inserisci un indirizzo email valido")
        String mail,

        @NotNull(message = "La password è obbligatoria")
        @Size(min = 3, max = 25, message = "La password deve contenere minimo 3 caratteri e massimo 25")
        String password,

        @NotNull(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 20, message = "Il nome deve contenere minimo 3 caratteri e massimo 20")
        String nome,

        @NotNull(message = "Il cognome è obbligatorio")
        @Size(min = 3, max = 20, message = "Il cognome deve contenere minimo 3 caratteri e massimo 20")
        String cognome,

        @NotNull(message = "Il ruolo non può essere nullo")
        Ruolo ruolo

) {
}
