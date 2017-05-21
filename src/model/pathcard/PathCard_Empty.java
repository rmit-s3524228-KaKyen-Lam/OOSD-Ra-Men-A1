package model.pathcard;

import model.PathCard;

/**
 * Empty path card to represent unexplored paths
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class PathCard_Empty extends PathCard {

    public PathCard_Empty() {
        super(false, false, false, false, false, "resources/Unexplored.png");
    }

}
