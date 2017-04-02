package model;

import java.util.ArrayList;

/**
 * Created by Lam Ka-Kyen on 30-Mar-17.
 */
public class Player {

    private int score;
    private String role;
    private String[] brokenTool;
    private ArrayList<Card> hand;

    public Player(int score, String role, String[] brokenTool, ArrayList<Card> hand) {
        this.score = score;
        this.role = role;
        this.brokenTool = brokenTool;
        this.hand = hand;
    }

    public boolean useCard(Card card) {
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

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int getSelectedCard(Card card) {
        if (hand.contains(card)) {
             return hand.indexOf(card);
        }
        return -1;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

}
