package model.goalcard;

import model.GoalCard;

/**
 * Goal card for coal
 *
 * @author Fabio Monsalve Duque s3585826
 *
 */
public class GoalCard_Coal extends GoalCard {

    /**
     * @param concealedImageResource location of image to indicate a concealed goal card
     * @param imageSource            location of image to indicate a revealed goal card
     * @param id
     */
    public GoalCard_Coal(String concealedImageResource, String imageSource, String id) {
        super(concealedImageResource, imageSource, id);
    }
}
