package com.luxoft.currencytradeapp.entity;

import javax.persistence.*;

/**
 * Created by omsk16 on 12/30/2016.
 */
@Entity
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "currency1")
    private String currency1;
    @Column(name = "currency2")
    private String currency2;
    @Column(name = "rate")
    private float rate;
    @Column(name ="reverse_rate")
    private float reverse_rate;

    public float getReverse_rate() {
        return reverse_rate;
    }

    public void setReverse_rate(float reverse_rate) {
        this.reverse_rate = reverse_rate;
    }

    public ExchangeRate() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrency1() {
        return currency1;
    }

    public void setCurrency1(String currency1) {
        this.currency1 = currency1;
    }

    public String getCurrency2() {
        return currency2;
    }

    public void setCurrency2(String currency2) {
        this.currency2 = currency2;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
