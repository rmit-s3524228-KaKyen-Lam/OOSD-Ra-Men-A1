package model.pathcard;

import model.PathCard;

/**
 * Cross shaped path card with hole
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class PathCard_Cross_Hole extends PathCard {

    public PathCard_Cross_Hole(String id) {
        super(true, true, true, true, false, "resources/Shape_Plus_Hole.png", id);
    }

    @Override
    public void cardAction() {

    }
}
