package model.actioncard;

import model.ActionCard;
import model.Grid;
import model.PathCard;
import model.pathcard.*;

/**
 * Action card representing the ability to remove a new path to an existing eligible path card
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Remove_Path extends ActionCard {
    public ActionCard_Remove_Path() {
        super("resources/Action_Remove_Path.png");
    }

    //TODO wait for flyweighted card
    @Override
    public boolean cardAction(Object[] target) {
        Grid targetGrid = (Grid) target[0];
        PathCard currentCard = (PathCard) targetGrid.getCard();
        if (currentCard instanceof PathCard_Cross) {
            // show option between possible orientations of T
        } else if (currentCard instanceof PathCard_Cross_Dead) {
            // show option between possible orientations of T hole
        } else if (currentCard instanceof PathCard_DeadEnd) {
            // ask user if he/she wants to remove path completely
        } else if (currentCard instanceof PathCard_T) {
            // show option between possible orientations of line or L
        } else if (currentCard instanceof PathCard_T_Dead) {
            // show option between possible orientations of line hole or L hole
        } else if (currentCard instanceof PathCard_L || currentCard instanceof PathCard_Line ||
                currentCard instanceof PathCard_L_Dead || currentCard instanceof PathCard_Line_Dead) {
            // show option between possible orientations of dead end
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
