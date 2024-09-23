package Epicode.epicenergy.entites;

import Epicode.epicenergy.enums.Ruolo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "utenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties
public class Utente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String username;
    private String mail;
    private String password;
    private String nome;
    private String cognome;
    private String avatar;

    @Enumerated(EnumType.STRING)
    private List<Ruolo> ruoli;

    public Utente(String username, String mail, String password, String nome, String cognome, List<Ruolo> ruoli) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.ruoli = ruoli;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
