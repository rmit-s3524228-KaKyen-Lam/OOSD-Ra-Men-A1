package model.actioncard;

import model.ActionCard;
import model.Grid;

/**
 * Action card for the ability to corrupt a path card on the board and make it unusable
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Corrupt_Path_Card extends ActionCard {
    public ActionCard_Corrupt_Path_Card(String imageResource, String id) {
        super(imageResource, id);
    }

    @Override
    public void cardAction(Object[] target) {
        Grid targetGrid = (Grid) target[0];
        targetGrid.setDisabled(true);
        //TODO calculate board
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Grid targetGrid = (Grid) target[0];
        targetGrid.setDisabled(false);
        //TODO calculate board
    }
}
