package model;

/**
 * Created by Ka Kyen Lam on 23/05/2017.
 */
public abstract class PersonalCard extends Card{

    /**
     * @param imageResource location of image file for card
     * @param id
     */
    public PersonalCard(String imageResource, String id) {
        super(imageResource, id);
    }

    /**
     * Method for actions different types of cards might have. E.g. Breaking tools
     *
     * @param target
     */
    @Override
    public abstract boolean cardAction(Object[] target);


    @Override
    public abstract void undoCardAction(Object[] target, Object[] undoExtraInformation);


}
