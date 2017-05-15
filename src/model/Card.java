package model;

/**
 * Abstract class for all other game cards
 *
 * This class in an example of the Single-Responsibility principle as it only has a single responsibility
 *
 * This class is also an example of the Open-Closed principle which allows for future additions of new types of Cards
 *
 * This class is also an example of the Dependency Inversion Principle as the dependence of the Card types is abstract
 * and not concrete
 *
 * @author Fabio Monsalve Duque s3585826
 */
public abstract class Card extends Drawable {
    private String id;
    // private Board board; // This will be used for implementation of cardAction method.

    /**
     * @param imageResource location of image file for card
     * @param id            every can will have and if when created to access and to ensure individuality
     */
    public Card(String imageResource, String id) {
        super(imageResource);
        this.id = id;
m    }

    /**
     * Method for actions different types of cards might have. E.g. Breaking tools
     */
    public abstract void cardAction();

    public String getId() {
        return id;
    }

}

