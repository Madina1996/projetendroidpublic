package com.example.gestionrvpatient.model;

public class Roles {
    private int id;
    private String nom;

    public Roles(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Roles() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
