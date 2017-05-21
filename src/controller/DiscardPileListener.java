package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Game;
import view.Notification;

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
     * @param game The gameLogic entity
     */
    public DiscardPileListener(Game game) {
        this.game = game;
    }

    /**
     * Handle a click button.
     *
     * Clicking on a discard pile will remove the card and moves the gameLogic to next turn.
     *
     * @param event MouseEvent related to the click
     */
    @Override
    public void handle(MouseEvent event) {
        if (game.getSelectedCard() != null) {
            game.nextTurn();
        } else {
            Notification.showAlertBoxErrorMessage("Cannot discard card: No card is currently selected");
        }
    }
}
