package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Player containing player details.
 *
 * @author Ka Kyen Lam s3524228
 */
public class Player {

    private int score;
    private String role;
    private ArrayList<String> brokenTool;
    private ArrayList<Card> hand;

    /**
     * Creates a player with initial values, if any (in case if the game implements save/load feature)
     *
     * @param score      score value of the player
     * @param role       "saboteur" or "miner"
     * @param brokenTool ArrayList of broken tools (this should be an empty ArrayList by default)
     * @param hand       The cards of the player object is holding
     */
    public Player(int score, String role, ArrayList<String> brokenTool, ArrayList<Card> hand) {
        this.score = score;
        this.role = role;
        this.brokenTool = brokenTool;
        this.hand = hand;
    }

    /**
     * Add card to hand
     *
     * @param card Card to be added
     */
    void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Remove card from hand
     *
     * @param card Card to be removed
     */
    void removeCard(Card card) {
        hand.remove(card);
    }

    /**
     * Setting up hand in beginning of game
     *
     * @param hand Initial starting hand
     */
    void setHand(Card[] hand) {
        this.hand = new ArrayList<>();
        Collections.addAll(this.hand, hand);
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

    public ArrayList<Card> getHand() {
        return hand;
    }
}
