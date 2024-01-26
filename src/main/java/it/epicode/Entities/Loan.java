package it.epicode.Entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "loan")
public class Loan {
    @Id
    private long id;
    private Catalog element;

    private User user;
    private Date startLoan;
    private Date agreedDateOfReturn;
    private Date effectiveDateOfReturn;


public Loan(Catalog element, User user, Date startLoan, Date agreedDateOfReturn, Date effectiveDateOfReturn){
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

    public Date getStartLoan() {
        return startLoan;
    }

    public void setStartLoan(Date startLoan) {
        this.startLoan = startLoan;
    }

    public Date getAgreedDateOfReturn() {
        return agreedDateOfReturn;
    }

    public void setAgreedDateOfReturn(Date agreedDateOfReturn) {
        this.agreedDateOfReturn = agreedDateOfReturn;
    }

    public Date getEffectiveDateOfReturn() {
        return effectiveDateOfReturn;
    }

    public void setEffectiveDateOfReturn(Date effectiveDateOfReturn) {
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
