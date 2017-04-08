package model.pathcard;

import model.PathCard;

/**
 * @author HP on 7/04/2017.
 */
public class PathCard_L_Hole extends PathCard {
    /**
     * Every path card has 5 booleans attached to it, all representing path availability/existence. E.g. If a path card
     * has all booleans set to true it must be a cross shaped path card in which all four possible paths available
     * including the centre.
     *
     * @param id of path card
     */
    public PathCard_L_Hole(String id) {
        super(8, "resources/Shape_Plus.png", id);
    }

    @Override
    public void cardAction() {

    }
}
