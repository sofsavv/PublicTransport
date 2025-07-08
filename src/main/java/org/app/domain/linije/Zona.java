package org.app.domain.linije;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
public class Zona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zonaId;
    private String nazivZone;

    @OneToMany(mappedBy = "zona", cascade = CascadeType.ALL)
    private List<Stajaliste> stajalista;

    public Zona(){}
    public Zona(String nazivZone) {
        this.nazivZone = nazivZone;
        stajalista = new ArrayList<>();
    }
}
