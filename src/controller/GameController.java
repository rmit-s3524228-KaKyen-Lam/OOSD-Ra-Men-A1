package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.*;
import view.BoardDraw;
import view.PlayerHandDraw;

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
    private Label boardLabel;
    @FXML
    private GridPane gridPlayerDeck;
    @FXML
    private Label playerLabel;
    @FXML
    private ImageView trashcanImageView;

    private BoardDraw boardDraw;
    private PlayerHandDraw playerHandDraw;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Game game = new Game();
        boardDraw = new BoardDraw(gridGameBoard, boardLabel, game);
        playerHandDraw = new PlayerHandDraw(gridPlayerDeck, playerLabel, trashcanImageView, game);
        game.gameStart(this);
    }

    /**
     * Change the Label of the board in the game window
     *
     * @param text Text to be placed on the label
     */
    public void changeBoardLabel(String text) {
        boardDraw.changeBoardLabel(text);
    }

    /**
     * Redraws the whole game board
     */
    public void redrawGrid() {
        boardDraw.redrawBoard();
    }

    /**
     * Redraws only part of the game board and render it on game window
     *
     * @param x x coordinate of the board to be redrawn
     * @param y y coordinate of the board to be redrawn
     */
    public void redrawGridXY(int x, int y) {
        boardDraw.redrawGrid(x, y);
    }

    /**
     * Change the player label in the game window and render it on game window
     *
     * @param playerNum The player number (before added by one) to be placed on the label
     */
    public void changePlayerLabel(int playerNum, String role) {
        playerHandDraw.changePlayerLabel(playerNum, role);
    }

    /**
     * Redraws the deck of a player and render it on game window
     *
     * @param currentPlayerHand the hand of current player, in ArrayList of Card format
     */
    public void redrawDeck(ArrayList<Card> currentPlayerHand) {
        playerHandDraw.redrawPlayerHand(currentPlayerHand);
    }
}
