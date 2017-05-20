package model.actioncard;

import model.ActionCard;
import model.Board;
import model.Grid;
import model.PathCard;
import model.pathcard.PathCard_Empty;
import view.Notification;

/**
 * Action card for the ability to destroy an eligible path card and its neighbour path cards
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Destroy_Multiple_Paths extends ActionCard {
    public ActionCard_Destroy_Multiple_Paths(String imageResource, String id) {
        super(imageResource, id);
    }

    @Override
    public boolean cardAction(Object[] target) {
        Grid targetGrid = (Grid) target[0];
        if (!(targetGrid.getCard() instanceof PathCard_Empty)) {
            for (int i = 0; i < 5; i++) {
                if (target[i] == null) {
                    // do nothing
                } else if (target[i] instanceof Grid) {
                    targetGrid = (Grid) target[i];
                    targetGrid.removeCardonGrid();
                } else {
                    Notification.showAlertBoxErrorMessage("That target is invalid");
                }
            }
            Board targetBoard = (Board) target[5];
            targetBoard.calculateBoard();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        for (int i = 0; i < 5; i++) {
            if (target[i] == null) {
                // do nothing
            } else if (target[i] instanceof Grid) {
                Grid targetGrid = (Grid) target[i];
                Grid prevGrid = (Grid) undoExtraInformation[i];
                targetGrid.setCard(prevGrid.getCard());
            } else {
                Notification.showAlertBoxErrorMessage("That target is invalid");
            }
        }
    }
}
