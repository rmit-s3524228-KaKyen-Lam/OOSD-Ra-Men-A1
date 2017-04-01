package model;

import controller.GameController;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author David Limantoro s3503728
 */
public class Game {
    private final int DECK_SIZE = 60;
    private final int NUM_OF_PLAYER = 2;

    private GameController gameCon;
    private Board board = new Board();
    private Deck deck = new Deck();
    private Player[] players;
    private int gameTurnNumber = 0;
    private int playerTurnNumber = 0;

    private Card selectedCard = null;

    public Game() {
        resetBoard();
    }

    public void resetBoard() {
        board.initBoard();
        deck = new Deck();
    }

    /**
     * Start a new game
     *
     * @param gc gameController object that
     */
    public void gameStart(GameController gc) {
        gameCon = gc;
        resetBoard();
        gameCon.redrawGrid(board.getGrid());
//        while (true) {
//            if (board.goldIsFound() == true) {
//                int playerWon = turnNumber % NUM_OF_PLAYER;
//                if (players[playerWon].getRole().equals("miner")) {
//                    //share gold
//                } else {
//                    //saboteur win
//                }
//            }
//            return;
//        }
    }

    /**
     * Method to increase player's gold (this method is for when miner wins) (precondition, the player number must not be associated with saboteur)
     *
     * @param winnerPlayerNumber the player number that wins the game.
     */
    private void shareGold(int winnerPlayerNumber) {
        ArrayList<Integer> goldPool = getGoldPool();
        // winning player get highest gold
        players[winnerPlayerNumber].setScore(players[winnerPlayerNumber].getScore() + getMax(goldPool));

    }

    /**
     * Get a pool of gold cards from the deck
     *
     * @return an arraylist containing gold cards
     */
    private ArrayList<Integer> getGoldPool(int numOfCards) {
        // Create gold deck
        ArrayList<Integer> goldDeck = new ArrayList<>();
        int i;
        for (i = 0; i < 16; i++) {
            goldDeck.add(1);
        }
        for (i = 0; i < 8; i++) {
            goldDeck.add(2);
        }
        for (i = 0; i < 4; i++) {
            goldDeck.add(3);
        }

        // Take X amount of gold card from gold deck where X is numOfCards
        ArrayList<Integer> goldPool = new ArrayList<>();
        Random randomNum = new Random();
        for (i = 0; i < (numOfCards); i++) {
            int temp = randomNum.nextInt(goldDeck.size());
            goldPool.add(goldDeck.get(temp));
            goldDeck.remove(temp);
        }
        return goldPool;
    }

    /**
     * Get the highest integer in the arraylist (precondition, array must has at least one element)
     *
     * @param intArray ArrayList of integer
     * @return the highest value in this array
     */
    private int getMax(ArrayList<Integer> intArray) {
        int max = 0;
        for (int i = 0; i < intArray.size(); i++) {
            if (max < intArray.get(i)) {
                max = intArray.get(i);
            }
        }
        return max;
    }

    /**
     * Method to place a path card on the board (precondition, card must be a path card)
     *
     * @param cardToPlace card to be placed in the board at location x,y
     * @param x           column number of the board
     * @param y           row number of the board
     * @return true if card is placed on the board successfully, otherwise false
     */
    public boolean placeCard(Card cardToPlace, int x, int y) {
        if (selectedCard != null) {
            if (cardCheck(cardToPlace, x, y)) {
                board.getGrid()[x][y].setCard(cardToPlace);
                //TODO check if touches goalCard, if yes, flip it
                //TODO check if gold card is found
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Game logic method that checks whether the path card is allowed to be placed at x,y or not
     *
     * @param cardToPlace card to be placed in the board at location x,y
     * @param x           column number of the board
     * @param y           row number of the board
     * @return true if card placement is valid and selectedCard is not null, otherwise false
     */
    private boolean cardCheck(Card cardToPlace, int x, int y) {
        //TODO do the checking if the card is allowed to be placed here or not, for now always set to true
        return true;
    }

    /**
     * This method is called after a card is selected from a player's hand and then aimed at other player or placed on the board
     */
    private void nextTurn() {
        gameTurnNumber++;
        playerTurnNumber++;
        if (playerTurnNumber >= MAX_PLAYER {
            playerTurnNumber = 0;
        }
        // TODO player draw a card

        // TODO redraw the next player deck and change the label
        selectedCard = null;
    }

    public Board getBoard() {
        return board;
    }

    public Deck getDeck() {
        return deck;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }
}
