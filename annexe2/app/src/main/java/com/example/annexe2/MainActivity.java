package com.example.annexe2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    Button envoyer;
    Spinner champ_nom_compte;
    EditText champ_a;
    TextView solde;
    EditText transfert;
    Vector<String> choix = new Vector<>();
    DecimalFormat df;
    Hashtable<String, compte> listeComptes;
    String compte;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listeComptes = new Hashtable<>();
        listeComptes.put("Epargne", new compte("Epargne", 500));
        listeComptes.put("Cheque", new compte("Cheque", 700));
        listeComptes.put("EpargnePlus", new compte("EpargnePlus", 250));
        Set<String> ensCles = listeComptes.keySet();
        choix = new Vector<>(ensCles);

        df = new DecimalFormat("0.00$");

        //Initialiser les variables
        envoyer = findViewById(R.id.envoyer);
        champ_nom_compte = findViewById(R.id.nom_de);
        champ_a = findViewById(R.id.nom_a);
        solde = findViewById(R.id.solde);
        transfert = findViewById(R.id.transfert);

        ArrayAdapter<String> ar = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,choix);
        champ_nom_compte.setAdapter(ar);

        //Premiere étape, créer l'écouteur
        ec = new Ecouteur();

        //Deuxième étape, inscrire la source à l'écouteur
        envoyer.setOnClickListener(ec);
        champ_nom_compte.setOnItemSelectedListener(ec);


    }

    private class Ecouteur implements View.OnClickListener, AdapterView.OnItemSelectedListener {


        @Override
        public void onClick(View v) {




            double solde_num = listeComptes.get(compte).getSolde();
            double transfert_num = Double.parseDouble(transfert.getText().toString());

            if (validate(champ_a.getText().toString())) {
                if (transfert_num < solde_num) {
                    listeComptes.get(compte).diminuer_solde( transfert_num );
                    solde.setText( df.format( listeComptes.get(compte).getSolde()) );
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Send $$")
                            .setMessage("Are you sure you want to send")
                            .show();
                }
            } else {
                champ_a.setText("");
                champ_a.setHint("Indiquer un destinataire");
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            compte = choix.get(position);
            solde.setText( df.format( listeComptes.get(compte).getSolde()) );

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}