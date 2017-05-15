package model.pathcard;

import model.PathCard;

/**
 * T shaped path card with hole
 *
 * @author Fabio Monsalve Duque s3585826
 */
public class PathCard_T_Dead extends PathCard {

  public PathCard_T_Dead(String id) {
    super(true, false, true, true, false, "resources/Shape_T_Hole.png", id);
  }

  @Override
  public void cardAction() {

  }
}