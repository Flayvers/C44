package com.eric.appsql;

import static com.eric.appsql.R.*;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;


public class MainActivity extends AppCompatActivity {

ListView list;
TextView reponse;
//Ecouteur ec;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        //quand on a un singleton il faut mettre getApplicationContext() au lieu de this pour un contexte en parametre
        GestionBD instance = GestionBD.getInstance(getApplicationContext());
        instance.ouvrirConnexion();
        Vector<String> v = instance.retournerInvention();
        for (String s : v) {
            System.out.println(s);
        }
        instance.fermerConnexion();


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, v);

        // Associe l'ArrayAdapter à la ListView
        list = findViewById(R.id.list);
        list.setAdapter(adapter);

        reponse = findViewById(R.id.reponse);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Logique à exécuter lorsque l'élément de la liste est cliqué
                String selectedItem = (String) parent.getItemAtPosition(position);
                if(instance.retournerInvention().equals("Paratonnerre"))
                    view.setBackgroundColor(Color.RED);

                reponse.setText(selectedItem);
            }
        });

    }







}