package model.actioncard;

import model.ActionCard;
import model.CardFlyweight;
import model.Grid;
import model.PathCard;
import model.pathcard.*;

/**
 * Action card representing the ability to remove a new path to an existing eligible path card
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Remove_Path extends ActionCard {
    public ActionCard_Remove_Path(String id) {
        super("resources/Action_Remove_Path.png", id);
    }

    //TODO wait for flyweighted card
    @Override
    public boolean cardAction(Object[] target) {
        Grid targetGrid = (Grid) target[0];
        PathCard currentCard = (PathCard) targetGrid.getCard();
        if (currentCard instanceof PathCard_Cross) {
            // show option between possible orientations of T
            targetGrid.setCard((PathCard) CardFlyweight.getCard("T_SHAPE", currentCard.getRotateVal()));
        } else if (currentCard instanceof PathCard_Cross_Dead) {
            // show option between possible orientations of T hole
            targetGrid.setCard((PathCard) CardFlyweight.getCard("T_SHAPE_DEAD", currentCard.getRotateVal()));
        } else if (currentCard instanceof PathCard_DeadEnd) {
            // ask user if he/she wants to remove path completely
            targetGrid.setCard((PathCard) CardFlyweight.getCard("EMPTY", currentCard.getRotateVal()));
        } else if (currentCard instanceof PathCard_T) {
            // show option between possible orientations of line or L
            targetGrid.setCard((PathCard) CardFlyweight.getCard("L_SHAPE", currentCard.getRotateVal()));
            targetGrid.setCard((PathCard) CardFlyweight.getCard("LINE_SHAPE", currentCard.getRotateVal()));
        } else if (currentCard instanceof PathCard_T_Dead) {
            // show option between possible orientations of line hole or L hole
            targetGrid.setCard((PathCard) CardFlyweight.getCard("L_SHAPE_DEAD", currentCard.getRotateVal()));
            targetGrid.setCard((PathCard) CardFlyweight.getCard("LINE_SHAPE_DEAD", currentCard.getRotateVal()));
        } else if (currentCard instanceof PathCard_L || currentCard instanceof PathCard_Line ||
                currentCard instanceof PathCard_L_Dead || currentCard instanceof PathCard_Line_Dead) {
            // show option between possible orientations of dead end
            targetGrid.setCard((PathCard) CardFlyweight.getCard("DEAD", currentCard.getRotateVal()));
        } else {
            //invalid, do nothing
            return false;
        }
        return true;

    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Grid targetGrid = (Grid) target[0];
        PathCard oldCard = (PathCard) undoExtraInformation[0];
        targetGrid.setCard(oldCard);
    }
}
