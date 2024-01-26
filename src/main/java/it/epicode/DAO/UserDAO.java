package it.epicode.DAO;

import it.epicode.Entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class UserDAO {
    private final EntityManager entityManager;
    public UserDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(User user){
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(user);
        transaction.commit();

        System.out.println("User " + user.getName() + " correctly added!");
    }

    public User getById(long id){
        return entityManager.find(User.class, id);
    }

    public void deleteById(long id){
        User userFound = this.getById(id);

        if(userFound != null){
            EntityTransaction transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.remove(userFound);
            transaction.commit();

            System.out.println("User " + userFound.getName() + " successfully deleted");
        } else {
            System.out.println("User with id " + id + " not found!");
        }
    }

}
