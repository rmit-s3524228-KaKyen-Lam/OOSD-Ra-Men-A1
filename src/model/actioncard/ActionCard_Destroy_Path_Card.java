package model.actioncard;

import model.ActionCard;
import model.Game;
import model.Grid;
import view.Notification;

/**
 * Action card for the ability to destroy a particular path card
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Destroy_Path_Card extends ActionCard {
    public ActionCard_Destroy_Path_Card(String imageResource, String id) {
        super(imageResource, id);
    }

    @Override
    public void cardAction(Object[] target) {
        if (target[0] instanceof Grid) {
            Grid targetGrid = (Grid) target[0];
            targetGrid.removeCardonGrid();
        }
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Grid targetGrid = (Grid) target[0];
        Game.board.placeCardOnLocation(targetGrid.getX(), targetGrid.getY(), targetGrid.getCard());
    }
}
