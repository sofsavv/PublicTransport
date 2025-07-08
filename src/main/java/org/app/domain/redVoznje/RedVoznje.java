package org.app.domain.redVoznje;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
public class RedVoznje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long redVoznjeId;

    @OneToMany
    @JoinColumn(name = "polazakId")
    private List<Polazak> polasci;


}
