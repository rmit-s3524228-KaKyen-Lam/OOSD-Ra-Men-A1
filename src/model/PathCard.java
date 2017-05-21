package model;

import controller.LogicCheckerBridge;

/**
 * Class for path cards which contains logic for rotation and initial path card configuration
 * Every path card has 5 booleans attached to it, all representing path availability/existence. E.g. If a path card
 * has all booleans set to true it must be a cross shaped path card in which all four possible paths available
 * including the centre.
 *
 * @author Fabio Monsalve s3585826
 */
public abstract class PathCard extends Card {

    // Keep track of rotations invoked
    private int rotateVal = 0;
    private boolean north = false;
    private boolean west = false;
    private boolean south = false;
    private boolean east = false;
    private boolean centre = false;

    /**
     * Creates a PathCard with the associated path values
     *
     * @param west        true if west path is valid
     * @param north       true if north path is valid
     * @param east        true if east path is valid
     * @param south       true if south path is valid
     * @param centre      true if center of the path is valid
     * @param imgResource imageResource for this path card
     */
    public PathCard(boolean west, boolean north, boolean east, boolean south, boolean centre, String imgResource) {
        super(imgResource);
        this.west = west;
        this.north = north;
        this.east = east;
        this.south = south;
        this.centre = centre;
    }

    /**
     * Logic for rotation of path cards. Path cards can rotate 90 degrees clockwise ("cw") and anticlockwise ("acw").
     * Every time this method is called all the boolean values change logically to suit the direction it's being rotates
     * to. E.g. L shaped path card is being rotated clockwise and has its boolean values of north set as true, east as
     * true, centre as true and the rest as false, after one rotation they will have changed to east as true, south as
     * true, centre as true and the rest as false.
     *
     * @param direction direction clockwise("cw") or anticlockwise("acw")
     */
    public void rotate(String direction) {
        boolean tempNorth = north;
        boolean tempWest = west;
        boolean tempSouth = south;
        boolean tempEast = east;

        // Stores original boolean values so as to not modify them twice
        boolean temp[] = {tempNorth, tempWest, tempSouth, tempEast};

        if (direction.equals("cw")) {
            rotateVal++;

            if (temp[0]) {
                east = true;
                north = false;
            }
            if (temp[1]) {
                north = true;
                west = false;
            }
            if (temp[2]) {
                west = true;
                south = false;
            }
            if (temp[3]) {
                south = true;
                east = false;
            }
        } else if (direction.equals("acw")) {
            rotateVal--;
            if (temp[0]) {
                west = true;
            }
            if (temp[1]) {
                west = true;
            }
            if (temp[2]) {
                south = true;
            }
            if (temp[3]) {
                north = true;
            }
        }

        if (rotateVal < 0) {
            rotateVal = 3;
        } else if (rotateVal > 3) {
            rotateVal = 0;
        }
    }

    public boolean isNorth() {
        return north;
    }

    public boolean isWest() {
        return west;
    }

    public boolean isSouth() {
        return south;
    }

    public boolean isEast() {
        return east;
    }

    public boolean isCentre() {
        return centre;
    }

    public int getRotateVal() {
        return rotateVal;
    }

    @Override
    public boolean cardAction(Object[] target) {
        Grid targetGrid = (Grid) target[0];
        if (LogicCheckerBridge.checkIfValid(this, targetGrid.getX(), targetGrid.getY())) {
            targetGrid.setCard(this);
            targetGrid.setConnectedToMain(true);
            return true;
        }
        return false;
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Grid targetGrid = (Grid) target[0];
        Grid prevGrid = (Grid) undoExtraInformation[0];
        targetGrid.setCard(prevGrid.getCard());
    }
}