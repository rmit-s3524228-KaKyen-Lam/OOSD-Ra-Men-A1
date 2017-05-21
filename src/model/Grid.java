package model;

import model.pathcard.PathCard_Empty;

/**
 * Grid class containing x-coordinate, y-coordinate, and card
 *
 * @author Ka Kyen Lam s3524228
 */
public class Grid {

    private int x;
    private int y;
    private Card card;

    /**
     * Creates a single grid object that contains a card and location information
     *
     * @param x    x location on the board
     * @param y    y location on the board
     * @param card place to store Card object
     */
    Grid(int x, int y, Card card) {
        this.x = x;
        this.y = y;
        this.card = card;
    }

    /**
     * Remove card from this grid
     */
    public void removeCardonGrid() {
        this.card = new PathCard_Empty();
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}