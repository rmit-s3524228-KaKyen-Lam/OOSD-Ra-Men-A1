package view;

import controller.DiscardPileListener;
import controller.PlayerHandListener;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Player;
import model.card.Card;
import model.Game;
import model.card.pathcard.PathCard;

import java.util.ArrayList;

/**
 * This class is responsible for drawing player's card hand and the label associated with it
 *
 * @author David Limantoro s3503728
 */
public class PlayerHandDraw {

    private GridPane gridPlayerDeck;
    private Label playerLabel;
    private Game game;
    private int selectedCardIndex;
    private ImageView[] images;
    private ImageFlyweight imageFlyweight;

    /**
     * Creates a draw class for player hand section
     *
     * @param gridPlayerDeck JavaFX GridPane object of player's hand
     * @param playerLabel    JavaFX Label object of player's label
     * @param game           The game object
     */
    public PlayerHandDraw(GridPane gridPlayerDeck, Label playerLabel, ImageView trashcanImageView, Game game, ImageFlyweight imageFlyweight) {
        this.gridPlayerDeck = gridPlayerDeck;
        this.playerLabel = playerLabel;
        this.game = game;
        selectedCardIndex = -1;
        this.imageFlyweight = imageFlyweight;

        trashcanImageView.setOnMouseClicked(new DiscardPileListener(this.game));
    }

    /**
     * Change the player label in the game window and render it on game window
     *
     * @param playerNum The player number (before added by one) to be placed on the label
     */
    public void changePlayerLabel(int playerNum, String role, String score) {
        playerLabel.setText("Player " + (playerNum + 1) + "'s Hand (" + role + ", score: " + score + ")");
    }

    /**
     * Redraws the deck of a player and render it on game window
     *
     * @param currentPlayerHand the hand of current player, in ArrayList of Card format
     */
    public void redrawPlayerHand(ArrayList<Card> currentPlayerHand) {
        // Remove previous player's hand ImageViews from gridPlayerDeck
        if (images != null) {
            for (int handIndex = 0; handIndex < Player.HAND_LIMIT; handIndex++) {
                gridPlayerDeck.getChildren().remove(images[handIndex]);
            }
        }

        // Draw current player's hand
        images = new ImageView[Player.HAND_LIMIT];
        for (int handIndex = 0; handIndex < Player.HAND_LIMIT; handIndex++) {
            try {
                ImageView iv = new ImageView(imageFlyweight.requestImage(currentPlayerHand.get(handIndex)));
                if (currentPlayerHand.get(handIndex) instanceof PathCard) {
                    int rotateVal = 0;
                    for (int i = 0; i < ((PathCard) currentPlayerHand.get(handIndex)).getRotateVal(); i++) {
                        rotateVal += 90;
                    }
                    iv.setRotate(rotateVal);
                }
                images[handIndex] = iv;

                /*
                Set click listener that allows Player's selected card to be highlighted
                and remove previously selected card's highlight.
                 */
                iv.setOnMouseClicked(new PlayerHandListener(handIndex, game, (cardNum) -> {
                    if (selectedCardIndex != -1) {
                        if (selectedCardIndex < images.length) {
                            ImageView imageViewToReset = images[selectedCardIndex];
                            imageViewToReset.setEffect(ImageViewTinter.getInstance().noTint);
                        }
                    }
                    iv.setEffect(ImageViewTinter.getInstance().grayToYellowTint);
                    selectedCardIndex = cardNum;
                }));
                gridPlayerDeck.add(iv, handIndex, 0);

            } catch (IndexOutOfBoundsException e) {
                ImageView iv = new ImageView("resources/Blank_Card.png");
                images[handIndex] = iv;
                gridPlayerDeck.add(iv, handIndex, 0);
            }
        }
        if (selectedCardIndex > -1 && game.getSelectedCard() != null) {
            game.setSelectedCard(selectedCardIndex);
            images[selectedCardIndex].setEffect(ImageViewTinter.getInstance().grayToYellowTint);
        }
    }
}
