package model;

import java.io.Serializable;

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
public abstract class Card extends Drawable implements Serializable {
    private String id;
    // private Board board; // This will be used for implementation of cardAction method.

    /**
     * @param imageResource location of image file for card
     */
    public Card(String imageResource) {
        super(imageResource);

    }

    /**
     * Method for actions different types of cards might have. E.g. Breaking tools
     */
    public abstract boolean cardAction(Object[] target);

    public abstract void undoCardAction(Object[] target, Object[] undoExtraInformation);

    public String getId() {
        return id;
    }

}

