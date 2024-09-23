package Epicode.epicenergy.entities;

import Epicode.epicenergy.enums.StatoFattura;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "fatture")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Fattura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private LocalDate data;
    private BigDecimal importo;
    private Integer numeroFattura;

    @Enumerated(EnumType.STRING)
    private StatoFattura stato;

     @ManyToOne
    @JoinColumn(name = "cliente_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Cliente cliente;
}
