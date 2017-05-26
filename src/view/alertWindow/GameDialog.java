package view.alertWindow;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Optional;

/**
 * A library class that is used to show dialog window with input for user to put into.
 * Credit to http://code.makery.ch/blog/javafx-dialogs-official/, which is what askUser() method is based on
 *
 * @author David Limantoro s3503728 on 2017/05/24.
 */
public class GameDialog {

    public static String CANCEL_DEFAULT_VALUE_RETURNED = "ERROR";

    /**
     * Creates a dialog window that contains one text field that requires user input.
     *
     * @param messageTitle  The title of the dialog window
     * @param messageHeader The header of the dialog window
     * @return The content of the dialog input
     */
    public static String askUser(String messageTitle, String messageHeader) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(messageTitle);
        dialog.setHeaderText(messageHeader);

        // Set the button types.
        ButtonType confirm = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(confirm, ButtonType.CANCEL);

        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(80);
        grid.setPadding(new Insets(20, 20, 20, 20));

        TextField filenameInput = new TextField();
        filenameInput.setPromptText("Filename");

        grid.add(new Label("Filename:"), 0, 0);
        grid.add(filenameInput, 1, 0);

        Node confirmButton = dialog.getDialogPane().lookupButton(confirm);
        confirmButton.setDisable(true);

        filenameInput.textProperty().addListener((observable, oldValue, newValue) -> {
            confirmButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == confirm) {
                return filenameInput.getText();
            } else {
                return CANCEL_DEFAULT_VALUE_RETURNED;
            }
        });

        Optional<String> result = dialog.showAndWait();
        return result.get();
    }

    /**
     * Creates a dialog window that presents two button to choose
     *
     * @param message Message to display to user
     * @param option1 Message for first button
     * @param option2 Message for second button
     * @return The string contained in the chosen button menu.
     */
    public static String askUserTwoOptions(String message, String option1, String option2) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, message, new ButtonType(option1), new ButtonType(option2));
        Optional<ButtonType> reply = alert.showAndWait();
        return reply.get().getText();
    }

    /**
     * Creates a dialog window that asks user for which theme to use for the game
     *
     * @return The string contained in the chosen button menu.
     */
    public static String askWhichTheme() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Choose theme", new ButtonType("Theme 1"), new ButtonType("Theme 3"));
        Optional<ButtonType> reply = alert.showAndWait();
        return reply.get().getText();
    }
}
