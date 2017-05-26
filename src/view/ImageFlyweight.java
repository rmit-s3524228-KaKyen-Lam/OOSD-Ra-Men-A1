package view;

import javafx.scene.image.Image;
import model.card.Card;

/**
 * @author David Limantoro s3503728 on 2017/05/22.
 */
public interface ImageFlyweight {
    public Image requestImage(Card card);
}
