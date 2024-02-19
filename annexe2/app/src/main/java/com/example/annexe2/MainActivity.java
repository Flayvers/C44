package com.example.annexe2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {
    Button boutonValider;
    Spinner nomCompte;
    TextView solde;
    EditText destinataire;

    Hashtable<String, Compte> comptes;

    DecimalFormat dc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomCompte = findViewById(R.id.SpinnerNomCompte);
        solde = findViewById(R.id.Solde);
        destinataire = findViewById(R.id.DestinataireMail);

        comptes = new Hashtable<>();
        comptes.put("epargne", new Compte(1000, "epargne"));
        comptes.put("epargne+", new Compte(2000, "epargne+"));
        comptes.put("credit", new Compte(500, "credit"));

        dc = new DecimalFormat("0.00$");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>(comptes.keySet()));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nomCompte.setAdapter(adapter);

        // Ajout d'un écouteur d'événements au Spinner pour détecter la sélection de l'utilisateur
        nomCompte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtenir la clé du compte sélectionné
                String selectedAccount = parent.getItemAtPosition(position).toString();
                // Récupérer le compte correspondant à partir de la Hashtable
                Compte compte = comptes.get(selectedAccount);
                // Mettre à jour le TextView avec le solde du compte
                solde.setText(dc.format(compte.getSolde()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ne rien faire en cas de sélection vide
            }
        });

        boutonValider = findViewById(R.id.Envoyer);
        boutonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gérer le clic sur le bouton de validation ici
            }
        });
    }
}
