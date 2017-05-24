package model;

import model.actioncard.*;
import model.goalcard.GoalCard_Coal;
import model.goalcard.GoalCard_Goal;
import model.goalcard.GoalCard_Gold;
import model.pathcard.*;
import model.personalcard.*;

/**
 * Factory class used to make cards when they are needed in conjunction with the
 * flyweight pattern.
 *
 * @author Fabio Monsalve s3585826
 */
class CardFactory {

    /*
    Switch which uses passes card objects to the flyweight class.
    Also rotates cards which need rotation.
     */
    Card makeCard(String cardType, int rotation) {
        switch (cardType) {
            case "T_SHAPE":
                PathCard_T T_Card = new PathCard_T(cardType);
                if (rotation != 0)
                    return rotateCard(T_Card, rotation);
                else
                    return T_Card;
            case "T_SHAPE_DEAD":
                PathCard_T_Dead T_Dead_Card = new PathCard_T_Dead(cardType);
                if (rotation != 0)
                    return rotateCard(T_Dead_Card, rotation);
                else
                    return T_Dead_Card;
            case "LINE_SHAPE":
                PathCard_Line Line_Card = new PathCard_Line(cardType);
                if (rotation != 0)
                    return rotateCard(Line_Card, rotation);
                else
                    return Line_Card;
            case "LINE_SHAPE_DEAD":
                PathCard_Line_Dead Line_Dead_Card = new PathCard_Line_Dead(cardType);
                if (rotation != 0)
                    return rotateCard(Line_Dead_Card, rotation);
                else
                    return Line_Dead_Card;
            case "L_SHAPE":
                PathCard_L L_Card = new PathCard_L(cardType);
                if (rotation != 0)
                    return rotateCard(L_Card, rotation);
                else
                    return L_Card;
            case "L_SHAPE_DEAD":
                PathCard_L_Dead L_Dead_Card = new PathCard_L_Dead(cardType);
                if (rotation != 0)
                    return rotateCard(L_Dead_Card, rotation);
                else
                    return L_Dead_Card;
            case "CROSS_SHAPE":
                return new PathCard_Cross(cardType);
            case "CROSS_SHAPE_DEAD":
                return new PathCard_Cross_Dead(cardType);
            case "DEAD":
                PathCard_DeadEnd DeadEnd_Card = new PathCard_DeadEnd(cardType);
                if (rotation != 0)
                    return rotateCard(DeadEnd_Card, rotation);
                else
                    return DeadEnd_Card;
            case "EMPTY":
                return new PathCard_Empty(cardType);
            case "ADD_PATH":
                return new ActionCard_Add_Path(cardType);
            case "CLEAN_PATH":
                return new ActionCard_Clean_Path_Card(cardType);
            case "CORRUPT_PATH":
                return new ActionCard_Corrupt_Path_Card(cardType);
            case "DESTROY_M_PATHS":
                return new ActionCard_Destroy_Multiple_Paths(cardType);
            case "DESTROY_PATH":
                return new ActionCard_Destroy_Path_Card(cardType);
            case "REMOVE_PATH":
                return new ActionCard_Remove_Path(cardType);
            case "ROTATE_PATH":
                return new ActionCard_Rotate_Path_Card(cardType);
            case "GOAL":
                return new GoalCard_Goal(true, cardType);
            case "COAL_HIDDEN":
                return new GoalCard_Coal(true, cardType);
            case "COAL_REVEALED":
                return new GoalCard_Coal(false, cardType);
            case "GOLD_HIDDEN":
                return new GoalCard_Gold(true, cardType);
            case "BREAK_LANTERN":
                return new PersonalCard_Break_Lantern(cardType);
            case "BREAK_PICKAXE":
                return new PersonalCard_Break_Pickaxe(cardType);
            case "FIX_LANTERN":
                return new PersonalCard_Fix_Lantern(cardType);
            case "FIX_PICKAXE":
                return new PersonalCard_Fix_Pickaxe(cardType);
            case "SICK":
                return new PersonalCard_Sick(cardType);
            case "SWAP_ROLE":
                return new PersonalCard_Swap_Role(cardType);
        }
        return null;
    }

    /**
     * @param card          Card object which is returned after being rotated
     * @param rotationValue Number of rotations needed
     * @return Rotated Card object
     */
    private Card rotateCard(PathCard card, int rotationValue) {
        switch (rotationValue) {
            case 1:
                card.rotate("cw");
                break;
            case 2:
                card.rotate("cw");
                card.rotate("cw");
                break;
            case 3:
                card.rotate("cw");
                card.rotate("cw");
                card.rotate("cw");
                break;
        }
        return card;
    }
}