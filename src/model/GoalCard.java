package model;

public class GoalCard extends Card {
    private boolean hidden;
    private boolean gold;
    private String concealedImageResource;

    public GoalCard() {
    }

    public GoalCard(String concealedImageResource, String imageSource, String id) {
        super(imageSource, id);
    }
}