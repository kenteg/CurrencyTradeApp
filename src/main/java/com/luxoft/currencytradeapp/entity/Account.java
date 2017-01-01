package com.luxoft.currencytradeapp.entity;

import com.luxoft.currencytradeapp.exceptions.NotEnoughFundsException;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import javax.persistence.*;

/**
 * @author Khrishpens Viktor
 *         created Декабрь 24 2016
 */
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Columns(columns = {@Column(name = "paidMoneyCurrency"), @Column(name = "paidMoneyAmount")})
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmountAndCurrency")
    private Money balance;

    @ManyToOne
    private User user;

    public Account(Money balance) {
        this.balance = balance;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurrencyCode(){
        return this.balance.getCurrencyUnit().getCurrencyCode();
    }

    public void deposit(Money amount){
        this.balance=this.balance.plus(amount);
    }

    public void withdraw(Money amount) throws NotEnoughFundsException {
        if (this.balance.isGreaterThan(amount)) {
            this.balance=this.balance.minus(amount);
        } else throw new NotEnoughFundsException(amount.getCurrencyUnit().toString());
    }
}
