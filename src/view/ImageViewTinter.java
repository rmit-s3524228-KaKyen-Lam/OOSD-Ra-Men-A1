package view;

import javafx.scene.effect.ColorAdjust;

/**
 * This class is a library for tinting ImageView
 *
 * @author David Limantoro s3503728
 */
public class ImageViewTinter {

    static ColorAdjust grayToRedTint = new ColorAdjust(0, 1, 0.6, 1);
    static ColorAdjust grayToYellowTint = new ColorAdjust(0.3, 1, 0.6, 1);
    static ColorAdjust grayToGreenTint = new ColorAdjust(0.6, 1, 0.6, 1);
    static ColorAdjust blueToRedTint = new ColorAdjust(0.75, 1, 0.6, 1);
    static ColorAdjust blueToYellowTint = new ColorAdjust(0, 1, 0.6, 1);
    static ColorAdjust blueToGreenTint = new ColorAdjust(-0.4, 1, 0.6, 1);
    static ColorAdjust disabledTint = new ColorAdjust(0, 0, 0.2, 1);
    static ColorAdjust unconnectedTint = new ColorAdjust(0, 1, .5, 1);
    static ColorAdjust removeTint = new ColorAdjust();

}
