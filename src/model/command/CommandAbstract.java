package model.command;

import library.DeepCopier;
import model.card.Card;

import java.io.Serializable;

/**
 * @author David Limantoro (s3503728) on 5/18/2017.
 */
public abstract class CommandAbstract implements Command, Serializable {

    private int playerNumber;
    private Card cardToUse;
    private Card cardDrawnThisTurn;
    private Object[] target;

    public CommandAbstract(int playerNumber, Card cardToUse, Card cardDrawnThisTurn, Object[] target) {
        this.playerNumber = playerNumber;
        this.cardToUse = cardToUse;
        this.cardDrawnThisTurn = cardDrawnThisTurn;
        this.target = target;
    }

    public boolean doAction() {
        return cardToUse.cardAction(target);
    }

    public void undoAction(Object[] undoExtraInformation) {
        cardToUse.undoCardAction(target, undoExtraInformation);
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public Card getCardToUse() {
        return cardToUse;
    }

    public Card getCardDrawnThisTurn() {
        return cardDrawnThisTurn;
    }

    public Object[] getTarget() {
        return DeepCopier.copyArray(target);
    }
}
