package view;

import controller.GameBoardListener;
import controller.LogicChecker;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.*;
import model.pathcard.PathCard_Empty;

/**
 * This class is responsible for drawing the board and the label associated with it
 *
 * @author David Limantoro s3503728
 */
public class BoardDraw {
    private GridPane gridGameBoard;
    private Label boardLabel;
    private Game game;
    private ImageView[][] images;

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
        images = new ImageView[model.Board.GRID_MAX_WIDTH][Board.GRID_MAX_HEIGHT];
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

        images[x][y] = imageToDrawOnGrid;

        // TODO Create dedicated class for these handlers and put it in the controller package.
        imageToDrawOnGrid.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Card selectedCard = game.getSelectedCard();
                if (selectedCard != null && selectedCard instanceof PathCard) {
                    if (game.getBoard().getGridAtLocation(x, y).getCard() instanceof PathCard_Empty) {
                        if (LogicChecker.checkIfValid((PathCard) selectedCard, x, y)) {
                            images[x][y].setEffect(ImageViewTinter.greenTint);
                            return;
                        }
                    }
                    images[x][y].setEffect(ImageViewTinter.redTint);
                }
            }
        });

        imageToDrawOnGrid.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                images[x][y].setEffect(ImageViewTinter.removeTint);
            }
        });

        gridGameBoard.add(imageToDrawOnGrid, x, y);
        imageToDrawOnGrid.setOnMouseClicked(new GameBoardListener(x, y, game));
    }
}
