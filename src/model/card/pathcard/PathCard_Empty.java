package model.card.pathcard;

/**
 * Empty path card to represent unexplored paths
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class PathCard_Empty extends PathCard {

    public PathCard_Empty(String id) {
        super(false, false, false, false, false, "resources/Unexplored.png", id);
    }

}
