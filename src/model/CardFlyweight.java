package model;

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
    private static CardFactory cardFactory = new CardFactory();
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

        if (cardHashMap.containsKey(cardType)) {
            if (cardHashMap.get(cardType)[rotationValue] == null) {
                Card[] array = cardHashMap.get(cardType);
                Card card = cardFactory.makeCard(cardType, rotationValue);
                array[rotationValue] = card;
                return card;
            } else {
                return cardHashMap.get(cardType)[rotationValue];
            }
        } else {
            Card[] array = new Card[4];
            Card card = cardFactory.makeCard(cardType, rotationValue);
            array[rotationValue] = card;
            cardHashMap.put(cardType, array);

            return card;
        }
    }
}
