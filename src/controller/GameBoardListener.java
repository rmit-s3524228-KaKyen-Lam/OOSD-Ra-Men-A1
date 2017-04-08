package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Game;
import view.Notification;

/**
 * This is a class specifically dedicated for listening to click events on the game board GridPane
 *
 * @author David Limantoro s3503728
 */
public class GameBoardListener implements EventHandler<MouseEvent> {
    private int x;
    private int y;
    private Game game;

    /**
     * @param x    x location of the grid
     * @param y    y location of the grid
     * @param game The game entity
     */
    public GameBoardListener(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    /**
     * Handle a click button.
     *
     * If a card is already selected and it is a path card, then try to place the card.
     * Otherwise give the player a warning since it is an illegal move
     *
     * @param event MouseEvent related to the click
     */
    @Override
    public void handle(MouseEvent event) {
        if (game.getSelectedCard() != null) {
            game.placeCard(x, y);
        } else {
            Notification.showAlertBoxErrorMessage("Cannot play card: No card is currently selected");
        }
    }
}