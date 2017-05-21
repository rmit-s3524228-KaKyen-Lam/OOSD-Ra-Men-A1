package view;

import javafx.scene.image.ImageView;
import model.Card;
import model.PathCard;

import java.util.HashMap;

/**
 * Flyweight pattern implementation of ImageView used in-game
 *
 * @author David Limantoro s3503728
 */
public class ImageViewFlyweight {

    private HashMap<Card, ImageView[]> hashMap = new HashMap<>();

    final int TINT_NONE = 0;
    final int TINT_GREY_TO_RED = 1;
    final int TINT_GREY_TO_YELLOW = 2;
    final int TINT_GREY_TO_GREEN = 3;
    final int TINT_BLUE_TO_RED = 4;
    final int TINT_BLUE_TO_YELLOW = 5;
    final int TINT_BLUE_TO_GREEN = 6;

    /**
     * This method returns ImageView requested and with the specified rotation value
     *
     * @param card The card object being looked for
     * @return A flyweighted ImageView
     */
    public ImageView requestImageview(Card card, int tintValue) {
        if (hashMap.containsKey(card)) {
            if (hashMap.get(card)[tintValue] == null) {
                ImageView[] array = hashMap.get(card);
                ImageView newIV = createImageView(card, tintValue);
                array[tintValue] = newIV;
                return newIV;
            } else {
                return hashMap.get(card)[tintValue];
            }
        } else {
            ImageView[] ivArray = new ImageView[7];
            ImageView newIV = createImageView(card, tintValue);
            ivArray[tintValue] = newIV;

            // Put it in HashMap
            hashMap.put(card, ivArray);
            return newIV;
        }
    }

    private final int ROTATE_0 = 0;
    private final int ROTATE_90 = 1;
    private final int ROTATE_180 = 2;
    private final int ROTATE_270 = 3;

    private ImageView createImageView(Card card, int tintValue) {

        ImageView newIV = new ImageView(card.getImageResource());
        int rotationValue;
        if (card instanceof PathCard) {
            rotationValue = ((PathCard) card).getRotateVal();
        } else {
            rotationValue = ROTATE_0;
        }

        // Rotate the ImageView
        switch (rotationValue) {
            case ROTATE_0:
                break;
            case ROTATE_90:
                newIV.setRotate(90);
                break;
            case ROTATE_180:
                newIV.setRotate(180);
                break;
            case ROTATE_270:
                newIV.setRotate(270);
                break;
        }

        // Tint the color
        switch (tintValue) {
            case TINT_NONE:
                break;
            case TINT_GREY_TO_RED:
                newIV.setEffect(ImageViewTinter.grayToRedTint);
                break;
            case TINT_GREY_TO_YELLOW:
                newIV.setEffect(ImageViewTinter.grayToYellowTint);
                break;
            case TINT_GREY_TO_GREEN:
                newIV.setEffect(ImageViewTinter.grayToGreenTint);
                break;
            case TINT_BLUE_TO_RED:
                newIV.setEffect(ImageViewTinter.blueToRedTint);
                break;
            case TINT_BLUE_TO_YELLOW:
                newIV.setEffect(ImageViewTinter.blueToYellowTint);
                break;
            case TINT_BLUE_TO_GREEN:
                newIV.setEffect(ImageViewTinter.blueToGreenTint);
                break;
        }

        return newIV;
    }
}
