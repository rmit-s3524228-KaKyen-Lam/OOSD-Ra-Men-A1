package model;

public class Grid {


    private int x;
    private int y;
    private Card card;

    public Grid(int x, int y, Card card) {
        this.x = x;
        this.y = y;
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void removeCardonGrid() {
        this.card = new PathCard(PathCard.EMPTY, "resources/Unexplored.png", "empty");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}