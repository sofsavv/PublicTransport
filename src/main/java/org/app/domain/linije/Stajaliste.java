package org.app.domain.linije;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@Entity
public class Stajaliste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stajalisteId;

    private boolean terminus;

    @ManyToOne
    @JoinColumn(name = "smerId")
    private Smer smer;


    @ManyToOne
    @JoinColumn(name = "zonaId")
    private Zona zona;

    public Stajaliste(){}

    public Stajaliste(boolean terminus) {
        this.terminus = terminus;
    }
}
