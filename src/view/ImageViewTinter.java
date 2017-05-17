package view;

import javafx.scene.effect.ColorAdjust;

/**
 * @author HP on 4/05/2017.
 */
public class ImageViewTinter {

    static ColorAdjust redTint = new ColorAdjust();
    static ColorAdjust yellowTint = new ColorAdjust();
    static ColorAdjust greenTint = new ColorAdjust();
    static ColorAdjust removeTint = new ColorAdjust();

    public static void initialize() {
        redTint.setHue(0);
        redTint.setBrightness(0.6);
        redTint.setSaturation(1);
        //redTint.setContrast(1);

        yellowTint.setHue(0.3);
        yellowTint.setBrightness(0.6);
        yellowTint.setSaturation(1);
        //yellowTint.setContrast(1);

        greenTint.setHue(0.6);
        greenTint.setBrightness(0.6);
        greenTint.setSaturation(1);
        //greenTint.setContrast(1);
    }

}
