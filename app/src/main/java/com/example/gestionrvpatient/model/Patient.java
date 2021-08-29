package com.example.gestionrvpatient.model;

public class Patient extends Personne {
    private String email;
    public Patient( String nom, String prenom, String datenaiss, String telephone, String cni,  String email) {
        super( nom, prenom, datenaiss, telephone, cni);
        this.email = email;
    }

    public Patient() {
        super( );
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
