package model.personalcard;

import model.PersonalCard;
import model.Player;

/**
 * Created by Ka Kyen Lam on 23/05/2017.
 */
public class PersonalCard_Sick extends PersonalCard {
    /**
     * @param imageResource location of image file for card
     * @param id
     */
    public PersonalCard_Sick(String imageResource, String id) {
        super(imageResource, id);
    }

    /**
     * Method for actions different types of cards might have. E.g. Breaking tools
     *
     * @param target
     */
    @Override
    public boolean cardAction(Object[] target) {
        return false;
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Player targetPlayer = (Player) target[0];
        Player targetPlayerBeforeCardAction = (Player) undoExtraInformation[0];
        targetPlayer.setSickTurn(targetPlayerBeforeCardAction.getSickTurn());
    }
}
