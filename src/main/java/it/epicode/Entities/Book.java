package it.epicode.Entities;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book extends Catalog {
    private String author;
    private String genre;

    public Book(String title, int yearOfPublication, int numberOfPage, String author, String genre){
        super(title, yearOfPublication, numberOfPage);
        this.author = author;
        this.genre = genre;
    }
    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre(){
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }



    @Override
    public String toString() {
        return "Book {" +
                "ISBN=" + getISBN() +
                ", title='" + getTitle() + '\'' +
                ", yearOfPublication=" + getYearOfPublication() +
                ", numberOfPage=" + getNumberOfPage() +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

}
