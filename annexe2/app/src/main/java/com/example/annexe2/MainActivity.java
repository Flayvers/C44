package com.example.annexe2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Listener ec;
    Button boutonValider;
    EditText nomCompte;
    TextView solde;

    EditText destinataire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boutonValider = findViewById(R.id.Valider);
        nomCompte = findViewById(R.id.NomCompte);
        solde = findViewById(R.id.Solde);
        destinataire = findViewById(R.id.DestinaireMail);

        ec = new Listener();
        boutonValider.setOnClickListener(ec);

        //final Button button = findViewById(R.id.Valider);
        //return button.setOnClickListener(new View.OnClickListener() {
            //public void onClick(View v) {

            }
            private class Listener implements View.OnClickListener {

                @Override
                public void onClick(View v) {
                    if (nomCompte.getText().toString().equals("epargne")) {
                        solde.setText("xxx.xx$");
                    } else if (nomCompte.getText().toString().equals("cheque")) {
                        solde.setText("xxx.xx$");
                    } else if(nomCompte.getText().toString().equals("epargne+")){
                        solde.setText("xxx.xx$");
                    }
                    else{
                        solde.setText("Nom de compte invalide");
                    }

                }
            }
        }