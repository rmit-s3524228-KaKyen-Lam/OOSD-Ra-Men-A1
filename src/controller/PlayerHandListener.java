package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Game;
import view.PlayerHandListenerCallback;
import view.alertWindow.GameNotification;

/**
 * This is a class specifically dedicated for listening to click events on player's hand GridPane
 *
 * @author David Limantoro s3503728
 */
public class PlayerHandListener implements EventHandler<MouseEvent> {
    private PlayerHandListenerCallback callback;
    private int cardNum;
    private Game game;

    /**
     * Creates a listener for the player hand GridPane
     *
     * @param cardNum The card position in the array
     * @param game    The gameLogic entity
     */
    public PlayerHandListener(int cardNum, Game game, PlayerHandListenerCallback callback) {
        this.cardNum = cardNum;
        this.game = game;
        this.callback = callback;
    }

    /**
     * Handle a click button.
     *
     * Clicking a card will make it "selected". Additionally, if the card is an action card then
     * ask the player what to do.
     *
     * @param event MouseEvent related to the click
     */
    @Override
    public void handle(MouseEvent event) {
        if (event.getButton().toString().equals("PRIMARY")) {
            game.setSelectedCard(cardNum);
            callback.highlightSelectedCard(cardNum);
        }
        if (event.getButton().toString().equals("SECONDARY")) {
            if (!game.rotateCard(cardNum)) {
                GameNotification.showAlertBoxErrorMessage("Cannot rotate non-path card");
            }
        }
    }
}