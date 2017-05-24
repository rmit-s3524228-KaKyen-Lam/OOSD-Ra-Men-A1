package controller;

import model.Game;
import model.PathCard;

/**
 * @author HP on 4/05/2017.
 */
public class LogicCheckerBridge {
    private static GameLogic gameLogic;

    public static void initialize(GameLogic gameLogic) {
        LogicCheckerBridge.gameLogic = gameLogic;
    }

    public static boolean checkIfValid(PathCard card, int x, int y) {
        return gameLogic.cardPlacementCheck(x, y, card);
    }
}
