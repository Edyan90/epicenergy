package Epicode.epicenergy.entities;


import Epicode.epicenergy.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clienti")
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "ragione_sociale", nullable = false)
    private String ragioneSociale;

    @Column(name = "partita_iva", nullable = false)
    private String partitaIva;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "data_inserimento")
    @Temporal(TemporalType.DATE)
    private LocalDate dataInserimento;

    @Column(name = "data_ultimo_contatto")
    @Temporal(TemporalType.DATE)
    private LocalDate dataUltimoContatto;

    @Column(name = "fatturato_annuale")
    private Double fatturatoAnnuale;

    @Column(name = "pec")
    private String pec;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "email_contatto")
    private String emailContatto;

    @Column(name = "nome_contatto")
    private String nomeContatto;

    @Column(name = "cognome_contatto")
    private String cognomeContatto;

    @Column(name = "telefono_contatto")
    private String telefonoContatto;

    @Column(name = "logo_aziendale")
    private String logoAziendale;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente", nullable = false)
    private TipoCliente tipoCliente;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private List<Indirizzo> indirizzi;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Fattura> fatture;


}
