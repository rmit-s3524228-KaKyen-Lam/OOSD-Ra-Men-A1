package model.card.actioncard;

import model.board.Board;
import model.board.Grid;
import model.card.pathcard.PathCard_Empty;

/**
 * Action card for the ability to corrupt a path card on the board and make it unusable
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Corrupt_Path_Card extends ActionCard {
    public ActionCard_Corrupt_Path_Card(String id) {
        super("resources/Action_Corrupt.png", id);
    }

    @Override
    public boolean cardAction(Object[] target) {
        Grid targetGrid = (Grid) target[0];
        if (!(targetGrid.getCard() instanceof PathCard_Empty)) {
            targetGrid = (Grid) target[0];
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
