package model.card.actioncard;

import model.*;
import model.board.Board;
import model.board.Grid;
import model.card.pathcard.PathCard;

/**
 * Action card for the ability to rotate an existing path card on board
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class ActionCard_Rotate_Path_Card extends ActionCard {
    public ActionCard_Rotate_Path_Card(String id) {
        super("resources/Action_Rotate.png", id);
    }

    @Override
    public boolean cardAction(Object[] target) {
        //show all possible option and listen for response
        Grid targetGrid = (Grid) target[0];
        PathCard currentCard = (PathCard) targetGrid.getCard();
        targetGrid.setCard((PathCard) CardFlyweight.getCard(currentCard.getId(), currentCard.getRotateVal() + 1));
        Board targetBoard = (Board) target[5];
        targetBoard.calculateBoard();
        return true;
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Grid targetGrid = (Grid) target[0];
        Grid prevGrid = (Grid) undoExtraInformation[0];
        targetGrid.setCard(prevGrid.getCard());
    }
}
