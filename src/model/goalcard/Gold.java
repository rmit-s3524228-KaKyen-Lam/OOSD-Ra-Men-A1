package model.goalcard;

import model.GoalCard;

/**
 * Goal card for gold
 *
 * @author Fabio Monsalve Duque s3585826
 *
 */
public class Gold extends GoalCard {

    /**
     * @param concealedImageResource location of image to indicate a concealed goal card
     * @param imageSource            location of image to indicate a revealed goal card
     * @param id
     */
    public Gold(String concealedImageResource, String imageSource, String id) {
        super(concealedImageResource, imageSource, id);
    }
}
