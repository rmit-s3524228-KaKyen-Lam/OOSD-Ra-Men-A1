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
public class DiscardPileListener implements EventHandler<MouseEvent> {
    private Game game;

    /**
     * Creates a listener for the discard pile ImageView
     *
     * @param game The game entity
     */
    public DiscardPileListener(Game game) {
        this.game = game;
    }

    /**
     * Handle a click button.
     * <p>
     * Clicking on a discard pile will remove the card and moves the game to next turn.
     *
     * @param event MouseEvent related to the click
     */
    @Override
    public void handle(MouseEvent event) {
        if (game.getSelectedCard() != null) {
            game.nextTurn(true);
        } else {
            game.showAlertBoxErrorMessage("Cannot discard card: No card is currently selected");
        }
    }
}
