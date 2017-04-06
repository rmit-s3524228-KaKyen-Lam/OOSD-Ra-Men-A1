package model;

import java.util.ArrayList;

/**
 * Player containing player details.
 * <p>
 * Created by Ka Kyen Lam on 30-Mar-17.
 */
public class Player {

    private int score;
    private String role;
    private ArrayList<String> brokenTool;
    private ArrayList<Card> hand;

    public Player(int score, String role, ArrayList<String> brokenTool, ArrayList<Card> hand) {
        this.score = score;
        this.role = role;
        this.brokenTool = brokenTool;
        this.hand = hand;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Setting up hand in beginning of game
     *
     * @param hand Initial starting hand
     */
    public void setHand(Card[] hand) {
        this.hand = new ArrayList<Card>();
        for (Card newCard : hand) {
            this.hand.add(newCard);
        }
    }

    /**
     * Get card selected on hand
     *
     * @param card Selected card
     * @return Index of selected card
     */
    public int getSelectedCard(Card card) {
        if (hand.contains(card)) {
            return hand.indexOf(card);
        }
        return -1;
    }

    /**
     * Add card to hand
     *
     * @param card Card to be added
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Remove card from hand
     *
     * @param card Card to be removed
     */
    public void removeCard(Card card) {
        hand.remove(card);
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

    public ArrayList<String> getBrokenTool() {
        return brokenTool;
    }

    public void setBrokenTool(ArrayList<String> brokenTool) {
        this.brokenTool = brokenTool;
    }

}
