package model;

import model.goalcard.GoalCard_Coal;
import model.goalcard.GoalCard_Goal;
import model.goalcard.GoalCard_Gold;

/**
 * Created by orlandok on 25/5/17.
 * *
 */
public class GoalCardFactory extends AbstractCardFactory {
  @Override
  ActionCard getActionCard(String cardType) {
    return null;
  }

  @Override
  PathCard getPathCard(String cardType, int rotation) {
    return null;
  }

  @Override
  GoalCard getGoalCard(String cardType) {
    switch(cardType) {
      case "GOAL_GOAL":
        return new GoalCard_Goal(true, cardType);
      case "GOAL_COAL_HIDDEN":
        return new GoalCard_Coal(true, cardType);
      case "GOAL_COAL_REVEALED":
        return new GoalCard_Coal(false, cardType);
      case "GOAL_GOLD_HIDDEN":
        return new GoalCard_Gold(true, cardType);
    }
    return null;
  }

  @Override
  PersonalCard getPersonalCard(String cardType) {
    return null;
  }
}
