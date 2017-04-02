package model;

import controller.GameController;

import java.util.ArrayList;

/**
 * @author David Limantoro s3503728
 */
public class Game {
    private final int NUM_OF_PLAYER = 4;

    private GameController gameCon;
    private Board board = new Board();
    private Deck deck = new Deck();
    private Player[] players;

    private int gameTurnNumber = 0;
    private int playerTurnNumber = 0;
    private int numOfSaboteours = 0;

    private Card selectedCard = null;

    public Game() {
        resetBoard();
        players = new Player[NUM_OF_PLAYER];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(0, "miner", new String[3], null);
        }
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
        deck.initialiseDeck();
        numOfSaboteours = 0;
        for (int i = 0; i < players.length; i++) {
            players[i].setHand(deck.draw(7));
            //TODO change their role here perhaps
            //TODO if someone is saboteurs, do numOfSaboteurs++;
        }
        gameCon.redrawGrid(board.getGrid());
        gameCon.redrawDeck(players[playerTurnNumber].getHand());

    }

    /**
     * Method to increase player's gold (this method is for when miner wins) (precondition, the player number must not be associated with saboteur)
     *
     * @param winnerPlayerNumber the player number that wins the game.
     */
    private void shareGold(int winnerPlayerNumber) {
        ArrayList<Integer> goldPool = deck.getGoldPool(NUM_OF_PLAYER - numOfSaboteours);
        //TODO finish this method
    }

    /**
     * Method to place a path card on the board
     * <p>
     * precondition, selectedCard must be a path card
     *
     * @param x column number of the board
     * @param y row number of the board
     * @return true if card is placed on the board successfully, otherwise false
     */
    public boolean placeCard(int x, int y) {
        if (selectedCard != null) {
            if (cardCheck(selectedCard, x, y)) {
                board.placeCard(selectedCard, x, y);
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
     * Handle action cards
     */
    public void handleActionCard(Object targetObject) {
        //TODO handle action card
    }

    /**
     * This method is called after a card is selected from a player's hand and then aimed at other player or placed on the board
     */
    public void nextTurn() {
        gameTurnNumber++;
        playerTurnNumber++;
        if (playerTurnNumber >= NUM_OF_PLAYER) {
            playerTurnNumber %= NUM_OF_PLAYER;
        }
        // TODO player draw a card
        players[playerTurnNumber].addCard(deck.draw(1)[0]);

        // TODO redraw the next player deck and change the label
        gameCon.redrawDeck(players[playerTurnNumber].getHand());
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

    public int getGameTurnNumber() {
        return gameTurnNumber;
    }

    public int getPlayerTurnNumber() {
        return playerTurnNumber;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }
}
