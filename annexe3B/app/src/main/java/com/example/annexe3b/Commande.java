package com.example.annexe3b;

import java.util.Vector;

public class Commande {

    private Vector<Produit> listeCommande;

    public Commande() {
        listeCommande = new Vector();
    }

    public void ajouterProduit(Produit p) {
        listeCommande.add(p);
    }

    public double total() {
        double total = 0;
        for(Produit p:listeCommande)
            total += p.getPrixUnitaire() * p.getQte();
        return total;
    }

    public double taxes() {
        return (total() * 0.05) + (total() * 0.09975);
    }

    public double grandTotal() {
        return total() + taxes();
    }
}
