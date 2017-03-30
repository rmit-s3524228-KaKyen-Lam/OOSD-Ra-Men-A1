package model;

/**
 * Created by Lam Ka-Kyen on 30-Mar-17.
 */
public class Player {

    private int score;
    private String role;
    private String[] brokenTool;
    private Card[] hand;

    public Player (int score, String role, String[] brokenTool, Card[] hand) {
        this.score = score;
        this.role = role;
        this.brokenTool = brokenTool;
        this.hand = hand;
    }

    public boolean useCard (Card card) {
        return false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] getBrokenTool() {
        return brokenTool;
    }

    public void setBrokenTool(String[] brokenTool) {
        this.brokenTool = brokenTool;
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

}
