package model;

public class Card {
    private String id;
    /*private Player player[];*/
    private boolean discarded;

    public Card(String id) {
            this.id = id;
    }

    public String display () {
        return id;
    }
}
