package org.app.domain;

import lombok.Getter;
import lombok.Setter;
import org.app.domain.vozila.KategorijaVozila;
import org.app.domain.vozila.Vozilo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter

@Entity
public class Preduzece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preduzeceId;
    private String naziv;
    private String email;
    @ElementCollection
    private List<String> kontaktTelefoni = new ArrayList<>();
    private String vebsajt;

    @OneToMany
    private List<Garaza> garaze;

    public Vozilo kupiVozilo(String kategorija, Garaza garaza){


        if(garaze != null && !garaze.contains(garaza)){
            garaze.add(garaza);
        }else{
            garaze = new ArrayList<>();
            garaze.add(garaza);
        }

        Vozilo novoVozilo = new Vozilo();
        Calendar cal = Calendar.getInstance();
        Date danas = cal.getTime();
        novoVozilo.setDatumProizvodnje(danas);
        novoVozilo.setDatumEvaluacije(null);
        novoVozilo.setOznakaMesta(null);

        for(KategorijaVozila kv: garaza.getTipVozila().getKategorije()){
            if(kategorija.equalsIgnoreCase(kv.getNazivKategorijeVozila()))
                novoVozilo.setKategorijaVozila(kv);
        }
        novoVozilo.setGaraza(garaza);

        return novoVozilo;
    }

    public Preduzece(){}
    public Preduzece(String naziv, String email, String vebsajt) {
        this.naziv = naziv;
        this.email = email;
        this.vebsajt = vebsajt;
    }
}
