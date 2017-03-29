package model;

public class GoalCard extends Card {
    private boolean hidden;
    private boolean gold;

    public GoalCard(String type, String imgResource) {
        super(imgResource);
        if (type.equals("gold")) {
            gold = true;
        } else {
            gold = false;
        }
        hidden = true;
    }
}