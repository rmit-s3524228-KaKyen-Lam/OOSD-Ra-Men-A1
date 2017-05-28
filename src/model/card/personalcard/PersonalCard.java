package model.card.personalcard;

import model.card.Card;

/**
 * Created by Ka Kyen Lam on 23/05/2017.
 */
public abstract class PersonalCard extends Card {

    /**
     * @param imageResource location of image file for card
     * @param id            id of the card
     */
    public PersonalCard(String imageResource, String id) {
        super(imageResource, id);
    }

    /**
     * Method for card actions
     *
     * Prerequisites:
     * - target contains only one Player object
     *
     * @param target Player object
     * @return true if cardAction is valid
     */
    @Override
    public abstract boolean cardAction(Object[] target);

    /**
     * Method for undoing card action
     *
     * Prerequisites:
     * - target contains one Player object
     * - undoExtraInformation contains one PLayer object
     *
     * @param target               current Player to be replaced
     * @param undoExtraInformation original Player prior to cardAction
     */
    @Override
    public abstract void undoCardAction(Object[] target, Object[] undoExtraInformation);


}
