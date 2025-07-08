package org.app.domain.vozaci;

import lombok.Getter;
import lombok.Setter;
import org.app.domain.vozila.KategorijaVozila;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Entity
public class KategorijaVozaca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long kategorijaVozacaId;
    private String nazivKategorijeVozaca;

    @ManyToMany
    @JoinTable(
            name = "KategorijaVozacaZaVozila",
            joinColumns = @JoinColumn(name = "kategorijaVozacaId"),
            inverseJoinColumns = @JoinColumn(name = "kategorijaVozilaId")
    )
    private List<KategorijaVozila> kategorijeVozila;

    @OneToMany(mappedBy = "kategorijaVozaca")
    private List<Vozac> vozaci;

    public KategorijaVozaca(){}

    public KategorijaVozaca(String nazivKategorijeVozaca) {
        this.nazivKategorijeVozaca = nazivKategorijeVozaca;
        kategorijeVozila = new ArrayList<>();
        vozaci = new ArrayList<>();
    }
}
