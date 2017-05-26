package model;

import java.io.Serializable;

/**
 * Abstract class to be used by all other classes that will be drawn or manifested on the board in the GUI itself.
 * <p>
 * This class in an example of the Single-Responsibility principle as it only has a single responsibility of serving as
 * a constructor.
 *
 * @author Fabio Monsalve s3585826
 */
public abstract class Drawable implements Serializable {

    private String imageResource;

    /**
     * Creates a drawable object with associated imageResource
     *
     * @param imageResource all objects that will appear on the board in the GUI will have a corresponding image
     */
    public Drawable(String imageResource) {
        this.imageResource = imageResource;
    }

    public String getImageResource() {
        return imageResource;
    }
}
