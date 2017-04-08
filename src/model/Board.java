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
 * <p>
 * Created by Ka Kyen Lam on 30-Mar-17.
 */
public class Board {

    public static final int GRID_MAX_WIDTH = 9;
    public static final int GRID_MAX_HEIGHT = 7;
    private static final int START_GOAL_X = 8;
    private static ArrayList<Integer> START_GOAL_Y = new ArrayList<>();
    private static int GOAL_ONE = 1;
    private static int GOAL_TWO = 3;
    private static int GOAL_THREE = 5;
    private static final int START_X = 0;
    private static final int START_Y = 3;

    private Grid[][] grid = new Grid[GRID_MAX_WIDTH][GRID_MAX_HEIGHT];
    private Grid goldLocation;

    /**
     * Randomise starting positions of GoalCards
     */
    public void randomPosition() {
        START_GOAL_Y.add(GOAL_ONE);
        START_GOAL_Y.add(GOAL_TWO);
        START_GOAL_Y.add(GOAL_THREE);

        Collections.shuffle(START_GOAL_Y);
    }

    /**
     * Initialise board
     */
    public void initBoard() {
        randomPosition();
        for (int i = 0; i < GRID_MAX_HEIGHT; i++) {
            for (int j = 0; j < GRID_MAX_WIDTH; j++) {
                if ((i == START_GOAL_Y.get(0) || i == START_GOAL_Y.get(1)) && j == START_GOAL_X) {
                    grid[j][i] = new Grid(j, i, new GoalCard_Coal(("coal")));
                } else if (i == START_GOAL_Y.get(2) && j == START_GOAL_X) {
                    grid[j][i] = new Grid(j, i, new GoalCard_Gold(("gold")));
                    goldLocation = grid[j][i];
                } else if (i == START_Y && j == START_X) {
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
    public boolean goldIsFound() {
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
