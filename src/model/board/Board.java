package model.board;

import model.CardFlyweight;
import model.card.Card;
import model.card.goalcard.GoalCard;
import model.card.pathcard.PathCard;
import model.card.pathcard.PathCard_Empty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*http://stackoverflow.com/questions/9369368/2d-arraylist-in-java
 https://www.thoughtco.com/generating-unique-random-numbers-2034208
 http://stackoverflow.com/questions/5262308/how-do-implement-a-breadth-first-traversal
*/

/**
 * Game board containing Grids.
 *
 * @author Ka Kyen Lam s3524228.
 */
public class Board implements Serializable {

    public static final int MAX_ALLOWED_WIDTH = 9;
    public static final int MAX_ALLOWED_HEIGHT = 7;
    public static final int MIN_ALLOWED_WIDTH = 4;
    public static final int MIN_ALLOWED_HEIGHT = 4;
    public static int gridMaxWidth = 9;
    public static int gridMaxHeight = 7;

    private int goalNo;
    private int startGoalX = 8;
    private int startGoalY1 = 1;
    private int startGoalY2 = 3;
    private int startGoalY3 = 5;
    public static int startPathX = 0;
    public static int startPathY = 3;
    private int x;
    private int y;

    private ArrayList<Grid> goalPos;

    private Grid[][] grid = new Grid[gridMaxWidth][gridMaxHeight];
    private boolean[][] isFilled;
    private Grid goldLocation;
    Queue<Node> queue = new LinkedList<>();
    private ArrayList<Grid> goalList = new ArrayList<>();

    /**
     * Configure board based on user input of width and height
     *
     * @param widthMax  board width
     * @param heightMax board height
     */
    public void configureBoard(int widthMax, int heightMax) {
        gridMaxWidth = widthMax;
        gridMaxHeight = heightMax;
        grid = new Grid[gridMaxWidth][gridMaxHeight];
        isFilled = new boolean[gridMaxWidth][gridMaxHeight];

        for (int i = 0; i < gridMaxHeight; i++) {
            for (int j = 0; j < gridMaxWidth; j++) {
                isFilled[j][i] = false;
            }
        }
    }

    /**
     * Configure position of goal cards based on user input of width and height
     *
     * @param name name of goal card ("coal" and "gold")
     * @param width width position of goal card
     * @param height height position of goal card
     * @return
     */
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

                grid[width][height] = new Grid(width, height, CardFlyweight.getCard("GOAL_COAL_HIDDEN", 0));
                return ("Coal created at (" + width + ", " + height + ")");

            } else if (name.equals("gold")) {

                grid[width][height] = new Grid(width, height, CardFlyweight.getCard("GOAL_GOLD_HIDDEN", 0));
                goldLocation = grid[width][height];

                return ("Gold created at (" + width + ", " + height + ")");
            }
        }
        return ("Internal error. No loop triggered.");
    }

    /**
     * Calculation method to check if Path Cards are still connected to main
     */
    public void calculateBoard() {
        for (int i = 0; i < gridMaxHeight; i++) {
            for (int j = 0; j < gridMaxWidth; j++) {
                grid[j][i].setConnectedToMain(false);
            }
        }

        grid[startPathX][startPathY].setConnectedToMain(true);
        Node root = new Node(grid, grid[startPathX][startPathY]);
        breadth(root);
    }

    /**
     * Breadth-first-search used in checking adjacent Path Cards
     * @param root root
     */
    public void breadth(Node root) {
        if (root == null)
            return;
        queue.clear();
        queue.add(root);


        while (!queue.isEmpty()) {
            Node node = queue.remove();
            //System.out.print(node.root + " ");


            if (((PathCard) node.root.getCard()).isCentre()) {

                if (node.left() != null) {
                    if (!node.left().root.isConnectedToMain() && !node.left().root.isDisabled() && ((PathCard) node.root.getCard()).isWest() && ((PathCard) node.left().root.getCard()).isEast()) {
                        x = node.left().root.getX();
                        y = node.left().root.getY();
                        grid[x][y].setConnectedToMain(true);
                        queue.add(node.left());
                    }
                }

                if (node.right() != null) {
                    if (!node.right().root.isConnectedToMain() && !node.right().root.isDisabled() && ((PathCard) node.root.getCard()).isEast() && ((PathCard) node.right().root.getCard()).isWest()) {
                        x = node.right().root.getX();
                        y = node.right().root.getY();
                        grid[x][y].setConnectedToMain(true);
                        queue.add(node.right());
                    }
                }

                if (node.up() != null) {
                    if (!node.up().root.isConnectedToMain() && !node.up().root.isDisabled() && ((PathCard) node.root.getCard()).isNorth() && ((PathCard) node.up().root.getCard()).isSouth()) {
                        x = node.up().root.getX();
                        y = node.up().root.getY();
                        grid[x][y].setConnectedToMain(true);
                        queue.add(node.up());
                    }
                }

                if (node.down() != null) {
                    if (!node.down().root.isConnectedToMain() && !node.down().root.isDisabled() && ((PathCard) node.root.getCard()).isSouth() && ((PathCard) node.down().root.getCard()).isNorth()) {
                        x = node.down().root.getX();
                        y = node.down().root.getY();
                        grid[x][y].setConnectedToMain(true);
                        queue.add(node.down());
                    }
                }
            }
        }


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
                        grid[j][i] = new Grid(j, i, CardFlyweight.getCard("PATH_CROSS_SHAPE", 0));
                        grid[j][i].setConnectedToMain(true);
                        isFilled[j][i] = true;
                    } else {
                        grid[j][i] = new Grid(j, i, CardFlyweight.getCard("PATH_EMPTY", 0));
                        isFilled[j][i] = true;
                    }
                }
            }
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
        grid[x][y].setConnectedToMain(true);
        Grid westGrid = getGridAtLocation(x - 1, y);
        Grid northGrid = getGridAtLocation(x, y - 1);
        Grid eastGrid = getGridAtLocation(x + 1, y);
        Grid southGrid = getGridAtLocation(x, y + 1);
        if (westGrid != null && ((PathCard) card).isWest() && !westGrid.isConnectedToMain() &&
                !(westGrid.getCard() instanceof PathCard_Empty) && westGrid.getCard() instanceof PathCard) {
            calculateBoard();
        } else if (northGrid != null && ((PathCard) card).isNorth() && !northGrid.isConnectedToMain() &&
                !(northGrid.getCard() instanceof PathCard_Empty) && northGrid.getCard() instanceof PathCard) {
            calculateBoard();
        } else if (eastGrid != null && ((PathCard) card).isEast() && !eastGrid.isConnectedToMain() &&
                !(eastGrid.getCard() instanceof PathCard_Empty) && eastGrid.getCard() instanceof PathCard) {
            calculateBoard();
        } else if (southGrid != null && ((PathCard) card).isSouth() && !southGrid.isConnectedToMain() &&
                !(southGrid.getCard() instanceof PathCard_Empty) && southGrid.getCard() instanceof PathCard) {
            calculateBoard();
        }

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

    public boolean[][] getIsFilled() {
        return isFilled;
    }
}
