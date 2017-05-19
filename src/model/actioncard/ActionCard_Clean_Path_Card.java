package model.actioncard;

import model.ActionCard;
import model.Grid;
import model.PathCard;

/**
 * Action card for the ability to fix a corrupted path card
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Clean_Path_Card extends ActionCard {
    public ActionCard_Clean_Path_Card(String imageResource, String id) {
        super(imageResource, id);
    }

    @Override
    public void cardAction(Object[] target) {
        Grid targetGrid = (Grid) target[0];
        targetGrid.setDisabled(false);
        //TODO calculate board
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Grid targetGrid = (Grid) target[0];
        targetGrid.setDisabled(true);
        //TODO calculate board
    }
}
