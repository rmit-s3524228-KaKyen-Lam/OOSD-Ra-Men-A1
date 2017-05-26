package model.card.personalcard;

import model.Player;

/**
 * Created by Ka Kyen Lam on 23/05/2017.
 */
public class PersonalCard_Break_Pickaxe extends PersonalCard {
    /**
     * @param id
     */
    public PersonalCard_Break_Pickaxe(String id) {
        super("resources/Personal_Break_Pickaxe.png", id);
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
            return player.addStatus("pickaxe");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Player player = (Player) target[0];
        try {
            player.removeStatus("pickaxe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
