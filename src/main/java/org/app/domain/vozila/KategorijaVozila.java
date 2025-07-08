package org.app.domain.vozila;

import lombok.Getter;
import lombok.Setter;
import org.app.domain.vozaci.KategorijaVozaca;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class KategorijaVozila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kategorijaVozilaId;
    private String nazivKategorijeVozila;
    private int mestaZaSedenje;
    private int mestaZaStajanje;
    // Klasični tramvaj može imati spojen i po jedan vagon
    // (40 mesta za sedenje i 60 mesta za stajanje).

    @ManyToMany(mappedBy = "kategorijeVozila")
    private List<KategorijaVozaca> kategorijeVozaca;

    @ManyToOne
    @JoinColumn(name = "tipVozilaId")
    private TipVozila tipVozila;

}
