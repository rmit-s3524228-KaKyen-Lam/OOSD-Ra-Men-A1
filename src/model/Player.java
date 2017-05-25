package model;

import library.DeepCopier;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Player containing player details.
 *
 * @author Ka Kyen Lam s3524228
 */
public class Player extends Drawable implements Serializable {

    private String id;
    private int score;
    private String role;
    private ArrayList<String> brokenTool;
    private ArrayList<Card> hand;
    private Card recentlyDrawnCard;
    private int undoCount = 0;
    private int sickTurn = 0;

    public static String ROLE_MINER = "miner";
    public static String ROLE_SABOTEUR = "saboteur";

    /**
     * Creates a player with initial values, if any (in case if the game implements save/load feature)
     *
     * @param score      score value of the player
     * @param role       "saboteur" or "miner"
     * @param brokenTool ArrayList of broken tools (this should be an empty ArrayList by default)
     * @param hand       The cards of the player object is holding
     */
    public Player(int score, String role, ArrayList<String> brokenTool, ArrayList<Card> hand, String id) {
        super("resources/Player.png");
        this.id = id;
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
        recentlyDrawnCard = card;
        hand.add(card);
    }

    /**
     * @param cardID
     */
    void removeCard(String cardID) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getId().equals(cardID)) {
                hand.remove(i);
                return;
            }
        }
    }

    /**
     * Setting up hand in beginning of game
     *
     * @param hand Initial starting hand
     */
    void setHand(Card[] hand) {
        if (this.hand == null) {
            this.hand = new ArrayList<>();
        } else {
            this.hand.clear();
        }
        Collections.addAll(this.hand, hand);
    }

    public boolean addStatus(String status) {
        if (brokenTool.indexOf(status) == -1) {
            brokenTool.add(status);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeStatus(String status) {
        if (brokenTool.indexOf(status) > -1) {
            brokenTool.remove(status);
            return true;
        } else {
            return false;
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

    public void addScore(int score) {
        this.score += score;
    }

    public void incrementUndoCount() {
        undoCount++;
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

    public int getUndoCount() {
        return undoCount;
    }

    public Card getRecentlyDrawnCard() {
        return (Card) DeepCopier.copy(recentlyDrawnCard);
    }

    public void setRecentlyDrawnCard(Card recentlyDrawnCard) {
        this.recentlyDrawnCard = recentlyDrawnCard;
    }

    public int getSickTurn() {
        return sickTurn;
    }

    public void setSickTurn(int sickTurn) {
        this.sickTurn = sickTurn;
    }

    public String getId() {
        return id;
    }
}
