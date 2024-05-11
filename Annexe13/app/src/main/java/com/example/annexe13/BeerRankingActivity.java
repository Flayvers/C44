package com.example.annexe13;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BeerRankingActivity extends AppCompatActivity {

    private TextView textViewBeer1;
    private TextView textViewBeer2;
    private TextView textViewBeer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_ranking);

        textViewBeer1 = findViewById(R.id.text_view_beer1);
        textViewBeer2 = findViewById(R.id.text_view_beer2);
        textViewBeer3 = findViewById(R.id.text_view_beer3);

        displayTopRatedBeers();
    }

    private void displayTopRatedBeers() {
        // Retrieve top 3 rated beers from database
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("BeerRatings", null, null, null, null, null, "rating DESC", "3");

        if (cursor != null && cursor.moveToFirst()) {
            int index = 0;
            do {
                //String beerName = cursor.getString(cursor.getColumnIndex("beerName"));
                //int rating = cursor.getInt(cursor.getColumnIndex("rating"));
                @SuppressLint("Range") String beerName = cursor.getString(cursor.getColumnIndex("beerName"));
                @SuppressLint("Range") int rating = cursor.getInt(cursor.getColumnIndex("rating"));
                String displayText = beerName + " - " + rating + " stars";

                if (index == 0) {
                    textViewBeer1.setText(displayText);
                } else if (index == 1) {
                    textViewBeer2.setText(displayText);
                } else if (index == 2) {
                    textViewBeer3.setText(displayText);
                }

                index++;
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
    }
}
