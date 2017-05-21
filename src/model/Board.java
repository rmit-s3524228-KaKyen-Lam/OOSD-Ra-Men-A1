package model;

import model.goalcard.GoalCard_Coal;
import model.goalcard.GoalCard_Gold;
import model.pathcard.PathCard_Cross;
import model.pathcard.PathCard_Empty;

import java.util.*;

/*http://stackoverflow.com/questions/9369368/2d-arraylist-in-java
 https://www.thoughtco.com/generating-unique-random-numbers-2034208
 http://stackoverflow.com/questions/5262308/how-do-implement-a-breadth-first-traversal

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
    private int x;
    private int y;

    private ArrayList<Grid> goalPos;

    private Grid[][] grid = new Grid[gridMaxWidth][gridMaxHeight];
    private boolean[][] isFilled;
    private Grid goldLocation;
    Queue<Node> queue = new LinkedList<>();
    private ArrayList<Grid> goalList = new ArrayList<>();

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


    public boolean inputCheck(int width, int height) {
        if (width < 0 || width > gridMaxWidth) {
            System.out.println("Invalid width. Please input width between 0 - " + gridMaxWidth);
            return false;
        } else if (height < 0 || height > gridMaxHeight) {
            System.out.println("Invalid height. Please input width between 0 - " + gridMaxHeight);
            return false;
        } else if (isFilled[width][height] == true) {
            System.out.println("Invalid position. That grid has been occupied.");
            return false;
        } else {
            return true;
        }

    }

    public String configureGoalPos(int amount, String name, int width, int height) {
        int i = 0;

        while (i < amount) {

            boolean validInput = inputCheck(width, height);

            if (validInput) {
                isFilled[width][height] = true;
                if (name.equals("coal")) {
                    grid[width][height] = new Grid(width, height, CardFlyweight.getCard("COAL", 0));
                    i++;
                    return ("Coal created at (" + width + ", " + height + ")");
                } else if (name.equals("gold")) {
                    grid[width][height] = new Grid(width, height, CardFlyweight.getCard("GOLD", 0));
                    goldLocation = grid[width][height];
                    i++;
                    return ("Gold created at (" + width + ", " + height + ")");

                }
            }
        }
        return ("Internal error. No loop triggered.");
    }

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


    public void breadth(Node root) {
        if (root == null)
            return;
        queue.clear();
        queue.add(root);


        while (!queue.isEmpty()) {
            Node node = queue.remove();
            System.out.print(node.root + " ");

            if (!node.left().root.isDisabled() && ((PathCard) node.root.getCard()).isWest() && ((PathCard) node.left().root.getCard()).isEast()) {
                x = node.left().root.getX();
                y = node.left().root.getY();
                grid[x][y].setConnectedToMain(true);
                queue.add(node.left());
            }

            if (!node.right().root.isDisabled() && ((PathCard) node.root.getCard()).isEast() && ((PathCard) node.right().root.getCard()).isWest()) {
                x = node.right().root.getX();
                y = node.right().root.getY();
                grid[x][y].setConnectedToMain(true);
                queue.add(node.right());
            }

            if (!node.up().root.isDisabled() && ((PathCard) node.root.getCard()).isNorth() && ((PathCard) node.up().root.getCard()).isSouth()) {
                x = node.up().root.getX();
                y = node.up().root.getY();
                grid[x][y].setConnectedToMain(true);
                queue.add(node.up());
            }

            if (!node.down().root.isDisabled() && ((PathCard) node.root.getCard()).isSouth() && ((PathCard) node.down().root.getCard()).isNorth()) {
                x = node.down().root.getX();
                y = node.down().root.getY();
                grid[x][y].setConnectedToMain(true);
                queue.add(node.down());
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
                        grid[j][i] = new Grid(j, i, CardFlyweight.getCard("CROSS_SHAPE", 0));
//                        ((PathCard) (grid[j][i].getCard())).setValid(true);
                        grid[j][i].setConnectedToMain(false);
                        isFilled[j][i] = true;
                    } else {
                        grid[j][i] = new Grid(j, i, CardFlyweight.getCard("EMPTY", 0));
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
                    grid[j][i] = new Grid(j, i, CardFlyweight.getCard("COAL_HIDDEN", 0));
                } else if (i == startGoalYList.get(2) && j == startGoalX) {
                    grid[j][i] = new Grid(j, i, CardFlyweight.getCard("GOLD_HIDDEN", 0));
                    goldLocation = grid[j][i];
                } else if (i == startPathY && j == startPathX) {
                    grid[j][i] = new Grid(j, i, CardFlyweight.getCard("CROSS_SHAPE", 0));
                    grid[j][i].setConnectedToMain(true);
//                    ((PathCard) (grid[j][i].getCard())).setValid(true);
                } else {
                    grid[j][i] = new Grid(j, i, CardFlyweight.getCard("EMPTY", 0));
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
