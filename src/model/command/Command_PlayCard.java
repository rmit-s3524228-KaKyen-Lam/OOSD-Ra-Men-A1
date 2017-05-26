package model.command;

import model.card.Card;

/**
 * @author David Limantoro s3503728 on 2017/05/20.
 */
public class Command_PlayCard extends CommandAbstract {
    public Command_PlayCard(int playerNumber, Card cardToUse, Card cardDrawnThisTurn, Object[] target) {
        super(playerNumber, cardToUse, cardDrawnThisTurn, target);
    }
}
