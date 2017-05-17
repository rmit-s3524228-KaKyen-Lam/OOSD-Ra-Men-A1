package controller;

import model.Game;
import model.PathCard;

/**
 * @author HP on 4/05/2017.
 */
public class LogicChecker {
    private static GameLogic gameLogic;
    private static Game game;

    public static void initialize(GameLogic gameLogic, Game game) {
        LogicChecker.gameLogic = gameLogic;
        LogicChecker.game = game;
    }

    public static boolean checkIfValid(PathCard card, int x, int y) {
        return gameLogic.cardPlacementCheck(x, y, card);
    }
}
