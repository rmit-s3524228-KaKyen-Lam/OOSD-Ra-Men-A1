package model;

import controller.GameController;

/**
 * Created by HP on 23/03/2017.
 */
public class Game {
    private final int DECK_SIZE = 60;
    private final int NUM_OF_PLAYER = 2;

    private Board board = new Board();
    private Deck deck = new Deck();
//    private Player[] players;

    public Game() {
        resetBoard();
    }

    public void resetBoard() {
        board.initBoard();
        deck = new Deck();
    }

    public void gameStart(GameController gc) {
        resetBoard();
        gc.redrawGrid(board.getGrid());
        gc.redrawGridXY(0,0,new Grid(0,0, new GoalCard("resources/Gold.png")));
//        int turnNumber = 0;
//        while (true) {
//            turnNumber++;
//            playerTurn();
//            if (board.goldIsFound() == true) {
//                int playerWon = turnNumber % NUM_OF_PLAYER;
//                if (players[playerWon].getRole().equals("miner")){
//                    //share gold
//                } else {
//                    //saboteur win
//                }
//            }
//            return;
//        }
    }

    private void playerTurn() {
        // player place card on board or discard card
        // player draw a card

    }

    public Board getBoard() {
        return board;
    }

    public Deck getDeck() {
        return deck;
    }
}
