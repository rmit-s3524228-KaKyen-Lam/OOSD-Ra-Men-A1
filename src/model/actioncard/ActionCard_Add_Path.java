package model.actioncard;

import model.*;
import model.pathcard.*;

import java.util.Random;

/**
 * Action card for the ability to add a new path to an existing eligible path card
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Add_Path extends ActionCard {
    public ActionCard_Add_Path(String id) {
        super("resources/Action_Add_Path.png", id);
    }

    @Override
    public boolean cardAction(Object[] target) {
        Grid targetGrid = (Grid) target[0];
        PathCard currentCard = (PathCard) targetGrid.getCard();
        String candidate = "";
        if (!currentCard.isWest()) {
            candidate += "W";
        }
        if (!currentCard.isNorth()) {
            candidate += "N";
        }
        if (!currentCard.isEast()) {
            candidate += "E";
        }
        if (!currentCard.isSouth()) {
            candidate += "S";
        }
        Random rng = new Random();
        int randomNumber = rng.nextInt(candidate.length());
        String chosenCandidate = candidate.substring(randomNumber, randomNumber + 1);
        if (chosenCandidate.equals("W")) {
            targetGrid.setCard(CardFlyweight.getPathCard(true, currentCard.isNorth(),
                    currentCard.isEast(), currentCard.isSouth(), currentCard.isCentre()));
        }
        if (chosenCandidate.equals("N")) {
            targetGrid.setCard(CardFlyweight.getPathCard(currentCard.isWest(), true,
                    currentCard.isEast(), currentCard.isSouth(), currentCard.isCentre()));
        }
        if (chosenCandidate.equals("E")) {
            targetGrid.setCard(CardFlyweight.getPathCard(currentCard.isWest(), currentCard.isNorth(),
                    true, currentCard.isSouth(), currentCard.isCentre()));
        }
        if (chosenCandidate.equals("S")) {
            targetGrid.setCard(CardFlyweight.getPathCard(currentCard.isWest(), currentCard.isNorth(),
                    currentCard.isEast(), true, currentCard.isCentre()));
        }

//        if (currentCard instanceof PathCard_Cross || currentCard instanceof PathCard_Cross_Dead) {
//            //invalid, do nothing
//            return false;
//        } else if (currentCard instanceof PathCard_DeadEnd) {
//            // show option between line and L
//            targetGrid.setCard((PathCard) CardFlyweight.getCard("LINE_SHAPE", currentCard.getRotateVal()));
//            targetGrid.setCard((PathCard) CardFlyweight.getCard("L_SHAPE", currentCard.getRotateVal()));
//        } else if (currentCard instanceof PathCard_L || currentCard instanceof PathCard_Line) {
//            // show option between possible orientations of T
//            targetGrid.setCard((PathCard) CardFlyweight.getCard("T_SHAPE", currentCard.getRotateVal()));
//        } else if (currentCard instanceof PathCard_L_Dead || currentCard instanceof PathCard_Line_Dead) {
//            // show option between possible orientations of T hole
//            targetGrid.setCard((PathCard) CardFlyweight.getCard("T_SHAPE_DEAD", currentCard.getRotateVal()));
//        } else if (currentCard instanceof PathCard_T) {
//            // show option between possible orientations of cross
//            targetGrid.setCard((PathCard) CardFlyweight.getCard("CROSS_SHAPE", currentCard.getRotateVal()));
//        } else if (currentCard instanceof PathCard_T_Dead) {
//            // show option between possible orientations of cross hole
//            targetGrid.setCard((PathCard) CardFlyweight.getCard("CROSS_SHAPE_DEAD", currentCard.getRotateVal()));
//        } else {
//            //invalid, do nothing
//            return false;
//        }
        Board targetBoard = (Board) target[5];
        targetBoard.calculateBoard();
        return true;
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Grid targetGrid = (Grid) target[0];
        PathCard oldCard = (PathCard) undoExtraInformation[0];
        targetGrid.setCard(oldCard);
    }
}
