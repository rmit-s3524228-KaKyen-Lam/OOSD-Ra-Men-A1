package model.actioncard;

import model.ActionCard;
import model.Game;
import model.Grid;

/**
 * Action card representing the ability to remove a new path to an existing eligible path card
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Remove_Path extends ActionCard {
    public ActionCard_Remove_Path(String imageResource, String id) {
        super(imageResource, id);
    }

    //TODO wait for flyweighted card
    @Override
    public void cardAction(Object[] target) {

    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
    }
}
