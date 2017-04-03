package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This is the JavaFX controller class that is responsible for talking to
 * the game object (model) as well as talking to viewer
 *
 * @author David Limantoro s3503728
 */
public class GameController implements Initializable {

    @FXML
    private GridPane gridGameBoard;
    @FXML
    private GridPane gridPlayerDeck;
    @FXML
    private Label playerLabel;
    @FXML
    private Label boardLabel;

    private Grid[][] gameBoard;
    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Game();
        gameBoard = game.getBoard().getGrid();
        game.gameStart(this);

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
     * Change the player label in the game window and render it on game window
     *
     * @param playerNum The player number (before added by one) to be placed on the label
     */
    public void changePlayerLabel(int playerNum) {
        playerLabel.setText("Player " + (playerNum + 1) + "'s Hand");
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

    /**
     * Redraws the deck of a player and render it on game window
     *
     * @param currentPlayerHand the hand of current player, in ArrayList of Card format
     */
    public void redrawDeck(ArrayList<Card> currentPlayerHand) {
        for (int i = 0; i < currentPlayerHand.size(); i++) {
            ImageView iv = new ImageView(currentPlayerHand.get(i).getImageResource());
            gridPlayerDeck.add(iv, i, 0);
            iv.setOnMouseClicked(new PlayerHandListener(i, game));
        }
    }
}
