package it.epicode.DAO;

import it.epicode.Entities.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class LoanDAO {
    private final EntityManager entityManager;
    public LoanDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void save(Loan loan){
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(loan);
        transaction.commit();

        System.out.println("Loan " + loan + " correctly added!");
    }

    public Loan getById(long id){
        return entityManager.find(Loan.class, id);
    }

    public void deleteById(long id){
        Loan loanFound = this.getById(id);

        if(loanFound != null){
            EntityTransaction transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.remove(loanFound);
            transaction.commit();

            System.out.println("Loan " + loanFound + " successfully deleted");
        } else {
            System.out.println("Loan with id " + id + " not found!");
        }
    }
}
