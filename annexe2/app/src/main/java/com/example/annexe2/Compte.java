package com.example.annexe2;

public class Compte {
    private double solde;
    private String nom;

    // Constructeur
    public Compte(double solde, String nom) {
        this.solde = solde;
        this.nom = nom;
    }

    // Getter pour solde
    public double getSolde() {
        return solde;
    }

    // Setter pour solde
    public void setSolde(double solde) {
        this.solde = solde;
    }

    // Getter pour nom
    public String getNom() {
        return nom;
    }

    // Setter pour nom
    public void setNom(String nom) {
        this.nom = nom;
    }
}

