package view;

import javafx.scene.image.ImageView;

/**
 * Created by HP on 22/03/2017.
 */
public class GridDraw extends ImageView {
    private int xAxis;
    private int yAxis;
    private String entityOwner;

    public GridDraw(String file, int x, int y, String owner) {
        super(file);
        this.xAxis = x;
        this.yAxis = y;
        entityOwner = owner;
    }

    public int getxAxis() {
        return xAxis;
    }

    public int getyAxis() {
        return yAxis;
    }

    public String getEntityOwner() {
        return entityOwner;
    }
}
