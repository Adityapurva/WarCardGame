// src/com/company/WarGame.java
package com.company;

import java.util.ArrayList;
import java.util.List;

public class WarGame {
    private Player player1;
    private Player player2;
    private Deck deck;

    public WarGame(String player1Name, String player2Name) {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        deck = new Deck();
    }

    public void play() {
        while (deck.size() >= 2) {
            Card player1Card = deck.drawCard();
            Card player2Card = deck.drawCard();

            System.out.println(player1.getName() + " plays: " + player1Card);
            System.out.println(player2.getName() + " plays: " + player2Card);

            if (player1Card.getRank().compareTo(player2Card.getRank()) > 0) {
                player1.addCard(player1Card);
                player1.addCard(player2Card);
                System.out.println(player1.getName() + " wins the round!");
            } else if (player1Card.getRank().compareTo(player2Card.getRank()) < 0) {
                player2.addCard(player1Card);
                player2.addCard(player2Card);
                System.out.println(player2.getName() + " wins the round!");
            } else {
                System.out.println("WAR!");

                List<Card> warCards = new ArrayList<>();
                warCards.add(player1Card);
                warCards.add(player2Card);

                resolveWar(warCards);
            }
        }

        // Determine the winner
        if (player1.getHandSize() > player2.getHandSize()) {
            System.out.println(player1.getName() + " wins the game!");
        } else if (player1.getHandSize() < player2.getHandSize()) {
            System.out.println(player2.getName() + " wins the game!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private void resolveWar(List<Card> warCards) {
        int cardsToDraw = Math.min(player1.getHandSize(), player2.getHandSize());

        for (int i = 0; i < cardsToDraw; i++) {
            warCards.add(player1.playCard());
            warCards.add(player2.playCard());
        }

        Card player1Card = player1.playCard();
        Card player2Card = player2.playCard();

        if (player1Card != null && player2Card != null) {
            warCards.add(player1Card);
            warCards.add(player2Card);
        }

        // Continue resolving war recursively if necessary
        if (!warCards.isEmpty()) {
            resolveWar(warCards);
        }
    }

    public static void main(String[] args) {
        WarGame game = new WarGame("Player 1", "Player 2");
        game.play();
    }
}
