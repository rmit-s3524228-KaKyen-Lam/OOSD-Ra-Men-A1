package model;

import model.board.Board;
import model.command.CommandHistory;

import java.io.*;

/**
 * @author David Limantoro (s3503728) on 5/24/2017.
 */
public class GameState implements Serializable {
    private Board board;
    private Deck deck;
    private Player[] players;
    private CommandHistory commandHistory;
    private int gameTurnNumber;
    private int playerTurnNumber;
    private boolean noMoreCardNotifiedOnce;

    public GameState(Game game) {
        board = game.getBoard();
        deck = game.getDeck();
        players = game.getPlayers();
        commandHistory = game.getCommandHistory();
        gameTurnNumber = game.getGameTurnNumber();
        playerTurnNumber = game.getPlayerTurnNumber();
        noMoreCardNotifiedOnce = game.isNoMoreCardNotifiedOnce();
    }

    public boolean saveState(String filepath) {
        try {
            FileOutputStream fout = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
            fout.close();
            oos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static GameState loadState(String filepath) {
        try {
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (GameState) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Board getBoard() {
        return board;
    }

    public Deck getDeck() {
        return deck;
    }

    public Player[] getPlayers() {
        return players;
    }

    public CommandHistory getCommandHistory() {
        return commandHistory;
    }

    public int getGameTurnNumber() {
        return gameTurnNumber;
    }

    public int getPlayerTurnNumber() {
        return playerTurnNumber;
    }

    public boolean isNoMoreCardNotifiedOnce() {
        return noMoreCardNotifiedOnce;
    }
}
