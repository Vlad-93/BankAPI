package main.java.model;

import java.util.ArrayList;

public class Client {
    private int id;
    private String firstName;
    private String secondName;
    private ArrayList<BankCard> cards;
    private String login;
    private String password;

    public Client() { }

    public Client(int id, String firstName, String secondName, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
    }

    public Client(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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
