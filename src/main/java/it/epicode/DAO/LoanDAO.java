package it.epicode.DAO;

import it.epicode.Entities.Loan;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class LoanDAO {
    private final EntityManager entityManager;
    public LoanDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    //Salva Prestito
    public void save(Loan loan){
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(loan);
        transaction.commit();

        System.out.println("Loan " + loan + " correctly added!");
    }

    //Ricerca per ID
    public Loan getById(long id){
        return entityManager.find(Loan.class, id);
    }

    //Rimozione per ID
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

    //Ricerca degli elementi attualmente in prestito dato un numero di tessera utente
    public List<Loan> getLoanByCard(long cardNumber) {
        LocalDate actualDate = LocalDate.now();
        TypedQuery<Loan> getLoanByCard = entityManager.createQuery(
                "SELECT l FROM Loan l " +
                        "JOIN l.user u " +
                        "WHERE u.cardNumber = :cardNumber AND l.agreedDateOfReturn < :actualDate", Loan.class);
        getLoanByCard.setParameter("cardNumber", cardNumber);
        return getLoanByCard.getResultList();
    }

    //Ricerca di tutti i prestiti scaduti e non ancora scaduti
    public List<Loan> getExpiredLoan() {
        LocalDate actualDate = LocalDate.now();
        TypedQuery<Loan> getExpiredLoan = entityManager.createQuery("SELECT l FROM Loan l WHERE l.agreedDateOfReturn < :actualDate", Loan.class);
        return getExpiredLoan.getResultList();
    }
}
