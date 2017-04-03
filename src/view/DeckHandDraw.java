package view;

import controller.PlayerHandListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Card;
import model.Game;

import java.util.ArrayList;

/**
 * This class is responsible for drawing player's card hand and the label associated with it
 *
 * @author David Limantoro s3503728
 */
public class DeckHandDraw {

    private GridPane gridPlayerDeck;
    private Label playerLabel;
    private Game game;

    /**
     * Creates a draw class for player hand section
     *
     * @param gridPlayerDeck JavaFX GridPane object of player's hand
     * @param playerLabel    JavaFX Label object of player's label
     * @param game           The game object
     */
    public DeckHandDraw(GridPane gridPlayerDeck, Label playerLabel, Game game) {
        this.gridPlayerDeck = gridPlayerDeck;
        this.playerLabel = playerLabel;
        this.game = game;
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
