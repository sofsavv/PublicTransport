package org.app.domain.vozila;

import lombok.Getter;
import lombok.Setter;
import org.app.domain.Garaza;

import javax.persistence.*;
import java.util.Date;
@Setter
@Getter

@Entity
public class Vozilo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voziloId;
    private Date datumProizvodnje;
    private Date datumEvaluacije;
    private String oznakaMesta;


    @ManyToOne
    @JoinColumn(name = "kategorijaVozilaId")
    private KategorijaVozila kategorijaVozila;

    @ManyToOne
    @JoinColumn(name = "garazaId")
    private Garaza garaza;

    public Vozilo(Date datumProizvodnje, Date datumEvaluacije, String oznakaMesta) {
        this.datumProizvodnje = datumProizvodnje;
        this.datumEvaluacije = datumEvaluacije;
        this.oznakaMesta = oznakaMesta;
    }

    public Vozilo(){}

}
