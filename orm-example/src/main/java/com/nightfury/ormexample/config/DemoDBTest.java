package com.nightfury.ormexample.config;

import com.nightfury.ormexample.config.PersistenceConfiguration;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;

public class DemoDBTest {
    public static void main(String[] args) {
        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new PersistenceConfiguration(),new HashMap<>());
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        // statements
        transaction.commit();
    }
}
