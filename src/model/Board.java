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


    private Object[][] grid = new Object[GRID_MAX_WIDTH][GRID_MAX_HEIGHT];
    private boolean goldDiscovered;
    private Card coalCard;
    private Card goldCard;
    private Card startCard;
    private Grid gridElem;

    Card coal = new GoalCard("Coal");
    Card gold = new GoalCard("Gold");
    Card start = new PathCard ("Start");

    public void initBoard() {

        for (int i = 0; i < GRID_MAX_HEIGHT; i++) {
           for (int j = 0; j < GRID_MAX_WIDTH; j++) {

               Card coal = new GoalCard("Coal");

               if ((i == COAL_ONE_Y || i == COAL_TWO_Y)&& j == START_GOAL_X) {
                   grid[j][i] = coal.display();
               } else if (i == GOLD_Y  && j == START_GOAL_X) {
                   grid[j][i] = gold.display();
               } else if (i == GOLD_Y  && j == START_GOAL_X) {
                   grid[j][i] = start.display();
               } else {
                   grid[j][i] = 0;
               }
               if (grid[j][i].equals(0)) {
                   System.out.printf("[]\t");
               }


//                boolean coal_one_pos_x = j == START_GOAL_X;
//                boolean coal_one_pos_y = i == COAL_ONE_Y;
//                boolean coal_two_pos_x = j == START_GOAL_X;
//                boolean coal_two_pos_y = i == COAL_TWO_Y;
//                boolean gold_pos_x = j == START_GOAL_X;
//                boolean gold_pos_y = i == GOLD_Y;
//                boolean start_pos_x = j == START_X;
//                boolean start_pos_y = i == START_Y;
//
//                if ((coal_one_pos_y && coal_one_pos_x) || (coal_two_pos_y && coal_two_pos_x)) {
//                    coalCard = new GoalCard("coal");
//                } else if (gold_pos_y && gold_pos_x) {
//                    goldCard = new GoalCard("gold");
//                } else if (start_pos_y && start_pos_x) {
//                    startCard = new PathCard("s");
//                } else {
//                    System.out.printf("[]");
//                }
//                System.out.printf("\t");
            }
           System.out.println();
        }

    }

    public void placeCard(Card card) {
        System.out.printf(card.display());
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
