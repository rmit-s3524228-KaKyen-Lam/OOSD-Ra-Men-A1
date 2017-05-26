package view;

import javafx.scene.image.Image;
import model.card.Card;

import java.util.HashMap;

/**
 * Implementation of ImageFlyweight, used by BoardDraw and PlayerHandDraw to minimize waste of memory
 * creating ImageView objects.
 *
 * Additionally, this is a singleton class to further reduce memory waste.
 * It contains a HashMap that uses card object as the key of the map since the cards are also flyweighted.
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
        // Check if image of the card has been made or not
        if (hashMap.containsKey(card)) { // return existing Image object
            return hashMap.get(card);
        } else { // Create new image
            Image newImage = new Image(card.getImageResource());
            hashMap.put(card, newImage);
            return newImage;
        }
    }
}
