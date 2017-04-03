package model;

import controller.GameController;
import controller.GameLogic;

import java.util.ArrayList;

/**
 * This is the class that contains all the information regarding the game itself.
 *
 * @author David Limantoro s3503728
 */
public class Game {
    private final int NUM_OF_PLAYER = 4;

    private GameLogic gameLogic;
    private GameController gameCon;
    private Board board = new Board();
    private Deck deck = new Deck();
    private Player[] players;

    private int gameTurnNumber = 0;
    private int playerTurnNumber = 0;
    private int numOfSaboteours = 0;

    private Card selectedCard = null;

    /**
     * Creates a new Game object
     */
    public Game() {
        players = new Player[NUM_OF_PLAYER];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(0, "miner", new ArrayList<>(), null);
        }
    }

    /**
     * Start a new game.
     * Reinitialize the board, deck, each player's hand, and reassign player to different role.
     * Lastly, redraw the game window.
     *
     * @param gc gameController object that
     */
    public void gameStart(GameController gc) {

        board.initBoard();
        deck.initialiseDeck();

        // Assigns the GameController so that Game can communicate with the viewer
        gameCon = gc;
        gameLogic = new GameLogic(board, gameCon);

        // Initialize players
        numOfSaboteours = 0;
        for (int i = 0; i < players.length; i++) {
            players[i].setHand(deck.draw(7));
            //TODO change their role here perhaps
            //TODO if someone is saboteurs, do numOfSaboteurs++;
        }

        gameCon.redrawGrid();
        gameCon.redrawDeck(players[playerTurnNumber].getHand());
        gameCon.changePlayerLabel(playerTurnNumber);
    }

    /**
     * Method to increase player's score.
     * <p>
     * This method is for when miner wins.
     * <p>
     * (precondition, the player number must not be associated with saboteur)
     *
     * @param winnerPlayerNumber the player number that wins the game.
     */
    private void shareGold(int winnerPlayerNumber) {
        ArrayList<Integer> goldPool = deck.getGoldPool(NUM_OF_PLAYER - numOfSaboteours);
        //TODO add player's score accordingly in regards to goldPool
    }

    /**
     * Method to place a path card on the board.
     * If a card is placed successfully, the specified location will be redrawn and the nextTurn() method is initiated.
     * <p>
     * precondition, selectedCard must be a path card
     *
     * @param x column number of the board
     * @param y row number of the board
     * @return true if card is placed on the board successfully, otherwise false
     */
    public boolean placeCard(int x, int y) {
        if (gameLogic.placeCard(x, y, selectedCard)) {
            nextTurn();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Handle action cards (work in progress)
     */
    public void handleActionCard(Object targetObject) {
        //TODO handle action card
    }

    /**
     * This method is called after a card is selected from a player's hand and then aimed at other player or placed on the board
     */
    public void nextTurn() {
        gameTurnNumber++;
        players[playerTurnNumber].removeCard(selectedCard);
        players[playerTurnNumber].addCard(deck.draw(1)[0]);

        //TODO handle the case when the deck runs out of card

        playerTurnNumber++;
        if (playerTurnNumber >= NUM_OF_PLAYER) {
            playerTurnNumber %= NUM_OF_PLAYER;
        }

        gameCon.redrawDeck(players[playerTurnNumber].getHand());
        gameCon.changePlayerLabel(playerTurnNumber);
        selectedCard = null;
    }

    // Getters and Setters

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
