package org.app.run;

import org.app.domain.Garaza;
import org.app.domain.linije.Smer;
import org.app.domain.vozaci.Vozac;
import org.app.domain.vozila.KategorijaVozila;
import org.app.domain.vozila.Vozilo;
import org.app.utils.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class AppMain {

    public static void main(String[] args) {
        popuniGaraze();
        ukupnoMesta();
        evaluacija2023();
        vozaciPoKategorijama();
        vozaciPreko40hNedeljno();
        linijeISmerovi();
//        kupiVoziloZaGarazu();
    }

    public static void popuniGaraze(){
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

        CriteriaQuery<Garaza> garazaCriteriaQuery = criteriaBuilder.createQuery(Garaza.class);
        garazaCriteriaQuery.from(Garaza.class);

        CriteriaQuery<Vozilo> voziloCriteriaQuery = criteriaBuilder.createQuery(Vozilo.class);
        voziloCriteriaQuery.from(Vozilo.class);

        List<Garaza> garaze = session.createQuery(garazaCriteriaQuery).getResultList();
        List<Vozilo> vozila = session.createQuery(voziloCriteriaQuery).getResultList();

        for (Garaza g : new ArrayList<>(garaze)) {
//            System.out.println("Trenutno obradjujemo garazu: " + g.getGarazaId());
            for (Vozilo v : new ArrayList<>(vozila)) {
                if (g.getGarazaId().equals(v.getGaraza().getGarazaId())) {
//                    System.out.println("Pronađeno vozilo u garazi: " + v.getVoziloId());
                    g.dodajVozilo(v);
//                    System.out.println("Popunjeno: " + g.getPopunjeno());
                }
            }
            g.setPopunjeno(g.getVozila().size());
            System.out.println("POPUNJENO OD STARTA " + g.getPopunjeno());
            garaze.remove(g);
        }

        session.getTransaction().commit();
    }

    public static void kupiVoziloZaGarazu(){
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Garaza> garazaCriteriaQuery = criteriaBuilder.createQuery(Garaza.class);
        garazaCriteriaQuery.from(Garaza.class);

        List<Garaza> garaze = new ArrayList<>(session.createQuery(garazaCriteriaQuery).getResultList());

        for (Garaza g : garaze) {
            for (Vozilo v : new CopyOnWriteArrayList<>(g.getVozila())) {
                if (g.getGarazaId().equals(v.getGaraza().getGarazaId())) {
                    g.kupiVozilo();
                    System.out.println("MAIN "+v.getGaraza().getTipGaraze() + " " + g.getPopunjeno());
                }
            }
        }

        session.getTransaction().commit();
    }

    public static void ukupnoMesta(){

        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Vozilo> voziloCriteriaQuery = criteriaBuilder.createQuery(Vozilo.class);
        voziloCriteriaQuery.from(Vozilo.class);

        List<Vozilo> vozila = session.createQuery(voziloCriteriaQuery).getResultList();

        int ukupnoMestaZaSedenje = 0;
        int ukupnoMestaZaStajanje = 0;
        int ukupno = 0;

        for (Vozilo vozilo : vozila) {
            KategorijaVozila kategorija = vozilo.getKategorijaVozila();

            if (kategorija != null) {
                ukupnoMestaZaSedenje += kategorija.getMestaZaSedenje();
                ukupnoMestaZaStajanje += kategorija.getMestaZaStajanje();
            }
            ukupno = ukupnoMestaZaStajanje + ukupnoMestaZaSedenje;
        }

        System.out.println("Ukupno mesta za sedenje: " + ukupnoMestaZaSedenje);
        System.out.println("Ukupno mesta za stajanje: " + ukupnoMestaZaStajanje);
        System.out.println("Ukupno mesta: " + ukupno);

        session.getTransaction().commit();

    }

    public static void evaluacija2023(){

        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Vozilo> voziloCriteriaQuery = criteriaBuilder.createQuery(Vozilo.class);
        voziloCriteriaQuery.from(Vozilo.class);

        List<Vozilo> vozila = session.createQuery(voziloCriteriaQuery).getResultList();
        Map<String, Integer> potkategorije = new HashMap<>();

        String potkategorija;

        for(Vozilo v: vozila){

            if(v.getDatumEvaluacije().toString().contains("2023")){
                potkategorija = v.getKategorijaVozila().getNazivKategorijeVozila();
                if(potkategorije.containsKey(potkategorija)) {
                    Integer value = potkategorije.get(potkategorija);
                    value++;
                    potkategorije.replace(potkategorija, value);
                }else
                    potkategorije.put(potkategorija, 1);
            }
        }

        potkategorije.forEach((p, count) ->
                System.out.println(p + " broj evaluiranih vozila tokom 2023: " + count)
        );

        session.getTransaction().commit();
    }

    public static void vozaciPoKategorijama(){

        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Vozac> vozacCriteriaQuery = criteriaBuilder.createQuery(Vozac.class);
        vozacCriteriaQuery.from(Vozac.class);
        List<Vozac> vozaci = session.createQuery(vozacCriteriaQuery).getResultList();
        Map<String, Integer> kategorijeBrVozaca = new HashMap<>();
        String kategorijaVozaca;

        for(Vozac vozac: vozaci){
            kategorijaVozaca = vozac.getKategorijaVozaca().getNazivKategorijeVozaca();
            if(!kategorijeBrVozaca.containsKey(kategorijaVozaca)){
                kategorijeBrVozaca.put(kategorijaVozaca, 1);
            }else{
                Integer value = kategorijeBrVozaca.get(kategorijaVozaca);
                value++;
                kategorijeBrVozaca.replace(kategorijaVozaca, value);
            }
        }

        kategorijeBrVozaca.forEach((k, count) ->
                System.out.println(k + " ima " + count + " vozaca")
        );

        session.getTransaction().commit();
    }

    public static void vozaciPreko40hNedeljno(){

        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Vozac> vozacCriteriaQuery = criteriaBuilder.createQuery(Vozac.class);
        vozacCriteriaQuery.from(Vozac.class);
        List<Vozac> vozaci = session.createQuery(vozacCriteriaQuery).getResultList();

        int brVozaca = 0;
        int nedeljnoSati;

        for(Vozac vozac: vozaci){
            nedeljnoSati = 0;
            nedeljnoSati+= vozac.getRadnoVreme().getBrojSatiNedeljom();
            nedeljnoSati+= vozac.getRadnoVreme().getBrojSatiSubotom();
            for(int i = 0; i < 5; i++){
                nedeljnoSati += vozac.getRadnoVreme().getBrojSatiTokomDana();
            }

            if(nedeljnoSati > 40)
                brVozaca++;
        }
        System.out.println("Preko 40h nedeljno ukupno radi " + brVozaca + " vozaca");
        session.getTransaction().commit();
    }

    public static void linijeISmerovi(){

        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Smer> smerCriteriaQuery = criteriaBuilder.createQuery(Smer.class);
        smerCriteriaQuery.from(Smer.class);

        List<Smer> smerovi = session.createQuery(smerCriteriaQuery).getResultList();
        Map<String, Integer> linijaSmer = new HashMap<>();
        String linija;

        for(Smer smer: smerovi){
            linija = smer.getLinija().getRedniBroj();
            if(!linijaSmer.containsKey(linija))
                linijaSmer.put(linija, 1);
            else{
                Integer value = linijaSmer.get(linija);
                linijaSmer.replace(linija, ++value);
            }
        }

        linijaSmer.forEach((l, count) ->
                System.out.println("Za liniju "+l + " broj smerova je: " + count)
        );

        session.getTransaction().commit();

    }
}