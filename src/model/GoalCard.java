package model;

/**
 * This is the class that contains all the information regarding the game itself.
 *
 * @author Fabio Monsalve s3585826
 */
public class GoalCard extends Card {
    private boolean hidden = true;
    private boolean gold = false;
    private String concealedImageResource;

    public GoalCard(String concealedImageResource, String imageSource, String id) {
        super(imageSource, id);
        this.concealedImageResource = concealedImageResource;
        if (imageSource.contains("Gold")) {
            gold = true;
        }
    }

    @Override
    public void cardAction() {

    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isGold() {
        return gold;
    }

    public String getConcealedImageResource() {
        return concealedImageResource;
    }

    public void setGold(boolean gold) {
        this.gold = gold;
    }
}