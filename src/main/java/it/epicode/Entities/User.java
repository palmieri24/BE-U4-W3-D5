package it.epicode.Entities;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    long cardNumber;
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user")
    private List<Loan> loans;


    public User(String name, String lastName, LocalDate dateOfBirth){
        Random random = new Random();
        this.cardNumber = random.nextInt(0,999999);
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }


    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString(){
        return "User {" +
                "cardNumber=" + cardNumber +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

}
