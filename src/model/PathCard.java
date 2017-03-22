package model;

public class PathCard extends Card {
    private boolean isValid;
    private boolean north;
    private boolean west;
    private boolean south;
    private boolean east;
    private boolean centre;

    public static void main(String args[]){
        PathCard pCard = new PathCard(true, true, true, false);
        pCard.printCurrentDirections();
        pCard.rotate("right");
        pCard.printCurrentDirections();
    }
    public PathCard (boolean north, boolean west, boolean south, boolean east){
        this.north = north;
        this.west = west;
        this.south = south;
        this.east = east;
    }

    private void rotate(String direction) {
        boolean tempNorth = north;
        boolean tempWest = west;
        boolean tempSouth = south;
        boolean tempEast = east;
        boolean temp [] = {tempNorth, tempWest, tempSouth, tempEast};

        if(direction.equals("right")) {
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

    private void printCurrentDirections(){
        System.out.println("North: " + north + "\n" + "West: " + west + "\n" + "South: " + south + "\n" +
                "East: " + east + "\n");
    }

    private boolean updateIsValid() {
        return true;
    }
}