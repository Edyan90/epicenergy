package Epicode.epicenergy.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comune")
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class Comune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codice_provincia", nullable = false)
    private String codiceProvincia;

    @Column(name = "progressivo_comune", nullable = false)
    private Integer progressivoComune;

    @Column(name = "denominazione", nullable = false)
    private String denominazione;


    private Provincia provincia;
}
