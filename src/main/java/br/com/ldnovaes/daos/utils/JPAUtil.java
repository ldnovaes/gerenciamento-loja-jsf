package br.com.ldnovaes.daos.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("loja");

    @Produces
    @ApplicationScoped
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void close(@Disposes EntityManager em) {
        em.close();
    }
}