package model;

/**
 * "Goal" card class for the three goal cards used in the game. When the card is hidden,
 * concealedImageResource is used.
 *
 * @author Fabio Monsalve s3585826
 */
public abstract class GoalCard extends Card {

    // All goal cards will initially be hidden
    private boolean hidden = true;
    private boolean gold = false;
    private String concealedImageResource;

    /**
     * Creates goal card containing concealed image and image resource.
     *
     * @param concealedImageResource location of image to indicate a concealed goal card
     * @param imageSource            location of image to indicate a revealed goal card
     */
    public GoalCard(String concealedImageResource, String imageSource, String id) {
        super(imageSource, id);
        this.concealedImageResource = concealedImageResource;
        if (imageSource.contains("Gold")) {
            gold = true;
        }
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

    @Override
    public void cardAction(Object[] target) {

    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {

    }

}