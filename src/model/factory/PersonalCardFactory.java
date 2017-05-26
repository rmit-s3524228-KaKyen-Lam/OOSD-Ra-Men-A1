package model.factory;

import model.card.actioncard.ActionCard;
import model.card.goalcard.GoalCard;
import model.card.pathcard.PathCard;
import model.card.personalcard.*;

/**
 * Created by orlandok on 25/5/17.
 * *
 */
public class PersonalCardFactory extends AbstractCardFactory {
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
        return null;
    }

    @Override
    public PersonalCard getPersonalCard(String cardType) {
        switch (cardType) {
            case "PERSONAL_BREAK_LANTERN":
                return new PersonalCard_Break_Lantern(cardType);
            case "PERSONAL_BREAK_PICKAXE":
                return new PersonalCard_Break_Pickaxe(cardType);
            case "PERSONAL_FIX_LANTERN":
                return new PersonalCard_Fix_Lantern(cardType);
            case "PERSONAL_FIX_PICKAXE":
                return new PersonalCard_Fix_Pickaxe(cardType);
            case "PERSONAL_SICK":
                return new PersonalCard_Sick(cardType);
            case "PERSONAL_SWAP_ROLE":
                return new PersonalCard_Swap_Role(cardType);
        }
        return null;
    }
}
