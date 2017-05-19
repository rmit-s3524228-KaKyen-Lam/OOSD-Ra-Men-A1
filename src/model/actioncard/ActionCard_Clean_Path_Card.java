package model.actioncard;

import model.ActionCard;

/**
 * Action card for the ability to fix a corrupted path card
 *
 * @author Fabio Monsalve Duque s3585826
 *
 */
public class ActionCard_Clean_Path_Card extends ActionCard {
    public ActionCard_Clean_Path_Card(String imageResource, String id) {
        super(imageResource, id);
    }

    @Override
    public void cardAction() {

    }

    @Override
    public void undoCardAction() {

    }
}
