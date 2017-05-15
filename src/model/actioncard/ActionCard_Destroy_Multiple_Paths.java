package model.actioncard;

import model.ActionCard;

/**
 * Action card for the ability to destroy an eligible path card and its neighbour path cards
 *
 * @author Fabio Monsalve Duque s3585826
 *
 */
public class ActionCard_Destroy_Multiple_Paths extends ActionCard {
    public ActionCard_Destroy_Multiple_Paths(String id) {
        super("resources/Action_Destroy_Path_And_Neighbour.png", id);
    }

    @Override
    public void cardAction() {

    }
}
