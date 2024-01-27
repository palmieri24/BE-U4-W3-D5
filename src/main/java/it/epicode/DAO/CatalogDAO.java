package it.epicode.DAO;

import it.epicode.Entities.Book;
import it.epicode.Entities.Catalog;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CatalogDAO {
    private final EntityManager entityManager;
    public CatalogDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    //Salva Elementi
    public void save(Catalog element){
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        entityManager.persist(element);
        transaction.commit();

        System.out.println("Element " + element + " correctly added!");
    }
    //Ricerca Elemento con un dato ISBN
    public Catalog getByISBN(long ISBN){
        return entityManager.find(Catalog.class, ISBN);
    }

    //Rimozione con un dato ISBN
    public void deleteByISBN(long ISBN){
        Catalog elementFound = this.getByISBN(ISBN);

        if(elementFound != null){
            EntityTransaction transaction = entityManager.getTransaction();

            transaction.begin();
            entityManager.remove(elementFound);
            transaction.commit();

            System.out.println("Element " + elementFound + " successfully deleted");
        } else {
            System.out.println("Element with ISBN " + ISBN + " not found!");
        }
    }
    // Ricerca per anno
    public List<Catalog> getYear(int yearOfPublication) {
        TypedQuery<Catalog> getYear = entityManager.createQuery("SELECT elem FROM Catalog elem WHERE elem.yearOfPublication = :yearOfPublication", Catalog.class);
        List<Catalog> result = getYear.setParameter("yearOfPublication", yearOfPublication).getResultList();
        return getYear.getResultList();
    }

    //Ricerca per autore
    public List<Book> getAuthor(String author) {
        TypedQuery<Book> getAuthor = entityManager.createQuery("SELECT book FROM Book book WHERE book.author LIKE :author", Book.class);
        getAuthor.setParameter("author", "%" + author + "%");
        return getAuthor.getResultList();
    }

    //Ricerca per titolo o parte di esso
    public List<Catalog> getTitle(String title) {
        TypedQuery<Catalog> getTitle = entityManager.createQuery("SELECT e FROM Element e WHERE e.title LIKE :title", Catalog.class);
        getTitle.setParameter("title", "%" + title + "%");
        return getTitle.getResultList();
    }
}
