package com.example.gestionrvpatient;

public class RendezV {
    private int id;
    private String daterv;
    private String description;
    private Patient patient;
    private Medecin medecin;

    public RendezV(int id, String daterv, String description, Patient patient, Medecin medecin) {
        this.id = id;
        this.daterv = daterv;
        this.description = description;
        this.patient = patient;
        this.medecin = medecin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RendezV() {
    }

    public String getDaterv() {
        return daterv;
    }

    public void setDaterv(String daterv) {
        this.daterv = daterv;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }
}
