package model.factory;

import model.card.actioncard.ActionCard;
import model.card.goalcard.GoalCard;
import model.card.pathcard.PathCard;
import model.card.personalcard.PersonalCard;

/**
 * Abstract Factory
 *
 * @author Fabio Monsalve s3585826
 */
public abstract class AbstractCardFactory {

    // Methods for all 4 card factories, all the same except for PathCard which
    // requires a rotation value
    public abstract ActionCard getActionCard(String cardType);

    public abstract PathCard getPathCard(String cardType, int rotation);

    public abstract GoalCard getGoalCard(String cardType);

    public abstract PersonalCard getPersonalCard(String cardType);
}
