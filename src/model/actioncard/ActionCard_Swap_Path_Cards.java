package model.actioncard;

import model.ActionCard;
import model.Board;
import model.Card;
import model.Grid;

/**
 * Action card for the ability to swap a path card for another already on the board
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Swap_Path_Cards extends ActionCard {
    public ActionCard_Swap_Path_Cards() {
        super("resources/Action_Swap.png");
    }

    @Override
    public boolean cardAction(Object[] target) {
        Grid targetGrid1 = (Grid) target[0];
        Grid targetGrid2 = (Grid) target[1];
        Card temp = targetGrid1.getCard();
        targetGrid1.setCard(targetGrid2.getCard());
        targetGrid2.setCard(temp);
    }
    // no start, no goal card
    //

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Grid targetGrid = (Grid) target[0];
        Grid prevGrid = (Grid) undoExtraInformation[0];
        targetGrid.setCard(prevGrid.getCard());
    }
}
