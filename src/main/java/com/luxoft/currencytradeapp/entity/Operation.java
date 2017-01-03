package com.luxoft.currencytradeapp.entity;

import javax.persistence.*;

import static org.hsqldb.lib.tar.TarHeaderField.name;

/**
 * @author Khrishpens Viktor
 *         created Январь 03 2017
 */
@Entity
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column (name = "currencybuy")
    private String currencyBuy;
    @Column (name = "currencysell")
    private String currencySell;
    @Column (name = "amountbuy")
    private String amountBuy;
    @Column (name = "amountsell")
    private String amountSell;
    @Column(name = "rate")
    private String rate;
    @ManyToOne
    private User user;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }



    public Operation() {
    }

    public Operation(String currencyBuy, String currencySell, String amountBuy, String amountSell, String rate) {
        this.currencyBuy = currencyBuy;
        this.currencySell = currencySell;
        this.amountBuy = amountBuy;
        this.amountSell = amountSell;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrencyBuy() {
        return currencyBuy;
    }

    public void setCurrencyBuy(String currencyBuy) {
        this.currencyBuy = currencyBuy;
    }

    public String getCurrencySell() {
        return currencySell;
    }

    public void setCurrencySell(String currencySell) {
        this.currencySell = currencySell;
    }

    public String getAmountBuy() {
        return amountBuy;
    }

    public void setAmountBuy(String amountBuy) {
        this.amountBuy = amountBuy;
    }

    public String getAmountSell() {
        return amountSell;
    }

    public void setAmountSell(String amountSell) {
        this.amountSell = amountSell;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
