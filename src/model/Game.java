package model;

import controller.GameController;
import controller.GameLogic;

import java.util.ArrayList;

/**
 * This is the class that contains all the information regarding the game itself,
 * as well as the bare game logic essentials.
 * <p>
 * The higher level logic such as card placement checking is handled by GameLogic class instead.
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
     * @param gc gameController object that will talk to the viewer classes
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
        gameCon.changePlayerLabel(playerTurnNumber, players[playerTurnNumber].getRole());
    }

    /**
     * Method to increase player's score.
     * This method is when a game round is over.
     * <p>
     * precondition, the player number must not be more than the maximum number of player
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
     * precondition:
     * 1. selectedCard must not be null
     * 2. selectedCard must be either subclass of PathCard or ActionCard
     *
     * @param x column number of the board
     * @param y row number of the board
     * @return true if card is placed on the board successfully, otherwise false
     */
    public boolean placeCard(int x, int y) {
        if (selectedCard instanceof PathCard) {
            if (gameLogic.placeCardOnBoard(x, y, selectedCard)) {
                nextTurn();
                return true;
            } else {
                return false;
            }
        } else if (selectedCard instanceof ActionCard) {
            // TODO handle action card
            handleActionCard((ActionCard) selectedCard);
            nextTurn();
            return true;
        }
        return false;
    }

    /**
     * Discards the currently selected card.
     * <p>
     * precondition, selectedCard must not be null
     */
    public void discardCard() {
        players[playerTurnNumber].removeCard(selectedCard);
    }

    /**
     * Handle action cards
     * <p>
     * precondition, selectedCard must not be null
     */
    public void handleActionCard(ActionCard actionCard) {
        //TODO handle action card
    }

    /**
     * This method is called after a card is used correctly and moves the game to next turn.
     * <p>
     * It checks if the game is over or not. If not, it increments the player turn number,
     * removes the currently selected card from player's hand and draws a card from a deck.
     */
    private void nextTurn() {
        gameTurnNumber++;
        discardCard();
        if (board.goldIsFound()) {
            shareGold(playerTurnNumber);
            // TODO implement game restart
        }

        if (deck.getPointer() == deck.getDECK_SIZE() - 30) {
            //TODO when the deck runs out of card
            System.out.println("I ran out of cards in deck");
        }

        players[playerTurnNumber].addCard(deck.draw(1)[0]);

        playerTurnNumber++;
        if (playerTurnNumber >= NUM_OF_PLAYER) {
            playerTurnNumber %= NUM_OF_PLAYER;
        }

        gameCon.redrawDeck(players[playerTurnNumber].getHand());
        gameCon.changePlayerLabel(playerTurnNumber, players[playerTurnNumber].getRole());
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
