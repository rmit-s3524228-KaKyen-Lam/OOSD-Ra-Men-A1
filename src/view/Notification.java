package view;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * This is a class that contains static methods to display notification message in-game.
 *
 * @author David Limantoro s3503728
 */
public class Notification {

    /**
     * @param message
     * @param option1
     * @param option2
     * @return The string contained in the button menu
     */
    public static String askUser2Options(String message, String option1, String option2) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, new ButtonType(option1), new ButtonType(option2));
        Optional<ButtonType> reply = alert.showAndWait();
        return reply.get().getText();
    }

    /**
     * @return The string contained in the button menu
     */
    public static String askWhichTheme() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Choose theme", new ButtonType("Theme 1"), new ButtonType("Theme 2"), new ButtonType("Theme 3"));
        Optional<ButtonType> reply = alert.showAndWait();
        return reply.get().getText();
    }

    /**
     * @param message Message to display to user
     */
    public static void showAlertBoxErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    /**
     * @param message Message to display to user
     */
    public static void showAlertBoxNotificationMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
        alert.showAndWait();
    }
}
