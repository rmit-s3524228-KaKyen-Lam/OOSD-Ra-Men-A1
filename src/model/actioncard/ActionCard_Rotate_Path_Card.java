package model.actioncard;

import model.ActionCard;
import model.Board;
import model.Grid;

/**
 * Action card for the ability to rotate an existing path card on board
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Rotate_Path_Card extends ActionCard {
    public ActionCard_Rotate_Path_Card(String imageResource, String id) {
        super(imageResource, id);
    }

    @Override
    public boolean cardAction(Object[] target) {
        //show all possible option and listen for response
        return true;
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Grid targetGrid = (Grid) target[0];
        Grid prevGrid = (Grid) undoExtraInformation[0];
        targetGrid.setCard(prevGrid.getCard());
    }
}
