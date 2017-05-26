package view.alertWindow;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * A library class that is used to show notification window with message.
 *
 * @author David Limantoro s3503728
 */
public class GameNotification {

    /**
     * Shows error an alert window containing error icon with message
     *
     * @param message Message to display to user
     */
    public static void showAlertBoxErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    /**
     * Shows error an alert window containing notification icon with message
     *
     * @param message Message to display to user
     */
    public static void showAlertBoxNotificationMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }
}
