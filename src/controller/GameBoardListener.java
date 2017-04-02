package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Game;

/**
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

    @Override
    public void handle(MouseEvent event) {
        if (game.placeCard(x, y)) {
            game.nextTurn();
        } else {
            System.out.println("Sorry that's an invalid move, I'm afraid");
        }
        System.out.printf("You clicked game board at %d,%d \n", x + 1, y + 1);
    }
}