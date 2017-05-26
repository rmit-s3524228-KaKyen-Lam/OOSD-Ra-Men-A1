package model.factory;

import model.card.actioncard.ActionCard;
import model.card.goalcard.GoalCard;
import model.card.goalcard.GoalCard_Coal;
import model.card.goalcard.GoalCard_Goal;
import model.card.goalcard.GoalCard_Gold;
import model.card.pathcard.PathCard;
import model.card.personalcard.PersonalCard;
import model.factory.AbstractCardFactory;

/**
 * Created by orlandok on 25/5/17.
 * *
 */
public class GoalCardFactory extends AbstractCardFactory {
    @Override
    public ActionCard getActionCard(String cardType) {
        return null;
    }

    @Override
    public PathCard getPathCard(String cardType, int rotation) {
        return null;
    }

    @Override
    public GoalCard getGoalCard(String cardType) {
        switch (cardType) {
            case "GOAL_GOAL":
                return new GoalCard_Goal(true, cardType);
            case "GOAL_COAL_HIDDEN":
                return new GoalCard_Coal(true, cardType);
            case "GOAL_COAL_REVEALED":
                return new GoalCard_Coal(false, cardType);
            case "GOAL_GOLD_HIDDEN":
                return new GoalCard_Gold(true, cardType);
            case "GOAL_GOLD_REVEALED":
                return new GoalCard_Gold(false, cardType);
        }
        return null;
    }

    @Override
    public PersonalCard getPersonalCard(String cardType) {
        return null;
    }
}
