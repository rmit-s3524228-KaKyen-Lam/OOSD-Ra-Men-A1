package view;

import javafx.scene.effect.ColorAdjust;

/**
 * This is a library class containing multiple ColorAdjust objects for tinting ImageView
 *
 * @author David Limantoro s3503728
 */
public class ImageViewTinter {

    ColorAdjust grayToRedTint = new ColorAdjust(0, 1, 0.6, 1);
    ColorAdjust grayToYellowTint = new ColorAdjust(0.3, 1, 0.6, 1);
    ColorAdjust grayToGreenTint = new ColorAdjust(0.6, 1, 0.6, 1);
    ColorAdjust blueToRedTint = new ColorAdjust(0.75, 1, 0.6, 1);
    ColorAdjust blueToYellowTint = new ColorAdjust(0, 1, 0.6, 1);
    ColorAdjust blueToGreenTint = new ColorAdjust(-0.4, 1, 0.6, 1);
    ColorAdjust disabledTint = new ColorAdjust(0, 0, 0.2, 1);
    ColorAdjust unconnectedTint = new ColorAdjust(0, 1, .5, 1);
    ColorAdjust noTint = new ColorAdjust();

    private static ImageViewTinter instance = null;

    public static ImageViewTinter getInstance() {
        if (instance == null) {
            instance = new ImageViewTinter();
        }
        return instance;
    }

}
