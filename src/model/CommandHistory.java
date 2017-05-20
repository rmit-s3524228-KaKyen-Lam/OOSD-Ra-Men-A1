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
     * @param filename
     */
    public void saveHistory(String filename) {
        try {
            FileOutputStream fout = new FileOutputStream("save\\cmdHistory.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(commandHistory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filename
     */
    public void loadHistory(String filename) {
        try {
            FileInputStream fis = new FileInputStream("save\\cmdHistory.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            commandHistory = (ArrayList<Command>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Undo the turn of a player, effectively undoing the last 4 commands.
     *
     * @return True if the undo occurred, otherwise false.
     */
    public boolean undoTurn() {
        try {
            // Check if the player is at least on turn 4 and hasn't used undo more than two times
            // TODO discard should be a command too
            if (commandHistory.size() < 4) {
                return false;
            } else {
                for (int i = 0; i < 4; i++) {
                    commandHistory.get(commandHistory.size() - 1).undoAction(undoExtraInformation.get(commandHistory.size() - 1));
                    commandHistory.remove(commandHistory.size() - 1);
                }

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Execute a command and stores it to history for keeping track of the game.
     *
     * @param commandToAdd The Command object to execute
     * @return True if the command is executed correctly, otherwise false;
     */
    public boolean executeAndAddHistory(Command commandToAdd) {
        try {
            commandToAdd.doAction();
            commandHistory.add(commandToAdd);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
