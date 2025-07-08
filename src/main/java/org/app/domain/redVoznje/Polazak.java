package org.app.domain.redVoznje;

import lombok.Getter;
import lombok.Setter;
import org.app.domain.linije.Smer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
public class Polazak {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long polazakId;

    private LocalDateTime vremePolaska;

    @ManyToOne
    @JoinColumn(name = "smerId")
    private Smer smer;
}
