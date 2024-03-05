package com.example.exam1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    EditText matricule;
    EditText serviceReussi;
    Spinner niveau;
    Button service;
    Button enregistrer;
    TextView nbrEtuEval;
    TextView moyenne;
    TextView bestEtu;
    //Ecouteur ec;
    String selectedLevel;
    Evaluation e;
    Groupe grp;
    Toast toast;
    int temp;
    //AlertDialog alert;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matricule = findViewById(R.id.matricule);
        serviceReussi = findViewById(R.id.serviceReussi);
        niveau = findViewById(R.id.niveau);
        service = findViewById(R.id.serviceReussiBtn);
        enregistrer = findViewById(R.id.register);
        nbrEtuEval = findViewById(R.id.totalStud);
        moyenne = findViewById(R.id.moyenne);
        bestEtu = findViewById(R.id.bestStud);
        grp = new Groupe();
        //serviceReussi.setOnClickListener(ec);
        //enregistrer.setOnClickListener(ec);

        Vector<String> listNiveau = new Vector<>();
        listNiveau.add("Régulier");
        listNiveau.add("Avancé");
        listNiveau.add("Étudiant-Athlète");

        serviceReussi.setText("0");
        nbrEtuEval.setText(Integer.toString(grp.vectorLen()));
        moyenne.setText(Double.toString(grp.average()));
        bestEtu.setText(grp.bestMat());

        //alert.setTitle("Erreur matricule");
        //alert.setMessage("Le matricule doit être composé de 7 chiffres .");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listNiveau);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        niveau.setAdapter(adapter);

        service.setOnClickListener(v -> {
            temp = Integer.valueOf(serviceReussi.getText().toString());
            temp++;
            serviceReussi.setText(Integer.parseInt(Integer.toString(temp)));
        });
        enregistrer.setOnClickListener(v -> {
            if (selectedLevel != null)
                if (matricule.getText().toString().length() == 7) {
                    e = new Evaluation(matricule.getText().toString(), Integer.parseInt(serviceReussi.getText().toString()), selectedLevel);
                    grp.addEval(e);
                    nbrEtuEval.setText(Integer.toString(grp.vectorLen()));
                    moyenne.setText(Double.toString(grp.average()));
                    bestEtu.setText(grp.bestMat());
                    toast = Toast.makeText(MainActivity.this, "Évaluation enregistrer", Toast.LENGTH_SHORT);
                    toast.show();
                    matricule.setText("");
                    serviceReussi.setText("");
                } else {
                    //alert.show();
                    matricule.setText("");
                }
        });

        niveau.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedLevel = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Ne rien faire en cas de sélection vide
            }
        });


        //ec = new Ecouteur();
    }

    /*private class Ecouteur implements View.OnClickListener, AdapterView.OnItemSelectedListener {

        @Override
        public void onClick(View v) {
            System.out.println("test");
            if (v.getId() == service.getId()){

            }
            else if(v.getId() == enregistrer.getId()){
                if (selectedLevel != null)
                    if(matricule.getText().toString().length() == 7) {
                        e = new Evaluation(matricule.getText().toString(), Integer.parseInt(serviceReussi.getText().toString()), selectedLevel);
                        grp.addEval(e);
                        nbrEtuEval.setText(grp.vectorLen());
                        moyenne.setText(Double.toString(grp.average()));
                        bestEtu.setText(grp.bestMat());
                        toast = Toast.makeText(MainActivity.this, "Évaluation enregistrer", Toast.LENGTH_SHORT);
                        toast.show();
                        matricule.setText("");
                        serviceReussi.setText("");
                    }
                    else {
                        //alert.show();
                        matricule.setText("");
                    }
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedLevel = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
     */
}