package model.command;

import model.card.Card;

/**
 * @author David Limantoro s3503728 on 2017/05/18.
 */
public interface Command {
    boolean doAction();

    void undoAction(Object[] undoExtraInformation);

    int getPlayerNumber();

    Card getCardToUse();

    public Card getCardDrawnThisTurn();

    Object[] getTarget();

}
