package model.personalcard;

import model.PersonalCard;
import model.Player;

/**
 * Created by Ka Kyen Lam on 23/05/2017.
 */
public class PersonalCard_Fix_Lantern extends PersonalCard {
    /**
     * @param id
     */
    public PersonalCard_Fix_Lantern(String id) {
        super("resources/Personal_Fix_Lantern.png", id);
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
            return player.removeStatus("lantern");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Player player = (Player) target[0];
        try {
            player.addStatus("lantern");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
