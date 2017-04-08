package view;

import javafx.scene.control.Alert;

/**
 * This is a class that contains static methods to display notification message in-game.
 *
 * @author David Limantoro s3503728
 */
public class Notification {

    /**
     * @param message
     */
    public static void showAlertBoxErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.show();
    }

    /**
     * @param message
     */
    public static void showAlertBoxNotificationMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.show();
    }
}
