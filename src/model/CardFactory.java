package model;

import model.actioncard.*;
import model.goalcard.GoalCard_Coal;
import model.goalcard.GoalCard_Goal;
import model.goalcard.GoalCard_Gold;
import model.pathcard.*;

public class CardFactory {

  public Card makeCard (String cardType, String id){
    Card card = null;

    if(cardType.equals("T_SHAPE"))
      return card = new PathCard_T(id);
    else if(cardType.equals("T_SHAPE_DEAD"))
      return card = new PathCard_T_Dead(id);
    else if(cardType.equals("LINE_SHAPE"))
      return card = new PathCard_Line(id);
    else if(cardType.equals("LINE_SHAPE_DEAD"))
      return card = new PathCard_Line_Dead(id);
    else if(cardType.equals("CROSS_SHAPE"))
      return card = new PathCard_Cross(id);
    else if(cardType.equals("CROSS_SHAPE_DEAD"))
      return card = new PathCard_Cross_Dead(id);
    else if(cardType.equals("L_SHAPE"))
      return card = new PathCard_Line(id);
    else if(cardType.equals("L_SHAPE DEAD"))
      return card = new PathCard_Line_Dead(id);
    else if(cardType.equals("DEAD"))
      return card = new PathCard_DeadEnd(id);
    else if(cardType.equals("ADD_PATH"))
      return card = new ActionCard_Add_Path(id);
    else if(cardType.equals("CLEAN_PATH"))
      return card = new ActionCard_Clean_Path_Card(id);
    else if(cardType.equals("CORRUPT_PATH"))
      return card = new ActionCard_Corrupt_Path_Card(id);
    else if(cardType.equals("DESTROY_M_PATHS"))
      return card = new ActionCard_Destroy_Multiple_Paths(id);
    else if(cardType.equals("DESTROY_PATH"))
      return card = new ActionCard_Destroy_Path_Card(id);
    else if(cardType.equals("REMOVE_PATH"))
      return card = new ActionCard_Remove_Path(id);
    else if(cardType.equals("ROTATE_PATH"))
      return card = new ActionCard_Rotate_Path_Card(id);
    else if(cardType.equals("SWAP_PATH"))
      return card = new ActionCard_Swap_Path_Cards(id);
    else if(cardType.equals("COAL"))
      return card = new GoalCard_Coal(id);
    else if(cardType.equals("GOAL"))
      return card = new GoalCard_Goal(id);
    else if(cardType.equals("GOLD"))
      return card = new GoalCard_Gold(id);
    else
      return null;
  }
}