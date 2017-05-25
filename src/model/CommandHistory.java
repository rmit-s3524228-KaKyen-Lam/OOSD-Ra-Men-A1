package model;

import java.io.*;
import java.util.ArrayList;

/**
 * This is the the class that executes the Command object as well as the tracking each command.
 * Another purpose of this class is to undo commands, if needed.
 *
 * @author David Limantoro (s3503728) on 5/18/2017.
 */
public class CommandHistory implements Serializable {

    private ArrayList<Command> commandHistory = new ArrayList<>();
    private ArrayList<Object[]> undoExtraInformation = new ArrayList<>();

    private Board board = new Board();
    private Deck deck = new Deck();
    private Player[] players;

    public CommandHistory(Board board, Deck deck, Player[] players) {
        this.board = board;
        this.deck = deck;
        this.players = players;
    }

    /**
     * Method to clear the command history, used at the start of the game
     */
    public void clearHistory(Object caller) {
        if (caller instanceof Game) {
            commandHistory.clear();
        }
    }

    /**
     * Method to save the command history into a textfile
     *
     * @param filename The filename where this object will be saved to
     */
    public void saveHistory(String filename) {
        try {
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(commandHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to load the command history from a textfile
     *
     * @param filename The filename where this object will be loaded from
     */
    public void loadHistory(String filename) {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            commandHistory = (ArrayList<Command>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Undo the turn of a player, effectively undoing all commands of all players until this player's previous turn.
     *
     * @param playerNumber The player number that calls for the undo function
     * @return True if the undo occurred, otherwise false.
     */
    public boolean undoTurn(int playerNumber) {
        int playerLatestTurn = checkLatestTurnOfPlayer(playerNumber);
        if (playerLatestTurn > -1) {
            for (int i = commandHistory.size() - 1; i >= playerLatestTurn; i--) {
                Command prevCommand = commandHistory.get(i);
                prevCommand.undoAction(undoExtraInformation.get(i));
                Player prevPlayer = players[prevCommand.getPlayerNumber()];
                deck.addCard(prevPlayer.getRecentlyDrawnCard());
                prevPlayer.removeCard(prevPlayer.getRecentlyDrawnCard().getId());
                prevPlayer.addCard(prevCommand.getCardToUse());
                prevPlayer.setRecentlyDrawnCard(prevCommand.getCardDrawnThisTurn());
                commandHistory.remove(i);
                undoExtraInformation.remove(i);
            }
            deck.randomise();
            board.calculateBoard();
            return true;
        }
        return false;
    }

    /**
     * Execute a command and stores it to history for keeping track of the game.
     *
     * @param commandToAdd The Command object to execute
     * @return True if the command is executed correctly, otherwise false;
     */
    public boolean executeAndAddHistory(Command commandToAdd, int playerNumber) {
        try {
            undoExtraInformation.add(commandToAdd.getTarget());
            if (commandToAdd.doAction()) {
                commandHistory.add(commandToAdd);
                players[playerNumber].removeCard(commandToAdd.getCardToUse().getId());
                return true;
            } else {
                undoExtraInformation.remove(undoExtraInformation.size() - 1);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Return the index of commandHistory array where this player's last played his/her turn
     *
     * @param playerNumber the player number to look for
     * @return the index (i.e. turn number) when he/she last played.
     */
    private int checkLatestTurnOfPlayer(int playerNumber) {
        for (int i = commandHistory.size() - 1; i >= 0; i--) {
            if (commandHistory.get(i).getPlayerNumber() == playerNumber) {
                return i;
            }
        }
        return -1;
    }
}
