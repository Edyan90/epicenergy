package Epicode.epicenergy.payloads;

<<<<<<< Updated upstream
=======
import Epicode.epicenergy.enums.Ruolo;
>>>>>>> Stashed changes
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewUtenteDTO(

        @NotNull(message = "Lo username è obbligatorio")
<<<<<<< Updated upstream
        @Size(min = 3, max = 20, message = "Username deve contenere minimo 3 caratteri e massimo 20")
=======
        @Size(min = 3, max = 10, message = "Username deve contenere minimo 3 caratteri e massimo 10")
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
        @NotNull(message = "Il ruolo non può essere nullo e deve essere separati da una virgola se ci sono più ruoli")
        String ruolo
) {
}
=======
        @NotNull(message = "Il ruolo non può essere nullo")
        Ruolo ruolo

) {
}
>>>>>>> Stashed changes
