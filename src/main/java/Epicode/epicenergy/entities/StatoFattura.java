package Epicode.epicenergy.entities;

import Epicode.epicenergy.enums.StatoFatturaEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Table(name = "stato_fatture")
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties
public class StatoFattura {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private StatoFatturaEnum stato;

    public StatoFattura(StatoFatturaEnum stato) {
        this.stato = stato;
    }


}
