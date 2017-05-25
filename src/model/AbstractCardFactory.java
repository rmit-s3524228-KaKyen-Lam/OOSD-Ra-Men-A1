package model;

/**
 * Created by orlandok on 25/5/17.
 * *
 */
public abstract  class AbstractCardFactory {

  abstract ActionCard getActionCard(String cardType);
  abstract PathCard getPathCard(String cardType, int rotation);
  abstract GoalCard getGoalCard(String cardType);
  abstract PersonalCard getPersonalCard(String cardType);
}
