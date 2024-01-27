package it.epicode.Entities;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    @JoinColumn(name = "element_id")
    private Catalog element;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDate startLoan;
    private LocalDate agreedDateOfReturn;
    private LocalDate effectiveDateOfReturn;

    public Loan(){

    }

public Loan(Catalog element, User user, LocalDate startLoan, LocalDate agreedDateOfReturn, LocalDate effectiveDateOfReturn){
    this.element = element;
    this.user = user;
    this.startLoan = startLoan;
    this.agreedDateOfReturn = agreedDateOfReturn;
    this.effectiveDateOfReturn = effectiveDateOfReturn;
}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Catalog getElement() {
        return element;
    }

    public void setElement(Catalog element) {
        this.element = element;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getStartLoan() {
        return startLoan;
    }

    public void setStartLoan(LocalDate startLoan) {
        this.startLoan = startLoan;
    }

    public LocalDate getAgreedDateOfReturn() {
        return agreedDateOfReturn;
    }

    public void setAgreedDateOfReturn(LocalDate agreedDateOfReturn) {
        this.agreedDateOfReturn = agreedDateOfReturn;
    }

    public LocalDate getEffectiveDateOfReturn() {
        return effectiveDateOfReturn;
    }

    public void setEffectiveDateOfReturn(LocalDate effectiveDateOfReturn) {
        this.effectiveDateOfReturn = effectiveDateOfReturn;
    }

    @Override
    public String toString() {
        return "Loan {" +
                "id=" + id +
                ", user=" + user +
                ", element=" + element +
                ", startLoan=" + startLoan +
                ", agreedDateOfReturn=" + agreedDateOfReturn +
                ", effectiveDateOfReturn=" + effectiveDateOfReturn +
                '}';
    }
}
