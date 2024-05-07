package com.example.the_98_cards_game_final;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private List<Card> deck;
    private List<Card> hand;
    private List<Card> pileUp1, pileUp2, pileDown1, pileDown2;

    public Game() {
        initializeGame();
    }

    private void initializeGame() {
        // Initialize deck with cards from 2 to 99
        deck = new ArrayList<>();
        for (int i = 2; i <= 99; i++) {
            deck.add(new Card(i));
        }
        Collections.shuffle(deck); // Shuffle the deck

        // Initialize hand with 8 cards
        hand = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            hand.add(deck.remove(0)); // Deal cards from the deck to the hand
        }

        // Initialize piles
        pileUp1 = new ArrayList<>();
        pileUp2 = new ArrayList<>();
        pileDown1 = new ArrayList<>();
        pileDown2 = new ArrayList<>();
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> getPileUp1() {
        return pileUp1;
    }

    public List<Card> getPileUp2() {
        return pileUp2;
    }

    public List<Card> getPileDown1() {
        return pileDown1;
    }

    public List<Card> getPileDown2() {
        return pileDown2;
    }

    public void playCardToPile(Card card, List<Card> pile) {
        if (pile.isEmpty() || (card.getValue() > pile.get(pile.size() - 1).getValue())) {
            pile.add(card);
            hand.remove(card);
        } else {
            // Invalid move: card cannot be played on this pile
            // Handle error or display message to user
        }
    }

    public void drawCard() {
        if (!deck.isEmpty() && hand.size() < 8) {
            hand.add(deck.remove(0)); // Draw a card from the deck
        }
    }

    public boolean isGameOver() {
        return hand.isEmpty() && deck.isEmpty();
    }
}

