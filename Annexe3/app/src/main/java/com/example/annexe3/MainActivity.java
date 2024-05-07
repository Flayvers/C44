package com.example.annexe3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView champ_ml;
    ProgressBar pb;
    ImageView bidon;
    ImageView bouteille;
    ImageView verre;
    int ml = 0;
    Ecouteur ec;
    Toast toast;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ec = new Ecouteur();
        champ_ml = findViewById(R.id.champ_ml);
        pb = findViewById(R.id.progressBar);
        bouteille = findViewById(R.id.bouteille);
        verre = findViewById(R.id.verre);
        bidon = findViewById(R.id.bidon);
        bidon.setOnClickListener(ec);
        verre.setOnClickListener(ec);
        bouteille.setOnClickListener(ec);

        toast = Toast.makeText(this, "Bravo!!",Toast.LENGTH_SHORT);

    }
    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(v == bidon){
                ml+=1500;
            }
            else if(v == bouteille){
                ml+=330;
            }
            else if(v == verre){
                ml+=150;
            }
            if(ml >= 2000){
                toast.show();
            }

            champ_ml.setText(String.valueOf(ml) + "ml");
            System.out.println(Math.round(ml / 20));
            pb.setProgress(Math.round(ml / 20));
        }
    }
}