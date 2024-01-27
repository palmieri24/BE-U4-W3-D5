package it.epicode.Entities;


import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "catalog")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Catalog {
    @Id
    @GeneratedValue
    private long ISBN;
    private String title;
    private int yearOfPublication;
    private int numberOfPage;
    @OneToMany(mappedBy = "element")
    private List<Loan> loanList;

public Catalog(String title, int yearOfPublication, int numberOfPage){
    Random random = new Random();
    this.ISBN = random.nextInt(0, 999999 );
    this.title = title;
    this.yearOfPublication = yearOfPublication;
    this.numberOfPage = numberOfPage;
}

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }


    @Override
    public String toString() {
    return "Catalog : {" +
                "ISBN=" + ISBN +
                ", title='" + title + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", numberOfPage=" + numberOfPage +
                '}';
    }
}
