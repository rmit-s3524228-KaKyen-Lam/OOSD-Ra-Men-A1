package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.ActionCard;
import model.Card;
import model.Game;

/**
 * @author David Limantoro s3503728
 */
public class playerHandListener implements EventHandler<MouseEvent> {
    private int cardNum;
    private Game game;

    public playerHandListener(int cardNum, Game game) {
        this.cardNum = cardNum;
        this.game = game;
    }

    @Override
    public void handle(MouseEvent event) {
        Card card = game.getPlayers()[game.getPlayerTurnNumber()].getHand().get(cardNum);
        game.setSelectedCard(card);
        if (card instanceof ActionCard) {
            // TODO Ask player whether they want to use the card on player or board
            // game.handleActionCard(2);
        } else {
            // TODO highlight the card
        }
        System.out.printf("You clicked card number %d \n", cardNum);
    }
}
