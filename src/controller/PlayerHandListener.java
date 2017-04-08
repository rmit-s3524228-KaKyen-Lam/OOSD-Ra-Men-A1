package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.ActionCard;
import model.Card;
import model.Game;

/**
 * This is a class specifically dedicated for listening to click events on player's hand GridPane
 *
 * @author David Limantoro s3503728
 */
public class PlayerHandListener implements EventHandler<MouseEvent> {
    private int cardNum;
    private Game game;

    /**
     * Creates a listener for the player hand GridPane
     *
     * @param cardNum The card position in the array
     * @param game    The game entity
     */
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
        game.setSelectedCard(cardNum);
        // TODO highlight the card
    }
}