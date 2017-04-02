package model;

/**
 * Board of game
 *
 * @author Lam Ka-Kyen
 */
public class Board {

    public static final int GRID_MAX_WIDTH = 9;
    public static final int GRID_MAX_HEIGHT = 7;
    public static final int START_GOAL_X = 8;
    public static int COAL_ONE_Y = 1;
    public static int COAL_TWO_Y = 3;
    public static int GOLD_Y = 5;
    public static final int START_X = 0;
    public static final int START_Y = 3;


    private Grid[][] grid = new Grid[GRID_MAX_WIDTH][GRID_MAX_HEIGHT];
    private Grid goldLocation;


    public void initBoard() {

        for (int i = 0; i < GRID_MAX_HEIGHT; i++) {
            for (int j = 0; j < GRID_MAX_WIDTH; j++) {
                if ((i == COAL_ONE_Y || i == COAL_TWO_Y) && j == START_GOAL_X) {
                    grid[j][i] = new Grid(j, i, new GoalCard("resources/Goal.png", "resources/Coal.png", "coal"));
                } else if (i == GOLD_Y && j == START_GOAL_X) {
                    grid[j][i] = new Grid(j, i, new GoalCard("resources/Goal.png", "resources/Gold.png", "gold"));
                    goldLocation = grid[j][i];
                } else if (i == START_Y && j == START_X) {
                    grid[j][i] = new Grid(j, i, new PathCard(PathCard.CROSS_SHAPE, "resources/Shape_Plus.png", "cross"));
                } else {
                    grid[j][i] = new Grid(j, i, new PathCard(PathCard.EMPTY, "resources/Unexplored.png", "empty"));
                }
            }
            System.out.println();
        }
    }

    public Grid[][] getGrid() {
        return grid;
    }

    public Grid getGridAtLocation(int x, int y) {
        if (x < 0 || x >= GRID_MAX_WIDTH || y < 0 || y >= GRID_MAX_HEIGHT) {
            return null;
        } else {
            return grid[x][y];
        }
    }

    public boolean goldIsFound() {
        return !((GoalCard) (goldLocation.getCard())).isHidden();
    }

    public void placeCardOnLocation(int x, int y, Card card) {
        grid[x][y].setCard(card);
    }

    public static void main(String[] args) {
        Board bd = new Board();
        bd.initBoard();
    }
}
