package model;

public class GoalCard extends Card {
    private boolean hidden;
    private boolean gold;

    public GoalCard(String imgResource) {
        super(imgResource);
        if (imgResource.contains("gold")) {
            gold = true;
        } else {
            gold = false;
        }
        hidden = true;
    }

    public boolean isHidden() {
        return hidden;
    }

    public boolean isGold() {
        return gold;
    }
}