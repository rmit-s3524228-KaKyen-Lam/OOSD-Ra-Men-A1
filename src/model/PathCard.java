package model;

public class PathCard extends Card {
    private boolean isValid = false;
    private boolean north = false;
    private boolean west = false;
    private boolean south = false;
    private boolean east = false;
    private boolean centre = false;
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

    public PathCard (int configNo, String imgResource, String id){
        super(imgResource, id);
        switch (configNo){
            case T_SHAPE:
                south = true;
                west = true;
                east = true;
                centre = true;
            case T_SHAPE_DEAD: south = true;
                west = true;
                east = true;
                break;
            case LINE_SHAPE: west = true;
                east = true;
                centre = false;
                break;
            case LINE_SHAPE_DEAD: west = true;
                east = true;
                break;
            case CROSS_SHAPE: north = true;
                west = true;
                east = true;
                south = true;
                centre = true;
                break;
            case CROSS_SHAPE_DEAD: north = true;
                west = true;
                east = true;
                south = true;
                break;
            case L_SHAPE: west = true;
                south = true;
                centre = true;
                break;
            case L_SHAPE_DEAD: north = true;
                south = true;
                break;
            case DEAD: north = true;
                centre = false;
                break;
            case EMPTY:
                break;
        }
    }

    private void rotate(String direction) {
        boolean tempNorth = north;
        boolean tempWest = west;
        boolean tempSouth = south;
        boolean tempEast = east;
        boolean temp [] = {tempNorth, tempWest, tempSouth, tempEast};

        if(direction.equals("cw")) {

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
        }

        if(direction.equals("acw")){
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
    }

    @Override
    public void cardAction() {

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