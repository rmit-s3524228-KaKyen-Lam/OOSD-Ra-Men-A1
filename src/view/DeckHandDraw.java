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
 * Created by HP on 3/04/2017.
 */
public class DeckHandDraw {

    private GridPane gridPlayerDeck;
    private Label playerLabel;
    private Game game;

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
