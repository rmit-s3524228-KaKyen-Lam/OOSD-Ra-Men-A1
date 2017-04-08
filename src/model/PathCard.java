package model;

/**
 * Class for path cards which contains logic for rotation and initial path card configuration
 *
 * @author Fabio Monsalve s3585826
 */
public abstract class PathCard extends Card {

    // Keep track of rotations invoked
    private int rotateVal = 0;
    private boolean isValid = false;
    private boolean north = false;
    private boolean west = false;
    private boolean south = false;
    private boolean east = false;
    private boolean centre = false;

    // Configuration numbers relating to type of path card
    public final static int T_SHAPE = 1;
    public final static int T_SHAPE_DEAD = 2;
    public final static int LINE_SHAPE = 3;
    public final static int LINE_SHAPE_DEAD = 4;
    public final static int CROSS_SHAPE = 5;
    public final static int CROSS_SHAPE_DEAD = 6;
    public final static int L_SHAPE = 7;
    public final static int L_SHAPE_DEAD = 8;
    public final static int DEAD = 9;
    public final static int EMPTY = 10;

    /**
     * Every path card has 5 booleans attached to it, all representing path availability/existence. E.g. If a path card
     * has all booleans set to true it must be a cross shaped path card in which all four possible paths available
     * including the centre.
     *
     * @param configNo    represent the type of card, e.g. L shape or cross shape
     * @param imgResource location of image file
     * @param id          of path card
     */
    public PathCard(int configNo, String imgResource, String id) {
        super(imgResource, id);
        switch (configNo) {
            case T_SHAPE:
                south = true;
                west = true;
                east = true;
                centre = true;
            case T_SHAPE_DEAD:
                south = true;
                west = true;
                east = true;
                break;
            case LINE_SHAPE:
                west = true;
                east = true;
                centre = true;
                break;
            case LINE_SHAPE_DEAD:
                west = true;
                east = true;
                break;
            case CROSS_SHAPE:
                north = true;
                west = true;
                east = true;
                south = true;
                centre = true;
                break;
            case CROSS_SHAPE_DEAD:
                north = true;
                west = true;
                east = true;
                south = true;
                break;
            case L_SHAPE:
                east = true;
                north = true;
                centre = true;
                break;
            case L_SHAPE_DEAD:
                east = true;
                north = true;
                break;
            case DEAD:
                north = true;
                centre = false;
                break;
            case EMPTY:
                break;
        }
    }

    /**
     * Alternative constructor for a PathCard
     *
     * @param west
     * @param north
     * @param east
     * @param south
     * @param imgResource
     * @param id
     */
    public PathCard(boolean west, boolean north, boolean east, boolean south, String imgResource, String id) {
        super(imgResource, id);
        this.west = west;
        this.north = north;
        this.east = east;
        this.south = south;
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
    private void rotate(String direction) {
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

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public boolean isValid() {
        return isValid;
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
}