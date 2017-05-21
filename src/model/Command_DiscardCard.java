package model;

/**
 * @author David Limantoro s3503728 on 2017/05/21.
 */
public class Command_DiscardCard extends CommandAbstract {

    public Command_DiscardCard(int playerNumber, Card cardToUse, Card cardDrawnThisTurn, Object[] target) {
        super(playerNumber, cardToUse, cardDrawnThisTurn, target);
    }

    @Override
    public boolean doAction() {
        if (getCardToUse() != null) {
            return true;
        }
        return false;
    }

    @Override
    public void undoAction(Object[] undoExtraInformation) {
        super.undoAction(undoExtraInformation);
    }
}
