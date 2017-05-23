package view;

import controller.GameBoardListener;
import controller.LogicCheckerBridge;
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
    private ImageFlyweight imageFlyweight;

    /**
     * Creates a draw class for board section
     *
     * @param gridGameBoard JavaFX GridPane object of the game board
     * @param boardLabel    JavaFX Label object of board label
     * @param game          The game object
     */
    public BoardDraw(GridPane gridGameBoard, Label boardLabel, Game game, ImageFlyweight imageFlyweight) {
        this.gridGameBoard = gridGameBoard;
        this.boardLabel = boardLabel;
        this.game = game;
        this.imageFlyweight = imageFlyweight;
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
        images = new ImageView[model.Board.gridMaxWidth][Board.gridMaxHeight];
        for (int i = 0; i < model.Board.gridMaxWidth; i++) {
            for (int j = 0; j < model.Board.gridMaxHeight; j++) {
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

        imageToDrawOnGrid = new ImageView(imageFlyweight.requestImage(currentGridCard));
        if (currentGridCard instanceof PathCard) {
            int rotateVal = 0;
            for (int i = 0; i < ((PathCard) currentGridCard).getRotateVal(); i++) {
                rotateVal += 90;
            }
            imageToDrawOnGrid.setRotate(rotateVal);
        }
        drawNormal(imageToDrawOnGrid, gameBoard[x][y]);
        images[x][y] = imageToDrawOnGrid;

        imageToDrawOnGrid.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Card selectedCard = game.getSelectedCard();
                if (selectedCard != null && selectedCard instanceof PathCard) {
                    if (game.getBoard().getGridAtLocation(x, y).getCard() instanceof PathCard_Empty) {
                        if (LogicCheckerBridge.checkIfValid((PathCard) selectedCard, x, y)) {
                            images[x][y].setEffect(ImageViewTinter.blueToGreenTint);
                            return;
                        } else {
                            images[x][y].setEffect(ImageViewTinter.blueToRedTint);
                            return;
                        }
                    }
                    images[x][y].setEffect(ImageViewTinter.grayToRedTint);
                }
            }
        });

        imageToDrawOnGrid.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                drawNormal(images[x][y], gameBoard[x][y]);
//                if (gameBoard[x][y].isDisabled()) {
//                    images[x][y].setEffect(ImageViewTinter.darkTint);
//                } else {
//                    images[x][y].setEffect(ImageViewTinter.removeTint);
//                }
            }
        });

        gridGameBoard.add(imageToDrawOnGrid, x, y);
        imageToDrawOnGrid.setOnMouseClicked(new GameBoardListener(x, y, game));
    }

    private void drawNormal(ImageView imageView, Grid grid) {
        if (grid.isDisabled()) {
            imageView.setEffect(ImageViewTinter.disabledTint);
        } else if ((!(grid.getCard() instanceof PathCard_Empty) && grid.getCard() instanceof PathCard && !grid.isConnectedToMain())) {
            imageView.setEffect(ImageViewTinter.unconnectedTint);
        } else {
            imageView.setEffect(ImageViewTinter.removeTint);
        }
    }
}
