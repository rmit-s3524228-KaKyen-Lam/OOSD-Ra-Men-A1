package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Game;

/**
 * @author David Limantoro s3503728
 */
public class gameBoardListener implements EventHandler<MouseEvent> {
    private int x;
    private int y;
    private Game game;

    public gameBoardListener(int x, int y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    @Override
    public void handle(MouseEvent event) {
        game.placeCard(x, y);
        System.out.printf("You clicked game board at %d,%d \n", x + 1, y + 1);
    }
}