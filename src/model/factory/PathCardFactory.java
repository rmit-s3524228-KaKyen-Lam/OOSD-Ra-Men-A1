package model.factory;

import model.card.actioncard.ActionCard;
import model.card.goalcard.GoalCard;
import model.card.pathcard.*;
import model.card.personalcard.PersonalCard;

/**
 * Created by orlandok on 25/5/17.
 * *
 */
public class PathCardFactory extends AbstractCardFactory {

    @Override
    public ActionCard getActionCard(String cardType) {
        return null;
    }

    @Override
    public PathCard getPathCard(String cardType, int rotation) {

        switch (cardType) {
            case "PATH_T_SHAPE":
                PathCard_T T_Card = new PathCard_T(cardType);
                if (rotation != 0)
                    return rotateCard(T_Card, rotation);
                else
                    return T_Card;
            case "PATH_T_SHAPE_DEAD":
                PathCard_T_Dead T_Dead_Card = new PathCard_T_Dead(cardType);
                if (rotation != 0)
                    return rotateCard(T_Dead_Card, rotation);
                else
                    return T_Dead_Card;
            case "PATH_LINE_SHAPE":
                PathCard_Line Line_Card = new PathCard_Line(cardType);
                if (rotation != 0)
                    return rotateCard(Line_Card, rotation);
                else
                    return Line_Card;
            case "PATH_LINE_SHAPE_DEAD":
                PathCard_Line_Dead Line_Dead_Card = new PathCard_Line_Dead(cardType);
                if (rotation != 0)
                    return rotateCard(Line_Dead_Card, rotation);
                else
                    return Line_Dead_Card;
            case "PATH_L_SHAPE":
                PathCard_L L_Card = new PathCard_L(cardType);
                if (rotation != 0)
                    return rotateCard(L_Card, rotation);
                else
                    return L_Card;
            case "PATH_L_SHAPE_DEAD":
                PathCard_L_Dead L_Dead_Card = new PathCard_L_Dead(cardType);
                if (rotation != 0)
                    return rotateCard(L_Dead_Card, rotation);
                else
                    return L_Dead_Card;
            case "PATH_CROSS_SHAPE":
                return new PathCard_Cross(cardType);
            case "PATH_CROSS_SHAPE_DEAD":
                return new PathCard_Cross_Dead(cardType);
            case "PATH_DEAD":
                PathCard_DeadEnd DeadEnd_Card = new PathCard_DeadEnd(cardType);
                if (rotation != 0)
                    return rotateCard(DeadEnd_Card, rotation);
                else
                    return DeadEnd_Card;
            case "PATH_EMPTY":
                return new PathCard_Empty(cardType);
        }
        return null;
    }

    private PathCard rotateCard(PathCard card, int rotationValue) {
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

    @Override
    public GoalCard getGoalCard(String cardType) {
        return null;
    }

    @Override
    public PersonalCard getPersonalCard(String cardType) {
        return null;
    }
}
