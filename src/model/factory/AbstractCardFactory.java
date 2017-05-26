package model.factory;

import model.card.actioncard.ActionCard;
import model.card.goalcard.GoalCard;
import model.card.pathcard.PathCard;
import model.card.personalcard.PersonalCard;

/**
 * Created by orlandok on 25/5/17.
 * *
 */
public abstract class AbstractCardFactory {

    public abstract ActionCard getActionCard(String cardType);

    public abstract PathCard getPathCard(String cardType, int rotation);

    public abstract GoalCard getGoalCard(String cardType);

    public abstract PersonalCard getPersonalCard(String cardType);
}
