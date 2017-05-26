package model.command;

import library.DeepCopier;
import model.card.Card;

import java.io.Serializable;

/**
 * This is the abstract class that implements Command interface class.
 *
 * Concrete classes of command pattern should extend this class instead of implementing Command interface class directly
 *
 * @author David Limantoro (s3503728) on 5/18/2017.
 */
public abstract class CommandAbstract implements Command, Serializable {

    private int playerNumber;
    private Card cardToUse;
    private Card cardDrawnThisTurn;
    private Object[] target;

    /**
     * @param playerNumber      The player number who executes this command
     * @param cardToUse         Card used by the player
     * @param cardDrawnThisTurn Card drawn by the user at the end of previous turn
     * @param target            The target objects of cardToUse (e.g., the object of PathCard would be a Grid object)
     */
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
