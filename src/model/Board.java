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

    private final int GRID_MAX_WIDTH = 9;
    private final int GRID_MAX_HEIGHT = 7;

    private final int COAL_ONE_X = 8;
    private final int COAL_ONE_Y = 1;


    private final int COAL_TWO_X = 8;
    private final int COAL_TWO_Y = 3;


    private final int GOLD_X = 8;
    private final int GOLD_Y = 5;


    private final int START_X = 0;
    private final int START_Y = 3;


    /* private ArrayList<Card>[][] grid;*/
    private boolean goldDiscovered;
    private Card card;


    public void initBoard() {
        for (int i = 0; i < GRID_MAX_HEIGHT; i++) {
            for (int j = 0; j < GRID_MAX_WIDTH; j++) {

                boolean coal_one_pos_x = j == COAL_ONE_X;
                boolean coal_one_pos_y = i == COAL_ONE_Y;
                boolean coal_two_pos_x = j == COAL_TWO_X;
                boolean coal_two_pos_y = i == COAL_TWO_Y;
                boolean gold_pos_x = j == GOLD_X;
                boolean gold_pos_y = i == GOLD_Y;
                boolean start_pos_x = j == START_X;
                boolean start_pos_y = i == START_Y;

                if ((coal_one_pos_y && coal_one_pos_x) || (coal_two_pos_y && coal_two_pos_x)) {
                    System.out.print("coal");
                    System.out.printf("\t");
                } else if (gold_pos_y && gold_pos_x) {
                    System.out.print("gold");
                    System.out.printf("\t");
                } else if (start_pos_y && start_pos_x) {
                    System.out.print("s");
                    System.out.printf("\t");
                } else {
                    System.out.print("[]" + "\t");
                }
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
