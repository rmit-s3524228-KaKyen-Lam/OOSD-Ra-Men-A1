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

    public static final int GRID_MAX_WIDTH = 9;
    public static final int GRID_MAX_HEIGHT = 7;

    private int startGoalX = 8;
    private int startGoalY1 = 1;
    private int startGoalY2 = 3;
    private int startGoalY3 = 5;
    private int startPathX = 0;
    private int startPathY = 3;

    private Grid[][] grid = new Grid[GRID_MAX_WIDTH][GRID_MAX_HEIGHT];
    private Grid goldLocation;

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
    void initBoard() {
        ArrayList<Integer> startGoalYList = randomPosition();
        for (int i = 0; i < GRID_MAX_HEIGHT; i++) {
            for (int j = 0; j < GRID_MAX_WIDTH; j++) {
                if ((i == startGoalYList.get(0) || i == startGoalYList.get(1)) && j == startGoalX) {
                    grid[j][i] = new Grid(j, i, new GoalCard_Coal(("coal")));
                } else if (i == startGoalYList.get(2) && j == startGoalX) {
                    grid[j][i] = new Grid(j, i, new GoalCard_Gold(("gold")));
                    goldLocation = grid[j][i];
                } else if (i == startPathY && j == startPathX) {
                    grid[j][i] = new Grid(j, i, new PathCard_Cross("initial cross shaped path card"));
                    ((PathCard) (grid[j][i].getCard())).setValid(true);
                } else {
                    grid[j][i] = new Grid(j, i, new PathCard_Empty("empty"));
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
        if (x < 0 || x >= GRID_MAX_WIDTH || y < 0 || y >= GRID_MAX_HEIGHT) {
            return null;
        } else {
            return grid[x][y];
        }
    }

    public Grid[][] getGrid() {
        return grid;
    }
}
