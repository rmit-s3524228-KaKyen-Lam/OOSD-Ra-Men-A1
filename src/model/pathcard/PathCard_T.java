package model.pathcard;

import model.PathCard;

/**
 * T shaped path card
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class PathCard_T extends PathCard {

    public PathCard_T(String id) {
        super(true, false, true, true, true, "resources/Shape_T.png", id);
    }

    @Override
    public void cardAction() {

    }
}
