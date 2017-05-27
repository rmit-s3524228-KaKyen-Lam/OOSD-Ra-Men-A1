package model.command;

import model.card.Card;

/**
 * @author David Limantoro s3503728 on 2017/05/18.
 */
public interface Command {
    /**
     * Perform Command's action
     *
     * @return True if the action is successfully executed, false otherwise
     */
    boolean doAction();

    /**
     * Undoes Command's action
     *
     * @param undoExtraInformation Object arrays containing clone objects related to the action, before the action is executed
     */
    void undoAction(Object[] undoExtraInformation);

    int getPlayerNumber();

    Card getCardToUse();

    Card getCardDrawnThisTurn();

    Object[] getTarget();

    void setTarget(Object[] target);

}
