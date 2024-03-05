package com.example.exam1;

import java.util.Vector;

public class Evaluation {
    private String matricule;
    private int serviceReussi;
    private String niveau;

    // Constructeur
    public Evaluation(String matricule, int serviceReussi, String niveau) {
        this.matricule = matricule;
        this.serviceReussi = serviceReussi;
        this.niveau = niveau;
    }

    public String getMatricule() {
        return matricule;
    }

    public int getServiceReussi() {
        return serviceReussi;
    }
}
