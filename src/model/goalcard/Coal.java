package model.goalcard;

import model.GoalCard;

/**
 * Goal card for coal
 *
 * @author Fabio Monsalve Duque s3585826
 *
 */
public class Coal extends GoalCard {

    /**
     * @param concealedImageResource location of image to indicate a concealed goal card
     * @param imageSource            location of image to indicate a revealed goal card
     * @param id
     */
    public Coal(String concealedImageResource, String imageSource, String id) {
        super(concealedImageResource, imageSource, id);
    }
}
