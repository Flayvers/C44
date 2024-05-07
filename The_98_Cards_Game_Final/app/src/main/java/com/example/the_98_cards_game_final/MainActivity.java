package com.example.the_98_cards_game_final;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();

        // Example: Play a card to a pile
        Card cardToPlay = game.getHand().get(0); // Get the first card in hand
        game.playCardToPile(cardToPlay, game.getPileUp1()); // Play the card to PileUp1

        // Example: Draw a card
        game.drawCard();

        // Check game over condition
        if (game.isGameOver()) {
            // Game over logic: Display message or restart game
        }
    }
}
