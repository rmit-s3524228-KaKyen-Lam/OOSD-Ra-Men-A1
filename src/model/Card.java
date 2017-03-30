package model;

public class Card extends Drawable{
    private String id;
    private Board board;
    private boolean discarded;
    /*private Player player[];*/

    public Card(String imageResource, String id){
        super(imageResource);
        this.id = id;
    }
    public Card(){

    }

    public static void main(String[] args) {
    }

    public static void cardAction() {

    }
    public String getId() {
        return id;
    }

}

