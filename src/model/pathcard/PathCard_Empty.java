package model.pathcard;

import model.PathCard;

/**
 * Empty path card to represent unexplored paths
 */
public class PathCard_Empty extends PathCard {

    public PathCard_Empty(String id) {
        super(10, "resources/Unexplored.png", id);
    }

    @Override
    public void cardAction() {

    }
}
