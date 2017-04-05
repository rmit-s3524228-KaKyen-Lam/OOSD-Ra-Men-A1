package model;

/**
 * "Goal" card class for the three goal cards used in the game
 *
 * @author Fabio Monsalve s3585826
 */
public class GoalCard extends Card {

    // All goal cards will initially be hidden
    private boolean hidden = true;
    private boolean gold = false;
    private String concealedImageResource;

    /**
     *
     * @param concealedImageResource location of image to indicate a concealed goal card
     * @param imageSource location of image to indicate a revealed goal card
     */
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