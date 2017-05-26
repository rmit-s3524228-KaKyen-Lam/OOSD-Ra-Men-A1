package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.*;
import model.card.Card;
import model.card.personalcard.PersonalCard;
import view.alertWindow.GameNotification;

/**
 * @author David Limantoro s3503728 on 2017/05/25.
 */
public class PlayerTargetListener implements EventHandler<MouseEvent> {
    private int playerNum;
    private Game game;

    /**
     * Creates a listener for the player hand GridPane
     *
     * @param playerNum The card position in the array
     * @param game      The gameLogic entity
     */
    public PlayerTargetListener(int playerNum, Game game) {
        this.playerNum = playerNum;
        this.game = game;
    }

    /**
     * Handle a click button.
     *
     * Clicking a card will make it "selected". Additionally, if the card is an action card then
     * ask the player what to do.
     *
     * @param event MouseEvent related to the click
     */
    @Override
    public void handle(MouseEvent event) {

        if (event.getButton().toString().equals("PRIMARY")) {
            Card selCard = game.getSelectedCard();
            if (selCard != null) {
                if (selCard instanceof PersonalCard) {
                    game.playPersonalCard(playerNum);
                } else {
                    GameNotification.showAlertBoxErrorMessage("Select personal card first");
                }
            } else {
                GameNotification.showAlertBoxErrorMessage("Cannot play card: No personal card is currently selected");
            }
        }
    }
}
