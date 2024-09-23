package Epicode.epicenergy.entities;


import Epicode.epicenergy.enums.StatoFattura;
import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private StatoFattura stato;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
    