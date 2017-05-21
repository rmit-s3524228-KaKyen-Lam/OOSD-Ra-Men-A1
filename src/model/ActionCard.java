package model;

/**
 * Abstract class for all the 6 different action cards
 */
public abstract class ActionCard extends Card {
  /**
   *
   * @param imageResource location of image
   */
  public ActionCard(String imageResource) {
    super(imageResource);
  }
}