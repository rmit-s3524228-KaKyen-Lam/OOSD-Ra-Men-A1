package model;

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
    return null;
  }

  @Override
  PersonalCard getPersonalCard(String cardType) {
    return null;
  }
}
