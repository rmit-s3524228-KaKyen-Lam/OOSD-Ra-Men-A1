package model.actioncard;

import model.ActionCard;
import model.Board;
import model.Grid;
import model.PathCard;
import model.pathcard.PathCard_Empty;

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
    public boolean cardAction(Object[] target) {
        Grid targetGrid = (Grid) target[0];
        if (!(targetGrid.getCard() instanceof PathCard_Empty)) {
            targetGrid.setDisabled(false);
            Board targetBoard = (Board) target[5];
            targetBoard.calculateBoard();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Grid targetGrid = (Grid) target[0];
        targetGrid.setDisabled(true);
    }
}
