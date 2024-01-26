package it.epicode.Entities;


import it.epicode.Enum.Periodicity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "magazine")
public class Magazine extends Catalog{
    @Enumerated(EnumType.STRING)
    private Periodicity periodicity;

    public Magazine(String title, int yearOfPublication, int numberOfPage, Periodicity periodicity){
        super(title, yearOfPublication, numberOfPage);
        this.periodicity = periodicity;
    }

   @Override
    public String toString() {
       return "Magazine{" +
               "ISBN=" + getISBN() +
               ", title='" + getTitle() + '\'' +
               ", yearOfPublication=" + getYearOfPublication() +
               ", numberOfPage=" + getNumberOfPage() +
               "periodicity=" + periodicity +
               '}';
   }

}
