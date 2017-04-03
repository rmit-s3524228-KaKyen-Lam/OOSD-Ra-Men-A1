package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.ActionCard;
import model.Card;
import model.Game;

/**
 * This is a class specifically dedicated for listening to click events on player's hand
 *
 * @author David Limantoro s3503728
 */
public class PlayerHandListener implements EventHandler<MouseEvent> {
    private int cardNum;
    private Game game;

    public PlayerHandListener(int cardNum, Game game) {
        this.cardNum = cardNum;
        this.game = game;
    }

    /**
     * Handle a click button.
     * <p>
     * Clicking a card will make it "selected". Additionally, if the card is an action card then
     * ask the player what to do.
     *
     * @param event MouseEvent related to the click
     */
    @Override
    public void handle(MouseEvent event) {
        Card card = game.getPlayers()[game.getPlayerTurnNumber()].getHand().get(cardNum);
        game.setSelectedCard(card);
        if (card instanceof ActionCard) {
            // TODO Ask player whether they want to use the card on player or board (i.e. handle the action card)
        } else {
            // TODO highlight the card
        }
    }
}
