package model;

import controller.GameController;
import controller.GameLogic;
import controller.LogicCheckerBridge;
import model.pathcard.PathCard_Empty;
import view.Notification;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is the class that contains all the information regarding the game itself,
 * as well as the bare game logic essentials.
 * <p>
 * The higher level logic such as card placement checking is handled by GameLogic class instead.
 * <p>
 * The Game class is an example of the Information Expert principle as it has the information required to assign
 * responsibilities to objects such as Player and Deck.
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
    private CommandHistory commandHistory;

    private int gameTurnNumber = 0;
    private int playerTurnNumber = 0;
    private int nextPlayerTurnNumber = 1;
    private int numOfSaboteurs = 0;
    private boolean noMoreCardNotifiedOnce = false;

    private Card selectedCard = null;

    /**
     * Creates a new Game object
     */
    public Game() {
        players = new Player[NUM_OF_PLAYER];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(0, "miner", new ArrayList<>(), null);
        }
        commandHistory = new CommandHistory(board, deck, players);
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
        noMoreCardNotifiedOnce = false;

        // Assigns the GameController so that Game can communicate with the viewer
        gameCon = gc;
        gameLogic = new GameLogic(board, gameCon);
        LogicCheckerBridge.initialize(gameLogic, this);

        // Initialize players
        numOfSaboteurs = 0;
        for (int i = 0; i < players.length; i++) {
            players[i].setHand(deck.draw(7));
            //TODO change their role here
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
        ArrayList<Integer> goldPool = deck.getGoldPool(NUM_OF_PLAYER - numOfSaboteurs);

        int j = 1;
        for (int i = 0; i < players.length; i++) {
            if (players[i].getRole().equals("miner")) {
                if (i == winnerPlayerNumber) {
                    players[i].setScore(players[i].getScore() + goldPool.get(0));
                } else {
                    players[i].setScore(players[i].getScore() + goldPool.get(j));
                    j++;
                }
            }
        }
    }

    /**
     * Deletes the card and move to next turn
     */
    public void playDiscardCard() {
        Command command = new Command_DiscardCard(playerTurnNumber, selectedCard,
                players[playerTurnNumber].getRecentlyDrawnCard(), null);
        if (commandHistory.executeAndAddHistory(command, playerTurnNumber)) {
            nextTurn();
        } else {
            Notification.showAlertBoxErrorMessage("Cannot remove card");
        }
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
     */
    public void playPathCard(int x, int y) {
        if (board.getGridAtLocation(x, y).getCard() instanceof PathCard_Empty) {
            Object[] target = new Object[2];
            target[0] = board.getGridAtLocation(x, y);
            target[1] = board;
            Command command = new Command_PlayCard(playerTurnNumber, selectedCard,
                    players[playerTurnNumber].getRecentlyDrawnCard(), target);
            if (commandHistory.executeAndAddHistory(command, playerTurnNumber)) {
                gameLogic.checkGoalCardNeighbor(x, y, (PathCard) command.getCardToUse());
                gameCon.redrawGridXY(x, y);
                nextTurn();
            } else {
                Notification.showAlertBoxErrorMessage("This path card placement is invalid");
            }
        } else {
            Notification.showAlertBoxErrorMessage("Cannot place the card on top of this path");
        }
    }

    /**
     * Handle action cards
     * <p>
     * precondition, selectedCard must not be null
     */
    public void playActionCard(int x, int y) {
        if (board.getGridAtLocation(x, y).getCard() instanceof PathCard) {
            Object[] target = new Object[6];
            target[0] = board.getGridAtLocation(x, y);
            target[1] = board.getGridAtLocation(x - 1, y);
            target[2] = board.getGridAtLocation(x, y - 1);
            target[3] = board.getGridAtLocation(x + 1, y);
            target[4] = board.getGridAtLocation(x, y + 1);
            target[5] = board;
            Command command = new Command_PlayCard(playerTurnNumber, selectedCard,
                    players[playerTurnNumber].getRecentlyDrawnCard(), target);
            if (commandHistory.executeAndAddHistory(command, playerTurnNumber)) {
                gameCon.redrawGrid();
                nextTurn();
            } else {
                Notification.showAlertBoxErrorMessage("Cannot play this action card");
            }
        } else {
            Notification.showAlertBoxErrorMessage("Cannot play action card on non-path card");
        }
    }

    public boolean rotateCard(int cardNum) {
        ArrayList<Card> playerHand = players[playerTurnNumber].getHand();
        playerHand.set(cardNum, CardFlyweight.getCard(playerHand.get(cardNum).getId(),
                ((PathCard) playerHand.get(cardNum)).getRotateVal() + 1));
        gameCon.redrawDeck(playerHand);
        return true;
    }

    public boolean undoTurn() {
        if (players[playerTurnNumber].getUndoCount() < 2 && commandHistory.undoTurn(playerTurnNumber)) {
            players[playerTurnNumber].incrementUndoCount();
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is called after a card is used correctly and moves the game to next turn.
     * <p>
     * It checks if the game is over or not. If not, it increments the player turn number,
     * removes the currently selected card from player's hand and draws a card from a deck.
     * <p>
     * precondition, selectedCard must not be null
     */
    public void nextTurn() {
        gameTurnNumber++;
        if (board.goldIsFound()) {
            shareGold(playerTurnNumber);
            Notification.showAlertBoxNotificationMessage("The gold is found. This round is over");
            gameStart(gameCon);
        }
        selectedCard = null;

        // Checks if the deck runs out of card. If it doesn't, draw a card from the deck to current player.
        if (deck.getPointer() == deck.getDeckSize()) {
            // Notify the users (once per round) that there is no more card in the deck
            if (!noMoreCardNotifiedOnce) {
                Notification.showAlertBoxNotificationMessage("There is no more card in the deck");
                noMoreCardNotifiedOnce = true;
            }
        } else {
            players[playerTurnNumber].addCard(deck.draw(1)[0]);
        }


        playerTurnNumber = nextPlayerTurnNumber;
        while (true) {
            nextPlayerTurnNumber++;
            if (nextPlayerTurnNumber >= NUM_OF_PLAYER) {
                nextPlayerTurnNumber %= NUM_OF_PLAYER;
            }
            Player nextPlayer = players[nextPlayerTurnNumber];
            if (nextPlayer.getBrokenTool().size() == 0) {
                break;
            }
        }

        gameCon.redrawDeck(players[playerTurnNumber].getHand());
        gameCon.changePlayerLabel(playerTurnNumber, players[playerTurnNumber].getRole());
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

    public int getNextPlayerTurnNumber() {
        return nextPlayerTurnNumber;
    }

    public void removeSelectedCard() {
        this.selectedCard = null;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    /**
     * Sets current selected card to current player's turn hand, based on the index location of the card.
     *
     * @param cardNumberInCurrentHand the location of the card in the hand, starting from 0
     */
    public void setSelectedCard(int cardNumberInCurrentHand) {
        this.selectedCard = players[playerTurnNumber].getHand().get(cardNumberInCurrentHand);
    }

    public void setNextPlayerTurnNumber(int nextPlayerTurnNumber) {
        this.nextPlayerTurnNumber = nextPlayerTurnNumber;
    }
}
