package model;

public class Deck {
    private Card cards[];
    private final int DECK_SIZE = 60;

    public Deck() {
        cards = new Card[DECK_SIZE];
    }

    private void randomize() {
    }

    private void initialiseDeck() {

    }

    public Card[] getCards() {
        return cards;
    }
}
