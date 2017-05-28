package model.card.actioncard;

import model.card.Card;

/**
 * Abstract class for all the 6 different action cards
 */
public abstract class ActionCard extends Card {
    /**
     * @param imageResource location of image
     */
    public ActionCard(String imageResource, String id) {
        super(imageResource, id);
    }

    // returns true if action was successful
    @Override
    public abstract boolean cardAction(Object[] target);

    // Undoes last action with current target and previous target
    @Override
    public abstract void undoCardAction(Object[] target, Object[] undoExtraInformation);
}