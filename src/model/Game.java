package model;

/**
 * Created by HP on 23/03/2017.
 */
public class Game {
    private final int DECK_SIZE = 60;

    private Board board;
    private Deck deck = new Deck();

    public Game(){
        board = new Board();

    }

    public Board getBoard() {
        return board;
    }

    public Card[] getDeck() {
        return deck;
    }
}
