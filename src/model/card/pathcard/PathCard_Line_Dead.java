package model.card.pathcard;

/**
 * Line shaped path card with hole
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class PathCard_Line_Dead extends PathCard {

    public PathCard_Line_Dead(String id) {
        super(true, false, true, false, false, "resources/Shape_Line_Hole.png", id);
    }
}