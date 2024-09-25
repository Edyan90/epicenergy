package Epicode.epicenergy.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Entity
@Table(name = "utenti")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties
public class Utente implements UserDetails {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String username;
    private String mail;
    private String password;
    private String nome;
    private String cognome;
    private String avatar;

    @ManyToMany
    @JoinTable(name = "utenti_ruoli",
            joinColumns = @JoinColumn(name = "utente_id"),
            inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
    private Set<Ruolo> ruoli = new HashSet<>();

    public Utente(String username, String mail, String password, String nome, String cognome) {
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ruoli.stream()
                .map(ruolo -> new SimpleGrantedAuthority(ruolo.getRuoloE().name()))
                .collect(Collectors.toList());
    }

}
