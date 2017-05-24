package model.personalcard;

import model.PersonalCard;
import model.Player;

/**
 * Created by Ka Kyen Lam on 23/05/2017.
 */
public class PersonalCard_Fix_Pickaxe extends PersonalCard {
    /**
     * @param imageResource location of image file for card
     * @param id
     */
    public PersonalCard_Fix_Pickaxe(String imageResource, String id) {
        super(imageResource, id);
    }

    /**
     * Method for actions different types of cards might have. E.g. Breaking tools
     *
     * @param target
     */
    @Override
    public boolean cardAction(Object[] target) {
        Player player = (Player) target[0];
        try {
            return player.removeStatus("pickaxe");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Player player = (Player) target[0];
        try {
            player.addStatus("pickaxe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
