package com.example.gestionrvpatient;

public class Medecin  extends  Personne {
    private  String specialite;


    public Medecin(String nom, String prenom, String datenaiss, String telephone, String cni, String specialite) {
        super(nom, prenom, datenaiss, telephone, cni);
        this.specialite = specialite;
    }

    public Medecin() {
        super();

    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
}
