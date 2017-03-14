package model;

import java.util.*;

/*http://stackoverflow.com/questions/9369368/2d-arraylist-in-java
 * */

/**
 * Board of game
 *
 * @author Lam Ka-Kyen
 *
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

    private ArrayList<Card>[][] grid;
    private boolean goldDiscovered;


    public void initBoard() {
        for (int i = 0; i < GRID_MAX_HEIGHT; i++) {
            for (int j = 0; j < GRID_MAX_WIDTH; j++) {
                if ((i ==  COAL_ONE_Y && j == COAL_ONE_X) || (i == COAL_TWO_Y && j == COAL_TWO_X)) {
                    System.out.printf("coal");
                    System.out.print("\t");
                } else if (i == GOLD_Y && j == GOLD_X) {
                    System.out.printf("gold");
                    System.out.print("\t");
                } else if (i == START_Y && j == START_X) {
                    System.out.printf("s");
                    System.out.print("\t");
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
