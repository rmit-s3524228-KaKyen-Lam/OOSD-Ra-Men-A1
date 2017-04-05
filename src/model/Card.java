package model;

/**
 * Abstract class for all other game cards
 *
 * @author Fabio Monsalve s3585826
 */
public abstract class Card extends Drawable{
    private String id;
    private Board board;
    private boolean discarded;

    /**
     *
     * @param imageResource location of image file for card
     * @param id every can will have and if whenn created to access and to ensure individuality
     */
    public Card(String imageResource, String id){
        super(imageResource);
        this.id = id;
    }

    /**
     * Method for actions different types of cards might have. E.g. Breaking tools
     */
    public abstract void cardAction();

    public String getId() {
        return id;
    }

}

