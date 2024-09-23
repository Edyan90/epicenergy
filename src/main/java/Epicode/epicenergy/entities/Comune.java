package Epicode.epicenergy.entities;


import jakarta.persistence.*;
import lombok.*;

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
    private Long id;

    @Column(name = "codice_provincia", nullable = false)
    private String codiceProvincia;

    @Column(name = "progressivo_comune", nullable = false)
    private Integer progressivoComune;

    @Column(name = "denominazione", nullable = false)
    private String denominazione;


    private Provincia provincia;
}
