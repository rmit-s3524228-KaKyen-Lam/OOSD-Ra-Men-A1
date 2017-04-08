package view;

import controller.GameBoardListener;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Card;
import model.Game;
import model.GoalCard;
import model.Grid;

/**
 * This class is responsible for drawing the board and the label associated with it
 *
 * @author David Limantoro s3503728
 */
public class BoardDraw {
    private GridPane gridGameBoard;
    private Label boardLabel;
    private Game game;

    /**
     * Creates a draw class for board section
     *
     * @param gridGameBoard JavaFX GridPane object of the game board
     * @param boardLabel    JavaFX Label object of board label
     * @param game          The game object
     */
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
    public void redrawBoard() {
        for (int i = 0; i < model.Board.GRID_MAX_WIDTH; i++) {
            for (int j = 0; j < model.Board.GRID_MAX_HEIGHT; j++) {
                redrawGrid(i, j);
            }
        }
    }

    /**
     * Redraws only part of the game board and render it on game window
     *
     * precondition, x must not exceed the board width, and y must not exceed the board height
     *
     * @param x x coordinate of the board to be redrawn
     * @param y y coordinate of the board to be redrawn
     */
    public void redrawGrid(int x, int y) {

        ImageView imageToDrawOnGrid;
        Grid[][] gameBoard = game.getBoard().getGrid();
        Card currentGridCard = gameBoard[x][y].getCard();

        if (currentGridCard instanceof GoalCard) {
            if (((GoalCard) currentGridCard).isHidden()) {
                imageToDrawOnGrid = new ImageView(((GoalCard) currentGridCard).getConcealedImageResource());
            } else {
                imageToDrawOnGrid = new ImageView(currentGridCard.getImageResource());
            }
        } else {
            imageToDrawOnGrid = new ImageView(currentGridCard.getImageResource());
        }

        gridGameBoard.add(imageToDrawOnGrid, x, y);
        imageToDrawOnGrid.setOnMouseClicked(new GameBoardListener(x, y, game));
    }
}
