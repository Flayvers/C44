package com.example.annexe13;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonRateBeer = findViewById(R.id.button_rate_beer);
        Button buttonViewRankings = findViewById(R.id.button_view_rankings);

        buttonRateBeer.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BeerRatingActivity.class);
            startActivity(intent);
        });

        buttonViewRankings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BeerRankingActivity.class);
            startActivity(intent);
        });
    }
}
