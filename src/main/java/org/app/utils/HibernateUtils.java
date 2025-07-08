package org.app.utils;

import org.app.domain.Garaza;
import org.app.domain.Preduzece;
import org.app.domain.karta.Karta;
import org.app.domain.karta.PeriodVazenja;
import org.app.domain.karta.Povlasceni;
import org.app.domain.linije.*;
import org.app.domain.redVoznje.Polazak;
import org.app.domain.redVoznje.RedVoznje;
import org.app.domain.redVoznje.Smena;
import org.app.domain.vozaci.KategorijaVozaca;
import org.app.domain.vozaci.RadnoVreme;
import org.app.domain.vozaci.Vozac;
import org.app.domain.vozila.KategorijaVozila;
import org.app.domain.vozila.TipVozila;
import org.app.domain.vozila.Vozilo;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    private static SessionFactory sessionAnnotationFactory;

    private static SessionFactory sessionJavaConfigFactory;

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Linija.class);
            configuration.addAnnotatedClass(Smer.class);
            configuration.addAnnotatedClass(Stajaliste.class);
            configuration.addAnnotatedClass(TipStajalista.class);
            configuration.addAnnotatedClass(Zona.class);
            configuration.addAnnotatedClass(Polazak.class);
            configuration.addAnnotatedClass(RedVoznje.class);
            configuration.addAnnotatedClass(Smena.class);
            configuration.addAnnotatedClass(KategorijaVozaca.class);
            configuration.addAnnotatedClass(RadnoVreme.class);
            configuration.addAnnotatedClass(Vozac.class);
            configuration.addAnnotatedClass(Garaza.class);
            configuration.addAnnotatedClass(Preduzece.class);
            configuration.addAnnotatedClass(KategorijaVozila.class);
            configuration.addAnnotatedClass(TipVozila.class);
            configuration.addAnnotatedClass(Vozilo.class);
            configuration.addAnnotatedClass(Karta.class);
            configuration.addAnnotatedClass(PeriodVazenja.class);
            configuration.addAnnotatedClass(Povlasceni.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory
                    = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionAnnotationFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate-annotation.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionJavaConfigFactory() {
        try {
            Configuration configuration = getConfiguration();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        Properties props = new Properties();

        props.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        props.put("hibernate.connection.url", "jdbc:mysql://localhost/GradskiPrevoz");
        props.put("hibernate.connection.username", "root");
        props.put("hibernate.connection.password", "nova_lozinka");
        props.put("hibernate.current_session_context_class", "thread");
        configuration.setProperties(props);
        configuration.addAnnotatedClass(Linija.class);
        configuration.addAnnotatedClass(Smer.class);
        configuration.addAnnotatedClass(Stajaliste.class);
        configuration.addAnnotatedClass(TipStajalista.class);
        configuration.addAnnotatedClass(Zona.class);
        configuration.addAnnotatedClass(Polazak.class);
        configuration.addAnnotatedClass(RedVoznje.class);
        configuration.addAnnotatedClass(Smena.class);
        configuration.addAnnotatedClass(KategorijaVozaca.class);
        configuration.addAnnotatedClass(RadnoVreme.class);
        configuration.addAnnotatedClass(Vozac.class);
        configuration.addAnnotatedClass(Garaza.class);
        configuration.addAnnotatedClass(Preduzece.class);
        configuration.addAnnotatedClass(KategorijaVozila.class);
        configuration.addAnnotatedClass(TipVozila.class);
        configuration.addAnnotatedClass(Vozilo.class);
        configuration.addAnnotatedClass(Karta.class);
        configuration.addAnnotatedClass(PeriodVazenja.class);
        configuration.addAnnotatedClass(Povlasceni.class);
        return configuration;
    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null)
            sessionFactory = buildSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getSessionAnnotationFactory() {
        if(sessionAnnotationFactory == null) sessionAnnotationFactory = buildSessionAnnotationFactory();
        return sessionAnnotationFactory;
    }

    public static SessionFactory getSessionJavaConfigFactory() {
        if(sessionJavaConfigFactory == null) sessionJavaConfigFactory = buildSessionJavaConfigFactory();
        return sessionJavaConfigFactory;
    }

    private HibernateUtils() {

    }

}
