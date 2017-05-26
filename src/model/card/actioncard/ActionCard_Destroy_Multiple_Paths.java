package model.card.actioncard;

import model.board.Board;
import model.board.Grid;
import model.card.pathcard.PathCard_Empty;
import view.GameNotification;

/**
 * Action card for the ability to destroy an eligible path card and its neighbour path cards
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Destroy_Multiple_Paths extends ActionCard {
    public ActionCard_Destroy_Multiple_Paths(String id) {
        super("resources/Action_Destroy_Path_And_Neighbour.png", id);
    }

    @Override
    public boolean cardAction(Object[] target) {
        Grid targetGrid = (Grid) target[0];
        if (!(targetGrid.getCard() instanceof PathCard_Empty)) {
            for (int i = 0; i < 5; i++) {
                if (target[i] instanceof Grid) {
                    targetGrid = (Grid) target[i];
                    if (targetGrid.getX() != Board.startPathX || targetGrid.getY() != Board.startPathY) {
                        targetGrid.removeCardonGrid();
                    }
                }
            }
            Board targetBoard = (Board) target[5];
            targetBoard.calculateBoard();
            return true;
        } else {
            GameNotification.showAlertBoxErrorMessage("You have to aim at an existing path");
            return false;
        }
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        for (int i = 0; i < 5; i++) {
            if (target[i] == null) {
                // do nothing
            } else if (target[i] instanceof Grid) {
                Grid targetGrid = (Grid) target[i];
                Grid prevGrid = (Grid) undoExtraInformation[i];
                targetGrid.setCard(prevGrid.getCard());
            } else {
                GameNotification.showAlertBoxErrorMessage("That target is invalid");
            }
        }
    }
}
