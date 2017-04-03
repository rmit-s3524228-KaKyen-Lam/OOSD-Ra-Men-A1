package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Game;

/**
 * This is a class specifically dedicated for listening to click events on the game board
 *
 * @author David Limantoro s3503728
 */
public class GameBoardListener implements EventHandler<MouseEvent> {
    private int x;
    private int y;
    private Game game;

    public GameBoardListener(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    /**
     * Handle a click button.
     * <p>
     * If a card is already selected and it is a path card, then try to place the card.
     * Otherwise give the player a warning since it is an illegal move
     *
     * @param event MouseEvent related to the click
     */
    @Override
    public void handle(MouseEvent event) {
        if (!game.placeCard(x, y)) {
            System.out.println("Sorry, I'm afraid that is an invalid move");
        }
    }
}