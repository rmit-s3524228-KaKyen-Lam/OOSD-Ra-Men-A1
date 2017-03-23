package view;

import javafx.scene.image.ImageView;
import model.Grid;

import javax.xml.soap.Node;

/**
 * Created by HP on 22/03/2017.
 */
public class GridDraw extends ImageView {
    private Grid grid;

    public GridDraw(String file, Grid grid){
        super(file);
        this.grid = grid;
    }

    public Grid getGrid() {
        return grid;
    }
}
