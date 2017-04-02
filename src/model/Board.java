package model;

import java.util.*;

import static model.GoalCard.*;

/*http://stackoverflow.com/questions/9369368/2d-arraylist-in-java
 * */

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
    private boolean goldDiscovered;
    private Card coalCard;
    private Card goldCard;
    private Card startCard;
    private Grid gridElem;

    public Grid[][] getGrid() {
        return grid;
    }

    public void initBoard() {

        for (int i = 0; i < GRID_MAX_HEIGHT; i++) {
            for (int j = 0; j < GRID_MAX_WIDTH; j++) {

                boolean coal_one_pos_x = j == START_GOAL_X;
                boolean coal_one_pos_y = i == COAL_ONE_Y;
                boolean coal_two_pos_x = j == START_GOAL_X;
                boolean coal_two_pos_y = i == COAL_TWO_Y;
                boolean gold_pos_x = j == START_GOAL_X;
                boolean gold_pos_y = i == GOLD_Y;
                boolean start_pos_x = j == START_X;
                boolean start_pos_y = i == START_Y;

                if ((coal_one_pos_y && coal_one_pos_x) || (coal_two_pos_y && coal_two_pos_x)) {
                    coalCard = new GoalCard("resources/Goal.png", "resources/Coal.png", "200");
                } else if (gold_pos_y && gold_pos_x) {
                    goldCard = new GoalCard("resources/Goal.png", "resources/Gold.png", "201");
                } else if (start_pos_y && start_pos_x) {
                    startCard = new PathCard(PathCard.CROSS_SHAPE, "resources/Shape_Plus.png", "201");
                } else {
                    System.out.printf("[]");
                }
                System.out.printf("\t");
            }
            System.out.println();
        }

    }

    public void placeCard(Card card, int x, int y) {

    }

    public void removeCard(int x, int y) {

    }

    public void goldIsFound() {

    }

    public static void main(String[] args) {
        Board bd = new Board();
        bd.initBoard();
    }


}
