package model.goalcard;

import model.GoalCard;

/**
 * Goal card for gold
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class GoalCard_Gold extends GoalCard {

    public GoalCard_Gold(boolean hidden, String id) {
        super("resources/Goal.png", "resources/Gold.png", hidden, id);
    }
}
