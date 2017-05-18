package view;

import javafx.scene.image.ImageView;
import model.Card;

import java.util.HashMap;

/**
 * Flyweight pattern implementation of ImageView used in-game
 *
 * @author David Limantoro s3503728
 */
public class ImageViewFlyweight {

    private static HashMap<Card, ImageView[][]> hashMap = new HashMap<>();

    static final int TINT_NONE = 0;
    static final int TINT_GREY_TO_RED = 1;
    static final int TINT_GREY_TO_YELLOW = 2;
    static final int TINT_GREY_TO_GREEN = 3;
    static final int TINT_BLUE_TO_RED = 4;
    static final int TINT_BLUE_TO_YELLOW = 5;
    static final int TINT_BLUE_TO_GREEN = 6;

    static final int ROTATE_0 = 0;
    static final int ROTATE_90 = 1;
    static final int ROTATE_180 = 2;
    static final int ROTATE_270 = 3;

    /**
     * This method returns ImageView requested and with the specified rotation value
     *
     * @param card          The card object being looked for
     * @param rotationValue 0 for no rotation, 1 for 90 degree CW rotation, 2 for 180 degree rotation,
     *                      3 for 270 degree (90 CCW) rotation.
     * @return A flyweighted ImageView
     */
    public static ImageView requestImageview(Card card, int tintValue, int rotationValue) {
        if (hashMap.containsKey(card)) {
            if (hashMap.get(card)[tintValue][rotationValue] == null) {
                ImageView[][] array = hashMap.get(card);
                ImageView newIV = createImageView(card.getImageResource(), tintValue, rotationValue);
                array[tintValue][rotationValue] = newIV;
                // TODO check if the hashmap's data is changed or not
                return newIV;
            } else {
                return hashMap.get(card)[tintValue][rotationValue];
            }
        } else {
            ImageView[][] ivArray = new ImageView[7][4];

            ImageView newIV = createImageView(card.getImageResource(), tintValue, rotationValue);
            ivArray[tintValue][rotationValue] = newIV;

            // Put it in HashMap
            hashMap.put(card, ivArray);
            return newIV;
        }
    }

    private static ImageView createImageView(String imgPath, int tintValue, int rotationValue) {
        ImageView newIV = new ImageView(imgPath);

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
