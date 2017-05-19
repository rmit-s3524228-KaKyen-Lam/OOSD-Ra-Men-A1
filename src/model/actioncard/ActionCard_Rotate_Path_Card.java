package model.actioncard;

import model.ActionCard;

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
    public void cardAction(Object[] target) {
        //show all possible option and listen for response
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {

    }
}
