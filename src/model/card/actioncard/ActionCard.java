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

    @Override
    public abstract boolean cardAction(Object[] target);


    @Override
    public abstract void undoCardAction(Object[] target, Object[] undoExtraInformation);
}