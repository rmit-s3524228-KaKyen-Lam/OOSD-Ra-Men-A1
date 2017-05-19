package model.actioncard;

import model.ActionCard;
import model.Game;
import model.Grid;
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
    public void cardAction(Object[] target) {
        for (int i = 0; i < 5; i++) {
            if (target[i] == null) {
                // do nothing
            } else if (target[i] instanceof Grid) {
                Grid targetGrid = (Grid) target[i];
                targetGrid.removeCardonGrid();
                //TODO Recalculate each path's board validity
            } else {
                Notification.showAlertBoxErrorMessage("That target is invalid");
            }
        }
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        for (int i = 0; i < 5; i++) {
            if (target[i] == null) {
                // do nothing
            } else if (target[i] instanceof Grid) {
                Grid targetGrid = (Grid) target[i];
                Game.board.placeCardOnLocation(targetGrid.getX(), targetGrid.getY(), targetGrid.getCard());
                //TODO Recalculate each path's board validity
            } else {
                Notification.showAlertBoxErrorMessage("That target is invalid");
            }
        }
    }
}
