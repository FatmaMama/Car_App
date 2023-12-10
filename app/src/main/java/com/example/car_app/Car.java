package com.example.car_app;

import java.io.Serializable;

public class Car implements Serializable {
    private String modele;
    private String immatriculation;

    private String id;


    public Car(String modele, String immat,String id){
        this.modele = modele;
        this.immatriculation = immat;
        this.id = id;
    }

    public String getModele() {
        return modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public String getId() {
        return id;
    }
}
