package model.pathcard;

import model.PathCard;

/**
 * Dead end path card
 *
 * @author Fabio Monsalve Duque s3585826
 *
 */
public class PathCard_DeadEnd extends PathCard {

    public PathCard_DeadEnd(String id) {
        super(9, "resources/Shape_Dead.png", id);
    }

    @Override
    public void cardAction() {

    }
}
