package com.example.annexe2;

public class compte {

    String nom_compte;
    Double solde;

    public compte(String nom, double solde){
        this.nom_compte = nom;
        this.solde = solde;
    }

    public Double getSolde() {
        return solde;
    }

    public String getNom_compte() {
        return nom_compte;
    }

    public void diminuer_solde(Double transfert) {
        this.solde -= transfert;
    }
}
