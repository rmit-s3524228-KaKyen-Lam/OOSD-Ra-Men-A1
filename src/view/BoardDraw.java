package view;

import controller.GameBoardListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Card;
import model.Game;
import model.GoalCard;
import model.Grid;

/**
 * Created by HP on 3/04/2017.
 */
public class BoardDraw {
    private GridPane gridGameBoard;
    private Label boardLabel;
    private Game game;

    public BoardDraw(GridPane gridGameBoard, Label boardLabel, Game game) {
        this.gridGameBoard = gridGameBoard;
        this.boardLabel = boardLabel;
        this.game = game;
    }

    /**
     * Change the Label of the board in the game window
     *
     * @param text Text to be placed on the label
     */
    public void changeBoardLabel(String text) {
        boardLabel.setText(text);
    }

    /**
     * Redraws the whole game board
     */
    public void redrawGrid() {
        for (int i = 0; i < model.Board.GRID_MAX_WIDTH; i++) {
            for (int j = 0; j < model.Board.GRID_MAX_HEIGHT; j++) {
                redrawGridXY(i, j);
            }
        }
    }

    /**
     * Redraws only part of the game board and render it on game window
     *
     * @param x x coordinate of the board to be redrawn
     * @param y y coordinate of the board to be redrawn
     */
    public void redrawGridXY(int x, int y) {

        ImageView gd;
        Grid[][] gameBoard = game.getBoard().getGrid();
        Card currentGridCard = gameBoard[x][y].getCard();

        if (currentGridCard instanceof GoalCard) {
            if (((GoalCard) currentGridCard).isHidden()) {
                gd = new ImageView(((GoalCard) currentGridCard).getConcealedImageResource());
            } else {
                gd = new ImageView(currentGridCard.getImageResource());
            }
        } else {
            gd = new ImageView(currentGridCard.getImageResource());
        }

        gridGameBoard.add(gd, x, y);
        gd.setOnMouseClicked(new GameBoardListener(x, y, game));
    }
}
