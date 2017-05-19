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
    public ActionCard_Add_Path(String imageResource, String id) {
        super(imageResource, id);
    }

    //TODO wait for flyweighted card
    @Override
    public void cardAction(Object[] target) {
        if (target[0] instanceof Grid) {
            Grid targetGrid = (Grid) target[0];
            PathCard currentCard = (PathCard) targetGrid.getCard();
            if (currentCard instanceof PathCard_Cross || currentCard instanceof PathCard_Cross) {
                //invalid

                return;
            } else if (currentCard instanceof PathCard_DeadEnd) {
                // randomly choose between line and L

            } else if (currentCard instanceof PathCard_L || currentCard instanceof PathCard_Line) {

            } else if (currentCard instanceof PathCard_L_Hole || currentCard instanceof PathCard_Line_Hole) {

            } else if (currentCard instanceof PathCard_T) {

            } else if (currentCard instanceof PathCard_T_Hole) {

            } else {
                //invalid
            }
        }
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {

    }
}
