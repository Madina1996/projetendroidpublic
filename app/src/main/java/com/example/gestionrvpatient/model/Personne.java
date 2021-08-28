package com.example.gestionrvpatient.model;

public class Personne {
    private int id;
    private String nom;
    private String prenom;
    private String datenaiss;
    private String telephone;
    private String cni;
    private User user;

    public Personne( String nom, String prenom, String datenaiss, String telephone, String cni) {

        this.nom = nom;
        this.prenom = prenom;
        this.datenaiss = datenaiss;
        this.telephone = telephone;
        this.cni = cni;
    }

    public Personne() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(String datenaiss) {
        this.datenaiss = datenaiss;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }
}
