package com.example.annexe3b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Ecouteur ec;
    TextView nombre_avion;
    TextView nombre_hotel;
    TextView total_text;
    ImageView bouton_avion;
    ImageView bouton_hotel;
    Button bouton_total;
    Commande cmd;

    int nb_avion = 0;
    int nb_hotel = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ec = new Ecouteur();
        cmd = new Commande();

        nombre_avion = findViewById(R.id.nombre_avion);
        nombre_hotel = findViewById(R.id.nombre_hotel);
        total_text = findViewById(R.id.text_total);
        bouton_avion = findViewById(R.id.bouton_avion);
        bouton_hotel = findViewById(R.id.bouton_hotel);
        bouton_total = findViewById(R.id.bouton_total);

        bouton_avion.setOnClickListener(ec);
        bouton_hotel.setOnClickListener(ec);
        bouton_total.setOnClickListener(ec);



    }


    class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if(v == bouton_avion){
                cmd.ajouterProduit(new BilletAvion(1));
                nb_avion++;
                nombre_avion.setText(String.valueOf(nb_avion));
            } else if (v == bouton_hotel) {
                nb_hotel++;
                cmd.ajouterProduit(new HebergementHotel(1));
                nombre_hotel.setText(String.valueOf(nb_hotel));
            }else if (v == bouton_total){
                total_text.setText(String.format("%.2f", cmd.grandTotal())+ "$");
            }
        }
    }
}