package model.pathcard;

import model.Command;

import java.util.ArrayList;

/**
 * @author David Limantoro (s3503728) on 5/18/2017.
 */
public class CommandHistory {

    ArrayList<Command> commandHistory = new ArrayList<>();

    public boolean clearHistory() {
        try {
            commandHistory.clear();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean undoTurn() {
        try {
            //TODO Undo 4 command here
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

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
