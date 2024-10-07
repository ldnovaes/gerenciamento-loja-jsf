package br.com.ldnovaes.beans;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerConfig {

    private EntityManagerFactory entityManagerFactory;
    

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
    	
    	String environment = System.getenv("ENVIRONMENT");
        String persistenceUnitName;
        
        persistenceUnitName = "dev";

        if ("prod".equalsIgnoreCase(environment)) {
            persistenceUnitName = "prod";
        } 
        
        this.createEntityManagerFactory(persistenceUnitName);
        
        if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);
        }

        return entityManagerFactory.createEntityManager();    
    }

    public void close(@Disposes EntityManager em) {
    	if (em.isOpen()) {
            em.close();
        }
    }
    
    public void createEntityManagerFactory(String namePersitenceUnit) {
    	this.entityManagerFactory = Persistence.createEntityManagerFactory(namePersitenceUnit);
    }
}