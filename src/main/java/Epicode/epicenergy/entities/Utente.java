package Epicode.epicenergy.entities;

import Epicode.epicenergy.enums.Ruolo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name = "utenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String username;
    private String mail;
    private String password;
    private String nome;
    private String cognome;
    private String avatar;
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo;

    public Utente(String username, String mail, String password, String nome, String cognome, Ruolo ruolo) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
    }

}
