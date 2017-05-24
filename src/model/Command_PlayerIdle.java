package model;

/**
 * @author David Limantoro s3503728 on 2017/05/23.
 */
public class Command_PlayerIdle extends CommandAbstract {
    public Command_PlayerIdle(int playerNumber, Card cardDrawnThisTurn, Object[] target) {
        super(playerNumber, null, cardDrawnThisTurn, target);
    }

    @Override
    public boolean doAction() {
        Player targetPlayer = (Player) getTarget()[0];
        int sickTurn = targetPlayer.getSickTurn();
        if (sickTurn > 1) {
            targetPlayer.setSickTurn(sickTurn - 1);
        }
        return true;
    }

    @Override
    public void undoAction(Object[] undoExtraInformation) {
        Player targetPlayer = (Player) getTarget()[0];
        Player targetPlayerBefore = (Player) undoExtraInformation[0];
        targetPlayer.setSickTurn(targetPlayerBefore.getSickTurn());
    }
}
