package com.luxoft.currencytradeapp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "username",unique = true)
    private String login;

    @Column (name = "hashpass")
    private String password;

    @Column(name = "firstlogin")
    private boolean firstlogin;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch =FetchType.LAZY)
    private List<Operation> operations = new ArrayList<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

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

    public boolean getFirstlogin() {
        return firstlogin;
    }

    public void setFirstlogin(boolean firstlogin) {
        this.firstlogin = firstlogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}