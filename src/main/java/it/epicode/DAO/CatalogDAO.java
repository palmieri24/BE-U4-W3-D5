package it.epicode.DAO;

import it.epicode.Entities.Catalog;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class CatalogDAO {
    private final EntityManager entityManager;
    public CatalogDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(Catalog element){
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(element);
        transaction.commit();

        System.out.println("Element " + element + " correctly added!");
    }

    public Catalog getById(long id){
        return entityManager.find(Catalog.class, id);
    }

    public void deleteById(long id){
        Catalog elementFound = this.getById(id);

        if(elementFound != null){
            EntityTransaction transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.remove(elementFound);
            transaction.commit();

            System.out.println("Element " + elementFound + " successfully deleted");
        } else {
            System.out.println("Element with id " + id + " not found!");
        }
    }
}
