package com.example.gestionrvpatient.model;

public class Consultation {
    private int id;
    private String datec;
    private String description;
    private Patient patient;
    private Medecin medecin;

    public Consultation(String datec, String description, Patient patient, Medecin medecin) {
        this.datec = datec;
        this.description = description;
        this.patient = patient;
        this.medecin = medecin;
    }

    public Consultation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDatec() {
        return datec;
    }

    public void setDatec(String datec) {
        this.datec = datec;
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
