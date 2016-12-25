package com.luxoft.currencytradeapp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "username")
    private String login;
    @Column (name = "hashpass")
    private String password;
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}