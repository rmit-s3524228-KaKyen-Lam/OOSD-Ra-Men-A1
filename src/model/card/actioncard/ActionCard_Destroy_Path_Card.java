package model.card.actioncard;

import model.board.Board;
import model.board.Grid;
import model.card.pathcard.PathCard_Empty;
import view.GameNotification;

/**
 * Action card for the ability to destroy a particular path card
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Destroy_Path_Card extends ActionCard {
    public ActionCard_Destroy_Path_Card(String id) {
        super("resources/Action_Destroy_one_Path.png", id);
    }

    @Override
    public boolean cardAction(Object[] target) {
        Grid targetGrid = (Grid) target[0];
        if (!(targetGrid.getCard() instanceof PathCard_Empty)) {
            if (targetGrid.getX() == Board.startPathX && targetGrid.getY() == Board.startPathY) {
                GameNotification.showAlertBoxErrorMessage("Cannot destroy starting point");
                return false;
            } else {
                targetGrid.removeCardonGrid();
                Board targetBoard = (Board) target[5];
                targetBoard.calculateBoard();
                return true;
            }
        } else {
            return false;
        }

    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Grid targetGrid = (Grid) target[0];
        Grid prevGrid = (Grid) undoExtraInformation[0];
        targetGrid.setCard(prevGrid.getCard());
    }


}
