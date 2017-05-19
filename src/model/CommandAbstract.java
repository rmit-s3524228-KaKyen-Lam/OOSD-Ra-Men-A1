package model;

/**
 * @author David Limantoro (s3503728) on 5/18/2017.
 */
public abstract class CommandAbstract implements Command {

    private int playerNumber;
    private Card card;
    private Object[] target;

    public CommandAbstract(int playerNumber, Card card, Object[] target) {
        this.playerNumber = playerNumber;
        this.card = card;
        this.target = target;
    }

    public void doAction() {
        card.cardAction(target);
    }

    public void undoAction(Object[] undoExtraInformation) {
        card.undoCardAction(target, undoExtraInformation);
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public Card getCard() {
        return card;
    }
}
