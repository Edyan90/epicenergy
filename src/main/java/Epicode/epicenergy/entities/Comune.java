package Epicode.epicenergy.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "comune")
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class Comune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "codice_provincia", nullable = false)
    private String codiceProvincia;

    @Column(name = "progressivo_comune", nullable = false)
    private Integer progressivoComune;

    @Column(name = "denominazione", nullable = false)
    private String denominazione;


    @ManyToOne
    @JoinColumn(name = "provincia_id", referencedColumnName = "id", nullable = false)

    private Provincia provincia;
}
