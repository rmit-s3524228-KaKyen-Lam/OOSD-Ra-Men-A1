package model;

import controller.GameLogic;

import java.io.*;
import java.util.ArrayList;

/**
 * @author David Limantoro (s3503728) on 5/24/2017.
 */
public class State implements Serializable {
    private Board board;
    private Deck deck;
    private Player[] players;
    private CommandHistory commandHistory;

    public State(Game game) {
        setState(game);
    }

    public void setState(Game game) {
        board = game.getBoard();
        deck = game.getDeck();
        players = game.getPlayers();
        commandHistory = game.getCommandHistory();
    }

    public boolean saveState(String filepath) {
        try {
            FileOutputStream fout = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(this);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public State loadState(String filepath) {
        try {
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (State) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
