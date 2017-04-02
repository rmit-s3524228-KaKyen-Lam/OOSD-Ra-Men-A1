package model;

import java.util.*;

import static model.GoalCard.*;

/*http://stackoverflow.com/questions/9369368/2d-arraylist-in-java
 https://www.thoughtco.com/generating-unique-random-numbers-2034208*/

/**
 * Board of game
 *
 * @author Lam Ka-Kyen
 */
public class Board {

    private static final int GRID_MAX_WIDTH = 9;
    private static final int GRID_MAX_HEIGHT = 7;
    private static final int START_GOAL_X = 8;
    private static ArrayList<Integer> START_GOAL_Y = new ArrayList<>();
    private static int GOAL_ONE = 1;
    private static int GOAL_TWO = 3;
    private static int GOAL_THREE = 5;
    private static final int START_X = 0;
    private static final int START_Y = 3;


    private Grid[][] grid = new Grid[GRID_MAX_WIDTH][GRID_MAX_HEIGHT];
    private Grid goldLocation;

    public void randomPosition() {
        START_GOAL_Y.add(GOAL_ONE);
        START_GOAL_Y.add(GOAL_TWO);
        START_GOAL_Y.add(GOAL_THREE);

        Collections.shuffle(START_GOAL_Y);
    }

    public void initBoard() {
        randomPosition();
        for (int i = 0; i < GRID_MAX_HEIGHT; i++) {
            for (int j = 0; j < GRID_MAX_WIDTH; j++) {
                if ((i == START_GOAL_Y.get(1) || i == START_GOAL_Y.get(2)) && j == START_GOAL_X) {
                    grid[j][i] = new Grid(j, i, new GoalCard("resources/Goal.png", "resources/Coal.png", "coal"));
                } else if (i == START_GOAL_Y.get(3) && j == START_GOAL_X) {
                    grid[j][i] = new Grid(j, i, new GoalCard("resources/Goal.png", "resources/Gold.png", "gold"));
                    goldLocation = grid[j][i];
                } else if (i == START_Y && j == START_X) {
                    grid[j][i] = new Grid(j, i, new PathCard(PathCard.CROSS_SHAPE, "resources/Shape_Plus.png", "cross") );
                } else {
                    grid[j][i] = new Grid(j, i, new PathCard(PathCard.EMPTY, "resources/Unexplored.png", "empty") );
                }
            }
            System.out.println();
        }
    }

    public Grid[][] getGrid() {
        return grid;
    }

    public boolean goldIsFound() {
        return !((GoalCard)(goldLocation.getCard())).isHidden();
    }

    public void placeCardOnLocation(int x, int y, Card card) {
        grid[x][y].setCard(card);
    }

    public static void main(String[] args) {
        Board bd = new Board();
        bd.initBoard();
    }
}
