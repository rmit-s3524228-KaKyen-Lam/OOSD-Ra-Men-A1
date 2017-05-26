package model;

import model.card.Card;
import model.card.pathcard.PathCard;
import model.factory.*;

import java.util.HashMap;

/**
 * Flyweight pattern.
 * Keeps on object of each card in cardHashMap.
 * If it doesn't contain card object requested it asks the factory to produce
 * one
 *
 * @author Fabio Monsalve s3585826
 */
public class CardFlyweight {
    private static ActionCardFactory actionCardFactory = new ActionCardFactory();
    private static PathCardFactory pathCardFactory = new PathCardFactory();
    private static GoalCardFactory goalCardFactory = new GoalCardFactory();
    private static PersonalCardFactory personalCardFactory = new
            PersonalCardFactory();

    private static HashMap<String, Card[]> cardHashMap = new HashMap<>();

    /**
     * A single object of every card is kept in cardHashMap with the key being
     * cardType and the Object being an array of Cards where it stores single
     * objects of the possible rotation values.
     *
     * @param cardType      Key for hashmap and also used to create objects through the
     *                      Factory
     * @param rotationValue There are a total of 4 possible rotations, an array of
     *                      size 4 is created for each.
     * @return Requested card
     */
    public static Card getCard(String cardType, int rotationValue) {
        Card card = null;

        if (rotationValue < 0) {
            rotationValue += 4;
        } else if (rotationValue > 3) {
            rotationValue -= 4;
        }

        if (cardHashMap.containsKey(cardType)) {
            if (cardHashMap.get(cardType)[rotationValue] == null) {
                Card[] array = cardHashMap.get(cardType);

                if (cardType.contains("PATH")) {
                    card = pathCardFactory.getPathCard(cardType, rotationValue);
                } else if (cardType.contains("ACTION")) {
                    card = actionCardFactory.getActionCard(cardType);
                } else if (cardType.contains("PERSONAL")) {
                    card = personalCardFactory.getPersonalCard(cardType);
                } else if (cardType.contains("GOAL")) {
                    card = goalCardFactory.getGoalCard(cardType);
                }
                array[rotationValue] = card;
                cardHashMap.put(cardType, array);

                return card;
            } else {
                return cardHashMap.get(cardType)[rotationValue];
            }
        } else {
            Card[] array = new Card[4];

            if (cardType.contains("PATH")) {
                card = pathCardFactory.getPathCard(cardType, rotationValue);
            } else if (cardType.contains("ACTION")) {
                card = actionCardFactory.getActionCard(cardType);
            } else if (cardType.contains("PERSONAL")) {
                card = personalCardFactory.getPersonalCard(cardType);
            } else if (cardType.contains("GOAL")) {
                card = goalCardFactory.getGoalCard(cardType);
            }

            array[rotationValue] = card;
            cardHashMap.put(cardType, array);

            return card;
        }
    }

    public static PathCard getPathCard(boolean west, boolean north, boolean east, boolean south, boolean centre) {
        int pathCount = 0;
        if (west) {
            pathCount++;
        }
        if (north) {
            pathCount++;
        }
        if (east) {
            pathCount++;
        }
        if (south) {
            pathCount++;
        }
        switch (pathCount) {
            case 0:
                return (PathCard) getCard("PATH_EMPTY", 0);
            case 1:
                if (west) {
                    return (PathCard) getCard("PATH_DEAD", 3);
                } else if (north) {
                    return (PathCard) getCard("PATH_DEAD", 0);
                } else if (east) {
                    return (PathCard) getCard("PATH_DEAD", 1);
                } else if (south) {
                    return (PathCard) getCard("PATH_DEAD", 2);
                }
            case 2:
                if (west && north) {
                    if (!centre) {
                        return (PathCard) getCard("PATH_L_SHAPE_DEAD", 3);
                    } else {
                        return (PathCard) getCard("PATH_L_SHAPE", 3);
                    }
                } else if (north && east) {
                    if (!centre) {
                        return (PathCard) getCard("PATH_L_SHAPE_DEAD", 0);
                    } else {
                        return (PathCard) getCard("PATH_L_SHAPE", 0);
                    }
                } else if (east && south) {
                    if (!centre) {
                        return (PathCard) getCard("PATH_L_SHAPE_DEAD", 1);
                    } else {
                        return (PathCard) getCard("PATH_L_SHAPE", 1);
                    }
                } else if (south && west) {
                    if (!centre) {
                        return (PathCard) getCard("PATH_L_SHAPE_DEAD", 2);
                    } else {
                        return (PathCard) getCard("PATH_L_SHAPE", 2);
                    }
                } else if (south && north) {
                    if (!centre) {
                        return (PathCard) getCard("PATH_LINE_SHAPE_DEAD", 1);
                    } else {
                        return (PathCard) getCard("PATH_LINE_SHAPE", 1);
                    }
                } else if (east && west) {
                    if (!centre) {
                        return (PathCard) getCard("PATH_LINE_SHAPE_DEAD", 0);
                    } else {
                        return (PathCard) getCard("PATH_LINE_SHAPE", 0);
                    }
                }
            case 3:
                if (west && east && south) {
                    if (!centre) {
                        return (PathCard) getCard("PATH_T_SHAPE_DEAD", 0);
                    } else {
                        return (PathCard) getCard("PATH_T_SHAPE", 0);
                    }
                } else if (west && east && north) {
                    if (!centre) {
                        return (PathCard) getCard("PATH_T_SHAPE_DEAD", 2);
                    } else {
                        return (PathCard) getCard("PATH_T_SHAPE", 2);
                    }
                } else if (south && north && east) {
                    if (!centre) {
                        return (PathCard) getCard("PATH_T_SHAPE_DEAD", 3);
                    } else {
                        return (PathCard) getCard("PATH_T_SHAPE", 3);
                    }
                } else if (south && north && west) {
                    if (!centre) {
                        return (PathCard) getCard("PATH_T_SHAPE_DEAD", 1);
                    } else {
                        return (PathCard) getCard("PATH_T_SHAPE", 1);
                    }
                }
            case 4:
                if (centre) {
                    return (PathCard) getCard("PATH_CROSS_SHAPE", 0);
                } else {
                    return (PathCard) getCard("PATH_CROSS_SHAPE_DEAD", 0);
                }
        }
        return null;
    }
}
