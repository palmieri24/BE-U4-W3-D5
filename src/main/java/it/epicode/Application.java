package it.epicode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("BE-U4-W3-D5");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();

        
        
        //AGGIUNTA ELEMENTO DEL CATALOGO
        //RIMOZIONE ELEMENTO DEL CATALOGO DATO UN CODICE ISBN
        //RICERCA PER ISBN
        //RICERCA PER ANNO DI PUBBLICAZIONE
        //RICERCA PER AUTORE
        //RICERCA PER TITOLO O PARTE DI ESSO
        //RICERCA DEGLI ELEMENTI ATTUALMENTE IN PRESTITO DATO UN NUMERO DI TESSERA UTENTE
        //RICERCA DI TUTTI I PRESTITI SCADUTI E NON ANCORA RESTITUITI

        em.close();
        emf.close();
    }
}
