package model.pathcard;

import model.PathCard;

/**
 * Line shaped path card with hole
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class PathCard_Line_Dead extends PathCard {

    public PathCard_Line_Dead() {
        super(true, false, true, false, false, "resources/Shape_Line_Hole.png");
    }
}