package Epicode.epicenergy.entities;


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
@ToString
public class Fattura {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private LocalDate data;
    private BigDecimal importo;
    private Integer numeroFattura;

    @ManyToOne
    @JoinColumn(name = "stato_fatture")
    private StatoFattura stato;


    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Fattura(LocalDate data, BigDecimal importo, Integer numeroFattura, Cliente cliente) {
        this.data = data;
        this.importo = importo;
        this.numeroFattura = numeroFattura;
        this.cliente = cliente;
    }
}
    