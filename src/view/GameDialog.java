package view;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

/**
 * @author David Limantoro s3503728 on 2017/05/24.
 */
public class GameDialog {

    public static String CANCEL_DEFAULT_VALUE_RETURNED = "ERROR";

    /**
     * @param messageTitle
     * @param messageHeader
     * @return
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

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(confirm);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        filenameInput.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
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
}
