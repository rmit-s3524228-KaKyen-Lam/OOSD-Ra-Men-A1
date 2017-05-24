package model.actioncard;

import model.*;
import view.GameNotification;

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
        if (!candidate.equals("")) {
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
            Board targetBoard = (Board) target[5];
            targetBoard.calculateBoard();
            return true;
        }
        GameNotification.showAlertBoxErrorMessage("Cannot play this action card on this pathcard");
        return false;
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Grid targetGrid = (Grid) target[0];
        PathCard oldCard = (PathCard) undoExtraInformation[0];
        targetGrid.setCard(oldCard);
    }
}
