package com.example.gestionrvpatient.model;

import com.example.gestionrvpatient.model.Roles;

public class User {
    private int id;
    private String login;
    private String password;
    private Roles roles;

    public User(  String login, String password, Roles roles) {

        this.login = login;
        this.password = password;
        this.roles = roles;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

}
