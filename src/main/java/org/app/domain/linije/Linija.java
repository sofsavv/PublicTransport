package org.app.domain.linije;

import lombok.Getter;
import lombok.Setter;
import org.app.domain.redVoznje.RedVoznje;
import org.app.domain.redVoznje.Smena;
import org.app.domain.vozila.TipVozila;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
public class Linija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long linijaId;
    private String redniBroj;

    @ManyToOne
    @JoinColumn(name = "tipVozilaId")
    private TipVozila tipVozila;

    @OneToMany(mappedBy = "linija", cascade = CascadeType.ALL)
    private List<Smer> smerovi;

    @ManyToOne
    @JoinColumn(name = "redVoznjeId")
    private RedVoznje redVoznje;

    @ManyToOne
    @JoinColumn(name = "smenaId")
    private Smena smena;

}
