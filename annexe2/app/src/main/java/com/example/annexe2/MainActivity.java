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
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button boutonValider;
    Spinner nomCompte;
    TextView solde;
    EditText destinataire;
    EditText montant;

    String selectedAccount;
    Hashtable<String, Compte> comptes;

    DecimalFormat dc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomCompte = findViewById(R.id.SpinnerNomCompte);
        solde = findViewById(R.id.Solde);
        destinataire = findViewById(R.id.DestinataireMail);
        montant = findViewById(R.id.Montant);

        comptes = new Hashtable<>();
        comptes.put("epargne", new Compte(1000, "EPARGNE"));
        comptes.put("epargne+", new Compte(2000, "EPARGNE PLUS"));
        comptes.put("credit", new Compte(500, "CREDIT"));
        comptes.put("cheque", new Compte(500, "CHEQUE"));

        dc = new DecimalFormat("0.00$");

        ArrayList<String> sortedList = new ArrayList<>(comptes.keySet());
        Collections.sort(sortedList);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sortedList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nomCompte.setAdapter(adapter);

        // Ajout d'un écouteur d'événements au Spinner pour détecter la sélection de l'utilisateur
        nomCompte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Obtenir la clé du compte sélectionné
                selectedAccount = parent.getItemAtPosition(position).toString();
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
        boutonValider.setOnClickListener(v -> {

            String regex_mail = "^[\\w\\.-]+@([\\w\\.-]+\\.)+[\\w-]{2,4}$";
            double montant_float = Double.valueOf(montant.getText().toString());
            Toast toast;

            if (destinataire.getText().toString().matches(regex_mail)){
                if (montant_float >= comptes.get(selectedAccount).getSolde()){
                    toast = Toast.makeText(this, "Transfert refusé. Solde insuffisant", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    comptes.get(selectedAccount).setSolde(montant_float);

                    solde.setText(dc.format(comptes.get(selectedAccount).getSolde()));
                }
            }
            else{
                toast = Toast.makeText(this, "Adresse mail invalide !", Toast.LENGTH_SHORT);
                toast.show();
            }




        });
    }
}
