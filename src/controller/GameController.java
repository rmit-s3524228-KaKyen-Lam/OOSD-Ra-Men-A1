package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.*;
import view.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This is the JavaFX controller class that is responsible for talking to
 * the gameLogic object (model) as well as talking to viewer
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
    @FXML
    private Button buttonChangeTheme;
    @FXML
    private Button buttonLoadGame;
    @FXML
    private Button buttonSaveGame;
    @FXML
    private Button buttonUndo;

    private static BoardDraw boardDraw;
    private static PlayerHandDraw playerHandDraw;
    public static Game game = new Game();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageFlyweight imageFlyweight = new ImageFlyweightImpl();
        boardDraw = new BoardDraw(gridGameBoard, boardLabel, game, imageFlyweight);
        playerHandDraw = new PlayerHandDraw(gridPlayerDeck, playerLabel, trashcanImageView, game, imageFlyweight);

        buttonChangeTheme.setOnMouseClicked(event -> {
            System.out.println("buttonChangeTheme");
        });

        buttonLoadGame.setOnMouseClicked(event -> {
            String filename = GameDialog.askUser("Load Game", "");
            if (!filename.equals(GameDialog.CANCEL_DEFAULT_VALUE_RETURNED)) {
                game.loadGame(filename);
            }
        });

        buttonSaveGame.setOnMouseClicked(event -> {
            String filename = GameDialog.askUser("Save Game", "");
            if (!filename.equals(GameDialog.CANCEL_DEFAULT_VALUE_RETURNED)) {
                game.saveGame(filename);
            }
        });

        buttonUndo.setOnMouseClicked(event -> {
            System.out.println("buttonUndo");
        });
    }

    /**
     * Change the Label of the board in the gameLogic window
     *
     * @param text Text to be placed on the label
     */
    public void changeBoardLabel(String text) {
        boardDraw.changeBoardLabel(text);
    }

    /**
     * Redraws the whole gameLogic board
     */
    public static void redrawGrid() {
        boardDraw.redrawBoard();
    }

    /**
     * Redraws only part of the gameLogic board and render it on gameLogic window
     *
     * @param x x coordinate of the board to be redrawn
     * @param y y coordinate of the board to be redrawn
     */
    public static void redrawGridXY(int x, int y) {
        boardDraw.redrawGrid(x, y);
    }

    /**
     * Change the player label in the gameLogic window and render it on gameLogic window
     *
     * @param playerNum The player number (before added by one) to be placed on the label
     */
    public static void changePlayerLabel(int playerNum, String role) {
        playerHandDraw.changePlayerLabel(playerNum, role);
    }

    /**
     * Redraws the deck of a player and render it on gameLogic window
     *
     * @param currentPlayerHand the hand of current player, in ArrayList of Card format
     */
    public static void redrawDeck(ArrayList<Card> currentPlayerHand) {
        playerHandDraw.redrawPlayerHand(currentPlayerHand);
    }
}
