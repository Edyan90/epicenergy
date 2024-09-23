package Epicode.epicenergy.entities;

import Epicode.epicenergy.enums.StatoFattura;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;


import java.math.BigDecimal;
import java.time.LocalDate;

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
    private Long id;

    private LocalDate data;
    private BigDecimal importo;
    private Integer numeroFattura;

    @Enumerated(EnumType.STRING)
    private StatoFattura stato;

    /* @ManyToOne
    @JoinColumn(name = "cliente_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Cliente cliente; */
}
