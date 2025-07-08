package org.app.domain.linije;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
public class Smer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long smerId;

    @ManyToOne
    @JoinColumn(name = "pocetakId")
    private Stajaliste pocetak;

    @ManyToOne
    @JoinColumn(name = "krajId")
    private Stajaliste kraj;

    @ManyToOne
    @JoinColumn(name = "linijaId")
    private Linija linija;

    @OneToMany(mappedBy = "smer", cascade = CascadeType.ALL)
    private List<Stajaliste> stajalista = new ArrayList<>();


    // pocetak i kraj su terminusi
}
