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
    private boolean connectedToMain;
    private boolean isDisabled;

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
        this.connectedToMain = false;
        this.isDisabled = false;
    }

    /**
     * Remove card from this grid
     */
    public void removeCardonGrid() {
        this.card = CardFlyweight.getCard("EMPTY", 0);
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

    public boolean isConnectedToMain() {
        return connectedToMain;
    }

    public void setConnectedToMain(boolean connectedToMain) {
        this.connectedToMain = connectedToMain;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}