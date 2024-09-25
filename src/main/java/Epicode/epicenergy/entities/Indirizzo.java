package Epicode.epicenergy.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "indirizzi")
public class Indirizzo {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;


    private String via;


    private String civico;


    private String località;


    private String cap;


    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comune comune;
    
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


}




