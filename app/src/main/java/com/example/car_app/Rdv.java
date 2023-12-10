package com.example.car_app;

public class Rdv {
    private String agence;
    private String date;

    private String service;
    private String heure;

    public Rdv(String service, String agence,String date, String heure){
        this.service = service;
        this.agence = agence;
        this.date = date;
        this.heure = heure;
    }

    public String getService() {
        return service;
    }

    public String getAgence() {
        return agence;
    }

    public String getDate() {
        return date;
    }
    public String getHeure() {
        return heure;
    }
}
