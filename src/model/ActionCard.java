package model;

/**
 * Abstract class for all the 6 different action cards
 */
public abstract class ActionCard extends Card {
    /**
     *
     * @param imageResource location of image
     * @param id All types of card have a unique id
     */
    public ActionCard(String imageResource, String id) {
        super(imageResource, id);
    }
}