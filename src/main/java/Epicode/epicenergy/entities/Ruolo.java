package Epicode.epicenergy.entities;

import Epicode.epicenergy.enums.RuoloEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "ruoli")
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties
public class Ruolo {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private RuoloEnum ruoloE;

    public Ruolo(RuoloEnum ruoloE) {
        this.ruoloE = ruoloE;
    }

}
