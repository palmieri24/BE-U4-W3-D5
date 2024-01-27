package it.epicode;

import it.epicode.DAO.CatalogDAO;
import it.epicode.DAO.LoanDAO;
import it.epicode.DAO.UserDAO;
import it.epicode.Entities.*;
import it.epicode.Enum.Periodicity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BE-U4-W3-D5");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        CatalogDAO catalogDAO = new CatalogDAO(em);
        UserDAO userDAO = new UserDAO(em);
        LoanDAO loanDAO = new LoanDAO(em);

        Scanner input = new Scanner(System.in);
        int inputChoose = 0;

        try {
            while (true){
                System.out.println("Inserisci 1 per aggiungere un elemento all'archivio, 2 per rimuovere un elemento tramite ISBN,3 per cercare un elemento tramite ISBN,4 per cercare un elemento tramite l'anno");
                System.out.println("Inserisci 5 per cercare un libro tramite il suo autore,6 per salvare l'archivio sul disco, 7 per leggere l'archivio salvato sul disco, 0 per interrompere l'operazione.");
                inputChoose = Integer.parseInt(input.nextLine());

                switch (inputChoose) {
                    case 0:
                        break;
                    case 1:
                        try {
                            System.out.println("Inserisci 'book' per aggiungere un libro, 'magazine' per aggiungere una rivista");
                            String create = input.nextLine();
                            //Creare un book
                            if (create.toLowerCase().trim().equals("book")) {
                                System.out.println("Inserisci il titolo del libro: ");
                                String title = input.nextLine();
                                System.out.println("Inserisci l'anno di pubblicazione: ");
                                int yearOfPublication = Integer.parseInt(input.nextLine());
                                System.out.println("Inserisci il n° di pagine: ");
                                int numberOfPages = Integer.parseInt(input.nextLine());
                                System.out.println("Inserisci il nome dell'autore: ");
                                String author = input.nextLine();
                                System.out.println("Inserisci il genere: ");
                                String genre = input.nextLine();
                                Book book = new Book(title, yearOfPublication, numberOfPages, author, genre);
                                catalogDAO.save(book);

                            }  //Creare un magazine
                            else if (create.toLowerCase().trim().equals("magazine")) {
                                System.out.println("Inserisci il titolo della rivista: ");
                                String title = input.nextLine();
                                System.out.println("Inserisci l'anno di pubblicazione: ");
                                int yearOfPublication = Integer.parseInt(input.nextLine());
                                System.out.println("Inserisci il n° di pagine: ");
                                int numberOfPages = Integer.parseInt(input.nextLine());
                                System.out.println("Inserisci la periodicità (scegli fra settimanale,mensile,semestrale): ");
                                String periodicity = input.nextLine();
                                if (periodicity.toLowerCase().trim().equals("settimanale")) {
                                    Magazine magazine = new Magazine(title, yearOfPublication, numberOfPages, Periodicity.WEEKLY);
                                    catalogDAO.save(magazine);
                                } else if (periodicity.toLowerCase().trim().equals("mensile")) {
                                    Magazine magazine = new Magazine(title, yearOfPublication, numberOfPages, Periodicity.MONTHLY);
                                    catalogDAO.save(magazine);
                                } else if (periodicity.toLowerCase().trim().equals("semestrale")) {
                                    Magazine magazine = new Magazine(title, yearOfPublication, numberOfPages, Periodicity.HALFYEAR);
                                    catalogDAO.save(magazine);
                                }
                            } else {
                                System.out.println("Elemento non disponibile nel catalogo!");
                            }
                        } catch (Exception e) {
                            System.out.println("ERRORE : " + e.getMessage());
                        }
                        break;
                    //Rimozione elemento dato un codice ISBN:
                    case 2:
                        System.out.println("Inserisci il codice ISBN per rimuovere l'elemento dall'archivio: ");
                        long ISBN = Integer.parseInt(input.nextLine());
                        catalogDAO.deleteByISBN(ISBN);
                        break;

                    //Ricerca tramite ISBN:
                    case 3:
                        try {
                            System.out.println("Inserisci il codice ISBN per cercare l'elemento nell'archivio: ");
                            long ISBNfind = Integer.parseInt(input.nextLine());
                            Catalog ISBNfound = catalogDAO.getByISBN(ISBNfind);

                            if(ISBNfound == null){
                                System.out.println("ISBN non trovato!");
                            }

                        } catch (Exception e) {
                            System.out.println("ERRORE : " + e.getMessage());
                        }
                        break;

                    //Ricerca per anno di pubblicazione:
                    case 4:
                        try {
                            System.out.println("Inserisci un anno per cercare l'elemento nell'archivio: ");
                            int findYear = Integer.parseInt(input.nextLine());
                            List<Catalog> year = catalogDAO.getYear(findYear);
                            if (year.isEmpty()) {
                                System.out.println("In quell'anno non è stato pubblicato alcun libro!");
                            }
                        } catch (Exception e) {
                            System.out.println("ERRORE : " + e.getMessage());
                        }

                        break;
                    //Ricerca per Autore:
                    case 5:
                        try {
                            System.out.println("Inserisci un autore per cercare l'elemento nell'archivio: ");
                            String findAuthor = input.nextLine();
                            List<Book> catalog = catalogDAO.getAuthor(findAuthor);
                            if (catalog.isEmpty()) {
                                System.out.println("Non ci sono libri scritti da questo autore!");
                            }
                        } catch (Exception e) {
                            System.out.println("ERRORE : " + e.getMessage());
                        }
                        break;

                    //Ricerca per titolo o parte di esso:
                    case 6:
                        try {
                            System.out.println("Inserisci un titolo o parte di esso per ricercare un elemento: ");
                            String title = input.nextLine();

                            List<Catalog> title1 = catalogDAO.getTitle(title);
                            if (title1.isEmpty()) {
                                System.out.println("Nessun elemento con questo titolo è stato ritrovato!");
                            }
                        } catch (Exception e) {
                            System.err.println("ERRORE : " + e.getMessage());
                        }
                        break;

                        //Ricerca degli elementi attualmente in prestito dato un numero di tessera utente
                    case 7:
                        try {
                            System.out.println("Inserisci 1 per creare un Utente, 2 per eliminarlo tramite numero tessera, 3 per ricercarlo tramite il numero tessera: ");
                            inputChoose = Integer.parseInt(input.nextLine());
                            switch (inputChoose) {
                                case 1:
                                    try {
                                        System.out.println("Inserisci un nome utente: ");
                                        String name = input.nextLine();
                                        System.out.println("Inserisci un cognome utente: ");
                                        String lastName = input.nextLine();
                                        System.out.println("Inserisci data di nascita: ");
                                        LocalDate dateOfBirth = input.nextLine();
                                        User user= new User(name, lastName, dateOfBirth);
                                        userDAO.save(user);
                                    } catch (Exception e) {
                                        System.err.println(e.getMessage());
                                    }
                                    break;
                                case 2:
                                    System.out.println("Inserisci il numero di tessera per rimuovere l'utente: ");
                                    int cardNumberRemove = Integer.parseInt(input.nextLine());
                                    userDAO.deleteById(cardNumberRemove);
                                    break;
                                case 3:
                                    System.out.println("Inserisci il numero di tessera per ricercare l'utente: ");
                                    int cardNumberFind = Integer.parseInt(input.nextLine());
                                    System.out.println(userDAO.getById(cardNumberFind));
                                    break;
                            }
                        } catch (Exception e) {
                            System.err.println(e.getMessage());

                        }
                        break;

                        //Ricerca tutti i prestiti scaduti e non ancora restituiti
                    case 8:
                        System.out.println("Inserisci 1 per creare un Prestito, 2 per eliminarlo tramite id, 3 per cercare prestiti di un Utente tramite numero tessera, 4 per vedere i prestiti scaduti e non ancora restituiti");
                        inputChoose = Integer.parseInt(input.nextLine());
                        switch (inputChoose) {
                            case 1:
                                try {
                                    System.out.println("Inserisci il numero della tessera utente: ");
                                    long cardNumber = Integer.parseInt(input.nextLine());
                                    User userID = userDAO.getById(cardNumber);
                                    System.out.println("Inserisci ISBN dell'elemento scelto: ");
                                    long isbn = Integer.parseInt(input.nextLine());
                                    Catalog elementUtente = catalogDAO.getByISBN(isbn);
                                    System.out.println("Inserisci data inizio prestito : ");
                                    LocalDate startLoan = input.nextLine();
                                    Loan loan = new Loan();
                                    loanDAO.save(loan);
                                } catch (Exception e) {
                                    System.err.println(e.getMessage());
                                }
                            case 2:
                                System.out.println("Inserisci ID prestito per rimuoverlo: ");
                                long loanID = Integer.parseInt(input.nextLine());
                                LoanDAO.deleteById(loanID);
                                break;
                            case 3:
                                System.out.println("Inserisci numero tessera utente per mostrarti tutti i suoi prestiti : ");
                                long cardNumber = Integer.parseInt(input.nextLine());
                                List<Loan> loanList = loanDAO.getLoanByCard(cardNumber);
                                loanList.forEach(System.out::println);
                                break;
                            case 4:
                                System.out.println("Tutti i prestiti scaduti e non ancora restituiti: ");
                                List<Loan> loanExpired = loanDAO.getExpiredLoan();
                                loanExpired.forEach(System.out::println);
                                break;
                        }
                        break;
                    default:
                        throw new Exception();
                } } } catch (Exception e) {
            System.out.println("ERRORE : " + e.getMessage());
        }
        em.close();
        emf.close();
    }
}
