package com.example.gestionrvpatient;

public class Patient extends Personne {
    private String code;
    private String email;

    public Patient(int id, String nom, String prenom, String datenaiss, String telephone, String cni, String code, String email) {
        super(id, nom, prenom, datenaiss, telephone, cni);
        this.code = code;
        this.email = email;
    }

    public Patient() {
        super( );
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}