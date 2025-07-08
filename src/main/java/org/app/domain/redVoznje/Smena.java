package org.app.domain.redVoznje;

import lombok.Getter;
import lombok.Setter;
import org.app.domain.linije.Smer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
public class Smena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long smenaId;

    private String smena;
    private LocalDateTime satnica;

    @OneToMany
    private List<Smer> smerovi;

    public List<Smer> dodajSmer(Smer smer){

        if(smerovi == null)
            smerovi = new ArrayList<>();

        if(smenaId == 1){
            if(smerovi.size() > 2)
                System.out.println("Dnevna linija ne moze imati vise od 2 smera");
            else
                smerovi.add(smer);
        }else
            smerovi.add(smer);

        return smerovi;
    }

}
