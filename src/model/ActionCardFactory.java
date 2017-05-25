package model;

import model.actioncard.*;

/**
 * Created by orlandok on 25/5/17.
 * *
 */
public class ActionCardFactory extends AbstractCardFactory {


  @Override
  ActionCard getActionCard(String cardType) {
    switch (cardType) {
      case "ACTION_ADD_PATH":
        return new ActionCard_Add_Path(cardType);
      case "ACTION_CLEAN_PATH":
        return new ActionCard_Clean_Path_Card(cardType);
      case "ACTION_CORRUPT_PATH":
        return new ActionCard_Corrupt_Path_Card(cardType);
      case "ACTION_DESTROY_M_PATHS":
        return new ActionCard_Destroy_Multiple_Paths(cardType);
      case "ACTION_DESTROY_PATH":
        return new ActionCard_Destroy_Path_Card(cardType);
      case "ACTION_REMOVE_PATH":
        return new ActionCard_Remove_Path(cardType);
      case "ACTION_ROTATE_PATH":
        return new ActionCard_Rotate_Path_Card(cardType);
    }
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
