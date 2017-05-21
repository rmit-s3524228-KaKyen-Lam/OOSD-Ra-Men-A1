package model;

import java.util.HashMap;

/**
 * Created by orlandok on 19/5/17.
 * *
 */
public class CardFlyweight {
  private static CardFactory cardFactory = new CardFactory();
  public static HashMap<String, Card[]> cardHashMap = new HashMap<>();

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
    }else {
      Card [] array = new Card [4];
      Card card = cardFactory.makeCard(cardType, rotationValue);
      array[rotationValue] = card;
      cardHashMap.put(cardType, array);

      return card;
    }
  }
}
