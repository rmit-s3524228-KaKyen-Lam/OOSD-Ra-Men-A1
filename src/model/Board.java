package model;

import model.goalcard.GoalCard_Coal;
import model.goalcard.GoalCard_Gold;
import model.pathcard.PathCard_Cross;
import model.pathcard.PathCard_Empty;

import java.util.*;

/*http://stackoverflow.com/questions/9369368/2d-arraylist-in-java
 https://www.thoughtco.com/generating-unique-random-numbers-2034208*/

/**
 * Game board containing Grids.
 *
 * @author Ka Kyen Lam s3524228.
 */
public class Board {

    public static final int MAX_ALLOWED_WIDTH = 20;
    public static final int MAX_ALLOWED_HEIGHT = 20;
    public static int gridMaxWidth = 9;
    public static int gridMaxHeight = 7;

    private int goalNo;
    private int startGoalX = 8;
    private int startGoalY1 = 1;
    private int startGoalY2 = 3;
    private int startGoalY3 = 5;
    private int startPathX = 0;
    private int startPathY = 3;

    private ArrayList<Grid> goalPos;

    private Grid[][] grid;
    private boolean[][] isFilled;
    private Grid goldLocation;

    public void configureBoard(int widthMax, int heightMax) {
        gridMaxWidth = widthMax;
        gridMaxHeight = heightMax;
        grid = new Grid[gridMaxWidth][gridMaxHeight];

        for (int i = 0; i < gridMaxHeight; i++) {
            for (int j = 0; j < gridMaxWidth; j++) {
                isFilled[j][i] = false;
            }
        }
    }

    public void configureGoalNo(int goalAmount) {
        goalNo = goalAmount;
    }

    public String configureGoalPos(String name, int width, int height) {
        boolean isValid = true;
        if (width < 0 || width > gridMaxWidth) {
            isValid = false;
            return ("Invalid width. Please input width between 0 - " + gridMaxWidth);
        }

        if (height < 0 || height > gridMaxHeight) {
            isValid = false;
            return ("Invalid height. Please input width between 0 - " + gridMaxHeight);
        }

        if (isFilled[width][height] == true) {
            isValid = false;
            return ("Invalid position. That grid has been occupied.");
        }

        if (isValid) {
            isFilled[width][height] = true;
            if (name.equals("coal")) {
                grid[width][height] = new Grid(width, height, CardFlyweight.getCard("COAL", 0));
                return ("Coal created at (" + width + ", " + height + ")");
            } else if (name.equals("gold")) {
                grid[width][height] = new Grid(width, height, CardFlyweight.getCard("GOLD", 0));
                goldLocation = grid[width][height];
                return ("Gold created at (" + width + ", " + height + ")");
            }
        }
        return ("Internal error. No loop triggered.");
    }


    /**
     * Randomise starting positions of GoalCards
     */
    private ArrayList<Integer> randomPosition() {
        ArrayList<Integer> startGoalYList = new ArrayList<>();

        startGoalYList.add(startGoalY1);
        startGoalYList.add(startGoalY2);
        startGoalYList.add(startGoalY3);

        Collections.shuffle(startGoalYList);

        return startGoalYList;
    }

    /**
     * Initialise board
     */
    public void initBoardNew() {
        for (int i = 0; i < gridMaxHeight; i++) {
            for (int j = 0; j < gridMaxWidth; j++) {
                if (!isFilled[j][i]) {
                    if (i == startPathY && j == startPathX) {
                        grid[j][i] = new Grid(j, i, CardFlyweight.getCard("T_SHAPE", 0));
                        ((PathCard) (grid[j][i].getCard())).setValid(true);
                        grid[j][i].setConnectedToMain(false);
                        isFilled[j][i] = true;
                    } else {
                        grid[j][i] = new Grid(j, i, CardFlyweight.getCard("DEAD", 0));
                        isFilled[j][i] = true;
                    }
                }
            }
        }
    }


    void initBoard() {
        ArrayList<Integer> startGoalYList = randomPosition();
        for (int i = 0; i < gridMaxHeight; i++) {
            for (int j = 0; j < gridMaxWidth; j++) {
                if ((i == startGoalYList.get(0) || i == startGoalYList.get(1)) && j == startGoalX) {
                    grid[j][i] = new Grid(j, i, new GoalCard_Coal());
                } else if (i == startGoalYList.get(2) && j == startGoalX) {
                    grid[j][i] = new Grid(j, i, new GoalCard_Gold());
                    goldLocation = grid[j][i];
                } else if (i == startPathY && j == startPathX) {
                    grid[j][i] = new Grid(j, i, new PathCard_Cross());
                    ((PathCard) (grid[j][i].getCard())).setValid(true);
                } else {
                    grid[j][i] = new Grid(j, i, new PathCard_Empty());
                }
            }
            System.out.println();
        }
    }


    /**
     * Check if gold is found
     *
     * @return boolean
     */
    boolean goldIsFound() {
        return !((GoalCard) (goldLocation.getCard())).isHidden();
    }

    /**
     * Place card on selected x-y location
     *
     * @param x    position x on Board
     * @param y    position y on Board
     * @param card Card placed on Board
     */
    public void placeCardOnLocation(int x, int y, Card card) {
        grid[x][y].setCard(card);
    }

    /**
     * Get grid at specified location
     *
     * @param x position x on Board
     * @param y position y on Board
     * @return Grid object if x and y value is valid, otherwise return null
     */
    public Grid getGridAtLocation(int x, int y) {
        if (x < 0 || x >= gridMaxWidth || y < 0 || y >= gridMaxHeight) {
            return null;
        } else {
            return grid[x][y];
        }
    }

    public Grid[][] getGrid() {
        return grid;
    }
}
