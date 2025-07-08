package org.app.domain.vozila;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class TipVozila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tipVozilaId;
    private String nazivTipa;

    @OneToMany(mappedBy = "tipVozila", cascade = CascadeType.ALL)
    private List<KategorijaVozila> kategorije = new ArrayList<>();

}
