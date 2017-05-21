package model.pathcard;

import model.PathCard;

/**
 * Dead end path card
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class PathCard_DeadEnd extends PathCard {

    public PathCard_DeadEnd() {
        super(false, true, false, false, false, "resources/Shape_Dead.png");
    }

    @Override
    public void cardAction() {

    }
}
