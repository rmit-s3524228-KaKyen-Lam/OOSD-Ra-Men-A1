package model;

public class PathCard extends Card {
    private boolean isValid;
    private boolean north;
    private boolean west;
    private boolean south;
    private boolean east;
    private boolean centre;

    private void rotate(String direction) {
        boolean tempNorth = north;
        boolean tempSouth = south;
        boolean tempWest = west;
        boolean tempEast = east;
        boolean temp [] = {tempNorth, tempSouth, tempEast, tempWest};

        if(direction.equals("right")) {
            if (temp[0]) {
                east = true;
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

        if(direction.equals("left")){
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

    private boolean updateIsValid() {
        return true;
    }
}