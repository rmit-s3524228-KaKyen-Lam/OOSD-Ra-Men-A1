package model.goalcard;

import model.GoalCard;

/**
 * Goal card for gold
 *
 * @author Fabio Monsalve Duque s3585826
 *
 */
public class GoalCard_Gold extends GoalCard {

    /**
     * @param concealedImageResource location of image to indicate a concealed goal card
     * @param imageSource            location of image to indicate a revealed goal card
     * @param id
     */
    public GoalCard_Gold(String concealedImageResource, String imageSource, String id) {
        super(concealedImageResource, imageSource, id);
    }
}
