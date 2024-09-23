package Epicode.epicenergy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "fatture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString
public class Fattura {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private LocalDate data;
    private BigDecimal importo;
    private Integer numeroFattura;

    // @Enumerated(EnumType.STRING)
    // private StatoFattura stato;



    /* @ManyToOne
    @JoinColumn(name = "cliente_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Cliente cliente; */
}
