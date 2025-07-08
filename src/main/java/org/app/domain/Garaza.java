package org.app.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.app.domain.vozila.TipVozila;
import org.app.domain.vozila.Vozilo;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "Garaza")
public class Garaza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long garazaId;
    private int kapacitet;
    private Integer popunjeno;
    private String adresa;
    private String tipGaraze;

    @ManyToOne
    @JoinColumn(name = "tipVozilaId")
    private TipVozila tipVozila;

    @OneToMany(mappedBy = "garaza")
    private List<Vozilo> vozila = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "preduzeceId")
    private Preduzece preduzece;

//    @ManyToMany
//    @JoinTable(
//            name = "garazaPreduzece",
//            joinColumns = @JoinColumn(name = "garazaId"),
//            inverseJoinColumns = @JoinColumn(name = "preduzeceId"))
//    private List<Preduzece> preduzeca;

    public List<Vozilo> dodajVozilo(Vozilo vozilo){

        if(tipVozila.getNazivTipa().equalsIgnoreCase("autobus") && popunjeno < kapacitet &&
                vozilo.getKategorijaVozila().getTipVozila().getNazivTipa().equalsIgnoreCase("minibus")){
            vozila.add(vozilo);
            popunjeno++;
        }

        if (vozilo != null && vozilo.getKategorijaVozila() != null && popunjeno < kapacitet
                && vozilo.getKategorijaVozila().getTipVozila().getTipVozilaId().equals(tipVozila.getTipVozilaId())) {
            vozila.add(vozilo);
            popunjeno++;
        }
        return vozila;
    }

    public boolean kupiVozilo(){

        if(popunjeno > kapacitet/2) return false;

        Map<String, Integer> kategorijaBrVozila = new HashMap<>();

        List<Vozilo> kopijaVozila = new ArrayList<>(this.vozila);

        for (Vozilo vozilo : kopijaVozila) {
            if(vozilo.getKategorijaVozila().getTipVozila().getNazivTipa().equals(tipVozila.getNazivTipa())){
                String p =  vozilo.getKategorijaVozila().getNazivKategorijeVozila();
                System.out.println("kategorija: " + p);
                kategorijaBrVozila.merge(p, 1, Integer::sum);
            }
        }

        for (String kategorija : kategorijaBrVozila.keySet()) {
            System.out.println(kategorija.toUpperCase());
            Vozilo novo;
            System.out.println("I < " + kategorijaBrVozila.size());
            for(int i = 0; i < kapacitet/kategorijaBrVozila.size(); i++) {
                if(popunjeno < kapacitet){
                    novo = preduzece.kupiVozilo(kategorija, this);
                    System.out.println(popunjeno + " kupljeno + 1: " + novo.getKategorijaVozila().getNazivKategorijeVozila());
                    this.dodajVozilo(novo);
                    System.out.println("sad ih je: " + popunjeno);
                }
            }
        }
        return true;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Garaza garaza = (Garaza) obj;
        return Objects.equals(garazaId, garaza.garazaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(garazaId);
    }
}
