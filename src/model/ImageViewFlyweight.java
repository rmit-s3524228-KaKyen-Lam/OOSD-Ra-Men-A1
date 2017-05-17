package model;

import javafx.scene.image.ImageView;

import java.util.HashMap;

/**
 * @author HP on 4/05/2017.
 */
public class ImageViewFlyweight {

    static HashMap<String, ImageView[]> hashMap = new HashMap<>();

    /**
     *
     * @param requestString
     * @param rotationValue 0 for no rotation, 1 for 90 degree CW rotation, 2 for 180 degree rotation,
     *                      3 for 270 degree (90 CCW) rotation.
     * @return
     */
    public static ImageView requestImageview(String requestString, int rotationValue) {
        if (hashMap.containsKey(requestString)) {
            return hashMap.get(requestString)[rotationValue];
        } else {
            ImageView[] ivArray = new ImageView[4];
            ImageView newIV = new ImageView();

            ivArray[rotationValue] = newIV;

            hashMap.put(requestString, ivArray);
            return newIV;
        }
    }
}
