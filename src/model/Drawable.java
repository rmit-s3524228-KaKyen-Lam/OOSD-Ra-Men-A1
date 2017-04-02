package model;

public abstract class Drawable{

    protected String imageResource = "";

    public Drawable(){
    }

    public Drawable(String imageResource){
        this.imageResource = imageResource;
    }

    public String getImageResource() {
        return imageResource;
    }
}
