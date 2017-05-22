package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Card;
import model.PathCard;

import java.util.HashMap;

/**
 * Flyweight pattern implementation of ImageView used in-game
 *
 * @author David Limantoro s3503728
 */
public class ImageFlyweightImpl implements ImageFlyweight {

    private HashMap<Card, Image> hashMap = new HashMap<>();

    /**
     * This method returns ImageView requested and with the specified rotation value
     *
     * @param card The card object being looked for
     * @return A flyweighted ImageView
     */
    public Image requestImage(Card card) {
        if (hashMap.containsKey(card)) {
            return hashMap.get(card);
        } else {
            // Put it in HashMap
            Image newImage = new Image(card.getImageResource());
            hashMap.put(card, newImage);
            return newImage;
        }
    }
}
