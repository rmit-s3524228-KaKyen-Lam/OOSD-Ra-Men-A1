package model.actioncard;

import model.ActionCard;
import model.Grid;
import model.PathCard;
import model.pathcard.*;

/**
 * Action card for the ability to add a new path to an existing eligible path card
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Add_Path extends ActionCard {
    public ActionCard_Add_Path() {
        super("resources/Action_Add_Path.png");
    }

    //TODO wait for flyweighted card
    @Override
    public boolean cardAction(Object[] target) {
        Grid targetGrid = (Grid) target[0];
        PathCard currentCard = (PathCard) targetGrid.getCard();
        if (currentCard instanceof PathCard_Cross || currentCard instanceof PathCard_Cross_Dead) {
            //invalid, do nothing
            return false;
        } else if (currentCard instanceof PathCard_DeadEnd) {
            // show option between line and L
        } else if (currentCard instanceof PathCard_L || currentCard instanceof PathCard_Line) {
            // show option between possible orientations of T
        } else if (currentCard instanceof PathCard_L_Dead || currentCard instanceof PathCard_Line_Dead) {
            // show option between possible orientations of T hole
        } else if (currentCard instanceof PathCard_T) {
            // show option between possible orientations of cross
        } else if (currentCard instanceof PathCard_T_Dead) {
            // show option between possible orientations of cross hole
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
