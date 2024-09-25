package Epicode.epicenergy.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "province")
public class Provincia {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String sigla;

    private String provincia;

    private String regione;

    @OneToMany(mappedBy = "provincia")
    private List<Comune> comuni = new ArrayList<>();

}
