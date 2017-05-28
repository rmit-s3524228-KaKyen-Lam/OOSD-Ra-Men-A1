package model.card.actioncard;

import model.card.Card;

/**
 * Abstract class for all the 6 different action cards
 */
public abstract class ActionCard extends Card {
    /**
     * @param imageResource location of image
     * @param id            id of the card
     */
    public ActionCard(String imageResource, String id) {
        super(imageResource, id);
    }

    /**
     * Method for card actions
     *
     * Prerequisites:
     * - target first five element contains Grid object (current grid and west, north, east and south neighboring grid)
     * - target sixth element contains Board object where the first five grids are part of
     *
     * @param target contains board and grid location
     * @return true if cardAction is valid
     */
    @Override
    public abstract boolean cardAction(Object[] target);

    /**
     * Method for undoing card action
     *
     * Prerequisites:
     * - target contains five Grid object
     * - undoExtraInformation contains five Grid object
     *
     * @param target               current grids to be replaced
     * @param undoExtraInformation original grids prior to cardAction
     */
    @Override
    public abstract void undoCardAction(Object[] target, Object[] undoExtraInformation);
}