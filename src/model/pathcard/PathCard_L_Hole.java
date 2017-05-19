package model.pathcard;

import model.PathCard;

/**
 * L shaped path card with hole
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class PathCard_L_Hole extends PathCard {

    public PathCard_L_Hole(String id) {
        super(false, true, true, false, false, "resources/Shape_L_Hole.png", id);
    }
}
