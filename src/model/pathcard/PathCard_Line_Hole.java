package model.pathcard;

import model.PathCard;

/**
 * Line shaped path card with hole
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class PathCard_Line_Hole extends PathCard {

    public PathCard_Line_Hole(String id) {
        super(true, false, true, false, false, "resources/Shape_Line_Hole.png", id);
    }

    @Override
    public void cardAction() {

    }
}
