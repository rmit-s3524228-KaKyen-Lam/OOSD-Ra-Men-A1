package model;

import controller.GameController;
import controller.GameLogic;
import controller.LogicCheckerBridge;
import model.board.Board;
import model.card.Card;
import model.card.goalcard.GoalCard_Coal;
import model.card.goalcard.GoalCard_Gold;
import model.card.pathcard.PathCard;
import model.card.pathcard.PathCard_Empty;
import model.command.Command;
import model.command.CommandHistory;
import model.command.Command_DiscardCard;
import model.command.Command_PlayCard;
import view.alertWindow.GameNotification;

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

    public static final int NUM_OF_PLAYER = 4;

    private GameLogic gameLogic;
    private Board board = new Board();
    private Deck deck = new Deck();
    private Player[] players;
    private CommandHistory commandHistory;

    private int gameTurnNumber = 0;
    private static int playerTurnNumber = 0;
    private boolean noMoreCardNotifiedOnce = false;

    private Card selectedCard = null;

    /**
     * Creates a new Game object
     */
    public Game() {
        players = new Player[NUM_OF_PLAYER];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(0, null, new ArrayList<>(), new ArrayList<>(), "" + i);
        }
        commandHistory = new CommandHistory(board, deck, players);
    }

    /**
     * Initialize the game, needed to be called during configuration stage
     */
    public void gameInitialize() {
        // Assigns the GameController so that Game can communicate with the viewer
        gameLogic = new GameLogic(board);
        LogicCheckerBridge.initialize(gameLogic);
    }

    /**
     * A method to start the game for the first time.
     *
     * Do not call this method to restart the game, use gameRestart() instead to accomplish this.
     */
    public void gameStart() {
        noMoreCardNotifiedOnce = false;
        deck.initialiseDeck();

        gameTurnNumber = 0;
        playerTurnNumber = 0;

        initPlayer();
        commandHistory.clearHistory();

        GameController.redrawUsers();
        GameController.redrawGrid();
        GameController.redrawDeck(players[getPlayerTurnNumber()].getHand());
        GameController.changePlayerLabel(getPlayerTurnNumber(), players[getPlayerTurnNumber()]);
    }

    /**
     * A method to restart the game, not to be confused with the gameInitialize and gameStart
     */
    private void gameRestart() {
        ArrayList<String> golds = new ArrayList<>();
        ArrayList<String> coals = new ArrayList<>();

        for (int x = 0; x < Board.gridMaxWidth; x++) {
            for (int y = 0; y < Board.gridMaxHeight; y++) {
                if (board.getGridAtLocation(x, y).getCard() instanceof GoalCard_Gold) {
                    golds.add(x + "," + y);
                } else if (board.getGridAtLocation(x, y).getCard() instanceof GoalCard_Coal) {
                    coals.add(x + "," + y);
                }
            }
        }

        board.configureBoard(Board.gridMaxWidth, Board.gridMaxHeight);
        for (String goldLocation : golds) {
            String[] location = goldLocation.split(",");
            board.configureGoalPos("gold", Integer.parseInt(location[0]), Integer.parseInt(location[1]));
        }
        for (String coalLocation : coals) {
            String[] location = coalLocation.split(",");
            board.configureGoalPos("coal", Integer.parseInt(location[0]), Integer.parseInt(location[1]));
        }
        board.initBoardNew();

        initPlayer();

        gameStart();
    }

    /**
     * Initialize players' deck and their role.
     * This method is to be called during creation of new game.
     */
    private void initPlayer() {
        Random rng = new Random();
        for (Player player : players) {
            if (rng.nextInt(4) == 0) {
                player.setRole(Player.ROLE_SABOTEUR);
            } else {
                player.setRole(Player.ROLE_MINER);
            }
            player.resetUndoCount();
            player.setHand(deck.draw(7));
        }
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
        String winningRole = players[winnerPlayerNumber].getRole();

        if (winningRole.equals(Player.ROLE_MINER)) {
            ArrayList<Integer> goldPool = deck.getGoldPool(NUM_OF_PLAYER);
            int j = 1;
            for (int i = 0; i < players.length; i++) {
                if (players[i].getRole().equals(Player.ROLE_MINER)) {
                    if (i == winnerPlayerNumber) {
                        players[i].addScore(goldPool.get(0));
                    } else {
                        players[i].addScore(goldPool.get(j));
                        j++;
                    }
                }
            }
        } else if (winningRole.equals(Player.ROLE_SABOTEUR)) {
            players[winnerPlayerNumber].addScore(4);
        }
    }

    /**
     * Deletes card on hand and move to next turn
     *
     * precondition:
     * 1. selectedCard must not be null
     */
    public void playDiscardCard() {
        Command command = new Command_DiscardCard(playerTurnNumber, selectedCard,
                players[playerTurnNumber].getRecentlyDrawnCard(), null);
        if (commandHistory.executeAndAddHistory(command, playerTurnNumber)) {
            nextTurn();
        } else {
            GameNotification.showAlertBoxErrorMessage("Cannot remove card");
        }
    }

    /**
     * Method to place a personal card on the board.
     *
     * precondition:
     * 1. selectedCard must not be null
     * 2. selectedCard must be subclass of PersonalCard
     *
     * @param playerNumberTarget player number to be targeted
     */
    public void playPersonalCard(int playerNumberTarget) {
        Object[] target = {players[playerNumberTarget]};
        Command command = new Command_PlayCard(playerTurnNumber, selectedCard,
                players[playerTurnNumber].getRecentlyDrawnCard(), target);
        if (commandHistory.executeAndAddHistory(command, playerTurnNumber)) {
            nextTurn();
        } else {
            GameNotification.showAlertBoxErrorMessage("Cannot target this player right now");
        }
    }

    /**
     * Method to place a path card on the board.
     * If a card is placed successfully, the specified location will be redrawn and the nextTurn() method is initiated.
     * <p>
     * precondition:
     * 1. selectedCard must not be null
     * 2. selectedCard must be subclass of PathCard
     *
     * @param x column number of the board
     * @param y row number of the board
     */
    public void playPathCard(int x, int y) {
        if (board.getGridAtLocation(x, y).getCard() instanceof PathCard_Empty) {
            Object[] target = new Object[2];
            target[0] = board.getGridAtLocation(x, y); // Targeted grid
            target[1] = board;
            Command command = new Command_PlayCard(playerTurnNumber, selectedCard,
                    players[playerTurnNumber].getRecentlyDrawnCard(), target);
            if (commandHistory.executeAndAddHistory(command, playerTurnNumber)) {
                gameLogic.checkGoalCardNeighbor(x, y, (PathCard) command.getCardToUse());
                GameController.redrawGrid();
                nextTurn();
            } else {
                GameNotification.showAlertBoxErrorMessage("This path card placement is invalid");
            }
        } else {
            GameNotification.showAlertBoxErrorMessage("Cannot place the card on top of this path");
        }
    }

    /**
     * Method to play an action card.
     *
     * precondition:
     * 1. selectedCard must not be null
     * 2. selectedCard must be subclass of ActionCard
     *
     * @param x column number of the board
     * @param y row number of the board
     */
    public void playActionCard(int x, int y) {
        if (board.getGridAtLocation(x, y).getCard() instanceof PathCard) {
            Object[] target = new Object[6];
            target[0] = board.getGridAtLocation(x, y); // Targeted grid
            target[1] = board.getGridAtLocation(x - 1, y); // Targeted grid's west neighbor
            target[2] = board.getGridAtLocation(x, y - 1); // Targeted grid's north neighbor
            target[3] = board.getGridAtLocation(x + 1, y); // Targeted grid's east neighbor
            target[4] = board.getGridAtLocation(x, y + 1); // Targeted grid's south neighbor
            target[5] = board;
            Command command = new Command_PlayCard(playerTurnNumber, selectedCard,
                    players[playerTurnNumber].getRecentlyDrawnCard(), target);
            if (commandHistory.executeAndAddHistory(command, playerTurnNumber)) {
                GameController.redrawGrid();
                nextTurn();
            } else {
                GameNotification.showAlertBoxErrorMessage("Cannot play this action card");
            }
        } else {
            GameNotification.showAlertBoxErrorMessage("Cannot play action card on non-path card");
        }
    }

    /**
     * Method to rotate a card in current player's possession, only applies for PathCard
     *
     * @param cardNum card index number in current player's hand
     * @return True if it rotates, false otherwise
     */
    public boolean rotateCard(int cardNum) {
        ArrayList<Card> playerHand = players[playerTurnNumber].getHand();
        if (playerHand.get(cardNum) instanceof PathCard) {
            playerHand.set(cardNum, CardFlyweight.getCard(playerHand.get(cardNum).getId(),
                    ((PathCard) playerHand.get(cardNum)).getRotateVal() + 1));
            GameController.redrawDeck(playerHand);
            return true;
        }
        return false;
    }

    /**
     * Method to undo a player's turn all the way to his/her previous turn
     *
     * @return True if undo is possible and executed, false when undo is not possible
     */
    public boolean undoTurn() {
        if (players[playerTurnNumber].getUndoCount() < 2 && commandHistory.undoTurn(playerTurnNumber)) {
            players[playerTurnNumber].incrementUndoCount();
            GameController.redrawGrid();
            GameController.redrawDeck(players[getPlayerTurnNumber()].getHand());
            GameController.changePlayerLabel(getPlayerTurnNumber(), players[getPlayerTurnNumber()]);
            return true;
        }
        return false;
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
            GameNotification.showAlertBoxNotificationMessage("The gold is found. This round is over");
            gameRestart();
            return;
        }
        selectedCard = null;

        // Checks if the deck runs out of card. If it doesn't, draw a card from the deck to current player.
        if (deck.getDeckSize() == 0) {

            // Notify the users (once per round) that there is no more card in the deck
            if (!noMoreCardNotifiedOnce) {
                GameNotification.showAlertBoxNotificationMessage("There is no more card in the deck");
                noMoreCardNotifiedOnce = true;
            }
        } else {
            players[playerTurnNumber].addCard(deck.draw(1)[0]);
        }


        playerTurnNumber++;
        if (playerTurnNumber >= NUM_OF_PLAYER) {
            playerTurnNumber %= NUM_OF_PLAYER;
        }

        if (players[playerTurnNumber].getSickTurn() == 0 && players[playerTurnNumber].getBrokenTool().size() == 0) {
            GameController.redrawUsers();
            GameController.redrawDeck(players[playerTurnNumber].getHand());
            GameController.changePlayerLabel(playerTurnNumber, players[playerTurnNumber]);
        } else {
            if (players[playerTurnNumber].getSickTurn() > 0) {
                players[playerTurnNumber].setSickTurn(players[playerTurnNumber].getSickTurn() - 1);
            }
            nextTurn();
        }

    }

    /**
     * Save current game state into a file
     *
     * @param filename Filename to store the save file into
     */
    public void saveGame(String filename) {
        GameState state = new GameState(this);
        if (state.saveState(filename)) {
            GameNotification.showAlertBoxNotificationMessage("Save successful");
        } else {
            GameNotification.showAlertBoxErrorMessage("Save failed");
        }
    }

    /**
     * Load an on-going game from a file
     *
     * @param filename Filename to load the save file from
     */
    public void loadGame(String filename) {
        GameState loadState = GameState.loadState(filename);
        if (loadState != null) {
            board = loadState.getBoard();
            players = loadState.getPlayers();
            commandHistory = loadState.getCommandHistory();
            deck = loadState.getDeck();
            gameTurnNumber = loadState.getGameTurnNumber();
            playerTurnNumber = loadState.getPlayerTurnNumber();
            noMoreCardNotifiedOnce = loadState.isNoMoreCardNotifiedOnce();
            gameLogic.setBoard(board);
            GameController.redrawGrid();
            GameController.redrawDeck(players[getPlayerTurnNumber()].getHand());
            GameController.changePlayerLabel(getPlayerTurnNumber(), players[getPlayerTurnNumber()]);
            GameNotification.showAlertBoxNotificationMessage("Game loaded");
        }
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

    public CommandHistory getCommandHistory() {
        return commandHistory;
    }

    public boolean isNoMoreCardNotifiedOnce() {
        return noMoreCardNotifiedOnce;
    }
}
