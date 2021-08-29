package com.example.gestionrvpatient.model;

public class Gerant{
    private int id;
    private String prenom;
    private String nom;
    private User user;

    public Gerant(  String login, String password, Roles roles, String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }

    public Gerant() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
