package view;

import controller.GameBoardListener;
import controller.LogicCheckerBridge;
import controller.PlayerTargetListener;
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
    private GridPane[] gridTargets;
    private ImageFlyweight imageFlyweight;
    private Label[] labelTargets;

    /**
     * Creates a draw class for board section
     *
     * @param gridGameBoard JavaFX GridPane object of the game board
     * @param boardLabel    JavaFX Label object of board label
     * @param game          The game object
     */
    public BoardDraw(GridPane gridGameBoard, Label boardLabel, GridPane[] gridTargets, Label[] labelTargets,
                     Game game, ImageFlyweight imageFlyweight) {
        this.gridGameBoard = gridGameBoard;
        this.boardLabel = boardLabel;
        this.game = game;
        this.imageFlyweight = imageFlyweight;
        this.gridTargets = gridTargets;
        this.labelTargets = labelTargets;
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
    public void redrawGridXY() {
        images = new ImageView[model.Board.gridMaxWidth][Board.gridMaxHeight];
        for (int i = 0; i < model.Board.gridMaxWidth; i++) {
            for (int j = 0; j < model.Board.gridMaxHeight; j++) {
                redrawGridXY(i, j);
            }
        }
    }

    public void redrawUsers() {
        int currentPlayerNumber = game.getPlayerTurnNumber();
        int drawCount = 0, playerIndex = 0;
        while (drawCount < 3) {
            if (playerIndex != currentPlayerNumber) {
                Player player = game.getPlayers()[playerIndex];
                ImageView imageView = new ImageView(player.getImageResource());
                imageView.setOnMouseClicked(new PlayerTargetListener(playerIndex, game));
                gridTargets[drawCount].add(imageView, 0, 0);

                StringBuilder playerLabel = new StringBuilder();
                playerLabel.append("Player " + (playerIndex + 1) + " ");
                playerLabel.append(player.getRole() + "\n");

                StringBuilder status = new StringBuilder();
                status.append("(");
                if (player.getSickTurn() > 0) {
                    status.append("Sick for " + player.getSickTurn() + " turn, ");
                }
                if (player.getBrokenTool().size() > 0) {
                    for (int i = 0; i < player.getBrokenTool().size(); i++) {
                        if (i > 0) {
                            status.append(",");
                        }
                        status.append(player.getBrokenTool().get(i) + " broken");
                    }
                } else {
                    status.append("No broken tool");
                }
                status.append(")");

                labelTargets[drawCount].setText(playerLabel.toString() + status.toString());
                drawCount++;
            }
            playerIndex++;
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
    public void redrawGridXY(int x, int y) {

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
                            images[x][y].setEffect(ImageViewTinter.getInstance().blueToGreenTint);
                            return;
                        } else {
                            images[x][y].setEffect(ImageViewTinter.getInstance().blueToRedTint);
                            return;
                        }
                    }
                    images[x][y].setEffect(ImageViewTinter.getInstance().grayToRedTint);
                }
            }
        });

        imageToDrawOnGrid.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                drawNormal(images[x][y], gameBoard[x][y]);
            }
        });

        gridGameBoard.add(imageToDrawOnGrid, x, y);
        imageToDrawOnGrid.setOnMouseClicked(new GameBoardListener(x, y, game));
    }

    private void drawNormal(ImageView imageView, Grid grid) {
        if (grid.isDisabled()) {
            imageView.setEffect(ImageViewTinter.getInstance().disabledTint);
        } else if ((!(grid.getCard() instanceof PathCard_Empty) && grid.getCard() instanceof PathCard && !grid.isConnectedToMain())) {
            imageView.setEffect(ImageViewTinter.getInstance().unconnectedTint);
        } else {
            imageView.setEffect(ImageViewTinter.getInstance().removeTint);
        }
    }
}
