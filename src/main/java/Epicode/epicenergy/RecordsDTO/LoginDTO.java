package Epicode.epicenergy.RecordsDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record LoginDTO(@NotEmpty(message = "manca l'username")
                       @Size(min = 10, max = 20, message = "l'username devono contenere un minimo di 10 ad un massimo di 20 caratteri")
                       String Username,
                       @NotEmpty(message = "manca la password")
                       @Size(min = 3, max = 20, message = "la password deve contenere un minimo di 10 ad un massimo di 20 caratteri alfanumerici")
                       String password) {
}