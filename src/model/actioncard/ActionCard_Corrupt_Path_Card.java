package model.actioncard;

import model.ActionCard;
import model.Board;
import model.Grid;
import model.PathCard;
import model.pathcard.PathCard_Empty;

/**
 * Action card for the ability to corrupt a path card on the board and make it unusable
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Corrupt_Path_Card extends ActionCard {
    public ActionCard_Corrupt_Path_Card() {
        super("resources/Action_Corrupt.png");
    }

    @Override
    public boolean cardAction(Object[] target) {
        if (!(targetGrid.getCard() instanceof PathCard_Empty)) {
            Grid targetGrid = (Grid) target[0];
            targetGrid.setDisabled(true);
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
        targetGrid.setDisabled(false);

    }
}
