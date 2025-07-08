package org.app.domain.vozaci;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity
public class Vozac {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vozacId;

    @Embedded
    private RadnoVreme radnoVreme;

    @ManyToOne
    @JoinColumn(name = "kategorijaVozacaId")
    private KategorijaVozaca kategorijaVozaca;


}
