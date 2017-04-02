package model;

/**
 * Grid class containing x-coordinate, y-coordinate, and card
 * <p>
 * Created by Ka Kyen Lam on 30-Mar-17.
 */
public class Grid {

    private int x;
    private int y;
    private Card card;

    public Grid(int x, int y, Card card) {
        this.x = x;
        this.y = y;
        this.card = card;
    }

    /**
     * Remove card on selected grid
     */
    public void removeCardonGrid() {
        this.card = new PathCard(PathCard.EMPTY, "resources/Unexplored.png", "empty");
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