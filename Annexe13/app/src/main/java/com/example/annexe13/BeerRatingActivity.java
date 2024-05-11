package com.example.annexe13;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BeerRatingActivity extends AppCompatActivity {

    private EditText editTextBeerName;
    private EditText editTextBrewery;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_rating);

        editTextBeerName = findViewById(R.id.edit_text_beer_name);
        editTextBrewery = findViewById(R.id.edit_text_brewery);
        ratingBar = findViewById(R.id.rating_bar);

        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(v -> saveBeerRating());
    }

    private void saveBeerRating() {
        String beerName = editTextBeerName.getText().toString();
        String brewery = editTextBrewery.getText().toString();
        int rating = (int) ratingBar.getRating();

        // Save to database
        DatabaseHelper dbHelper = DatabaseHelper.getInstance(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Enregistrement des données dans la table BeerRatings
        ContentValues values = new ContentValues();
        values.put("beerName", beerName);
        values.put("brewery", brewery);
        values.put("rating", rating);

        db.insert("BeerRatings", null, values);

        db.close(); // Assurez-vous de fermer la base de données après utilisation

        Toast.makeText(this, "Beer rating saved!", Toast.LENGTH_SHORT).show();

        finish(); // Close activity and return to MainActivity
    }
}
