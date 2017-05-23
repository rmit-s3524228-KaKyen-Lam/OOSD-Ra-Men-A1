package model.personalcard;

import model.PersonalCard;
import model.Player;

/**
 * Created by Ka Kyen Lam on 23/05/2017.
 */
public class PersonalCard_Swap_Role extends PersonalCard {
    /**
     * @param imageResource location of image file for card
     * @param id
     */
    public PersonalCard_Swap_Role(String imageResource, String id) {
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
            String role = player.getRole();
            if (role.equals(Player.ROLE_MINER)) {
                ((Player) target[0]).setRole(Player.ROLE_SABOTEUR);
            } else {
                ((Player) target[0]).setRole(Player.ROLE_MINER);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void undoCardAction(Object[] target, Object[] undoExtraInformation) {
        Player player = (Player) target[0];
        try {
            String role = player.getRole();
            if (role.equals(Player.ROLE_MINER)) {
                ((Player) target[0]).setRole(Player.ROLE_SABOTEUR);
            } else {
                ((Player) target[0]).setRole(Player.ROLE_MINER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
