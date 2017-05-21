package model;

import model.actioncard.*;
import model.goalcard.GoalCard_Coal;
import model.goalcard.GoalCard_Goal;
import model.goalcard.GoalCard_Gold;
import model.pathcard.*;

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
                PathCard_T T_Card = new PathCard_T();
                if (rotation != 0)
                    return rotateCard(T_Card, rotation);
                else
                    return T_Card;
            case "T_SHAPE_DEAD":
                PathCard_T_Dead T_Dead_Card = new PathCard_T_Dead();
                if (rotation != 0)
                    return rotateCard(T_Dead_Card, rotation);
                else
                    return T_Dead_Card;
            case "LINE_SHAPE":
                PathCard_Line Line_Card = new PathCard_Line();
                if (rotation != 0)
                    return rotateCard(Line_Card, rotation);
                else
                    return Line_Card;
            case "LINE_SHAPE_DEAD":
                PathCard_Line_Dead Line_Dead_Card = new PathCard_Line_Dead();
                if (rotation != 0)
                    return rotateCard(Line_Dead_Card, rotation);
                else
                    return Line_Dead_Card;
            case "L_SHAPE":
                PathCard_L L_Card = new PathCard_L();
                if (rotation != 0)
                    return rotateCard(L_Card, rotation);
                else
                    return L_Card;
            case "L_SHAPE DEAD":
                PathCard_L_Dead L_Dead_Card = new PathCard_L_Dead();
                if (rotation != 0)
                    return rotateCard(L_Dead_Card, rotation);
                else
                    return L_Dead_Card;
            case "CROSS_SHAPE":
                return new PathCard_Cross();
            case "CROSS_SHAPE_DEAD":
                return new PathCard_Cross_Dead();
            case "DEAD":
                return new PathCard_DeadEnd();
            case "EMPTY":
                return new PathCard_Empty();
            case "ADD_PATH":
                return new ActionCard_Add_Path();
            case "CLEAN_PATH":
                return new ActionCard_Clean_Path_Card();
            case "CORRUPT_PATH":
                return new ActionCard_Corrupt_Path_Card();
            case "DESTROY_M_PATHS":
                return new ActionCard_Destroy_Multiple_Paths();
            case "DESTROY_PATH":
                return new ActionCard_Destroy_Path_Card();
            case "REMOVE_PATH":
                return new ActionCard_Remove_Path();
            case "ROTATE_PATH":
                return new ActionCard_Rotate_Path_Card();
            case "SWAP_PATH":
                return new ActionCard_Swap_Path_Cards();
            case "COAL":
                return new GoalCard_Coal();
            case "GOAL":
                return new GoalCard_Goal();
            case "GOLD":
                return new GoalCard_Gold();
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
            case 90:
                card.rotate("cw");
                break;
            case 180:
                card.rotate("cw");
                card.rotate("cw");
                break;
            case 270:
                card.rotate("cw");
                card.rotate("cw");
                card.rotate("cw");
                break;
        }
        return card;
    }
}