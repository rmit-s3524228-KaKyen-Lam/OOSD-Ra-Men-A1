package model;

/**
 * @author David Limantoro (s3503728) on 5/18/2017.
 */
public abstract class AbstractCommand implements Command {

    private int playerNumber;
    private Card card;

    public AbstractCommand(int playerNumber, Card card) {
        this.playerNumber = playerNumber;
        this.card = card;
    }

    public abstract void doAction();

    public abstract void undoAction();

    public int getPlayerNumber() {
        return playerNumber;
    }

    public Card getCard() {
        return card;
    }
}
