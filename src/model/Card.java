package model;

public abstract class Card extends Drawable{
    private String id;
    private Board board;
    private boolean discarded;
    /*private Player player[];*/

    public Card(String imageResource, String id){
        super(imageResource);
        this.id = id;
    }

    public abstract void cardAction();

    public String getId() {
        return id;
    }

}

