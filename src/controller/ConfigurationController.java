package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.board.Board;
import view.alertWindow.GameNotification;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for configurations window. It talks to the game controller
 * passing onto it the configuration preferences of the player before
 * initialising the game. It also checks is width and height of grid, coal and
 * gold locations are valid.
 *
 * @author Fabio Monsalve s3585826
 */
public class ConfigurationController implements Initializable {
    @FXML
    private TextField boardWidth;
    @FXML
    private TextField boardHeight;
    @FXML
    private TextField coalWidth;
    @FXML
    private TextField coalHeight;
    @FXML
    private TextField goldWidth;
    @FXML
    private TextField goldHeight;
    @FXML
    private Label numCoalLabel;
    @FXML
    private Label numGoldLabel;
    @FXML
    private Button submitButton;
    @FXML
    private Button coalButton;
    @FXML
    private Button goldButton;
    @FXML
    private Label warningLabel;

    private int coalCounter = 0;
    private int goldCounter = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        submitButton.setOnAction(e -> {
            warningLabel.setVisible(false);

            int width, height;

            // Checks if width and height are valid for board creation
            try {
                width = Integer.parseInt(boardWidth.getText());
                height = Integer.parseInt((boardHeight.getText()));
            } catch (NumberFormatException nfe) {
                warningLabel.setText("Please input valid number");
                warningLabel.setVisible(true);
                return;
            }

            if (width > Board.MAX_ALLOWED_WIDTH || height > Board.MAX_ALLOWED_HEIGHT ||
                    width <= Board.MIN_ALLOWED_WIDTH || height <= Board.MIN_ALLOWED_HEIGHT) {
                warningLabel.setText("Board size must be between 4x4 and 10x10");
                warningLabel.setVisible(true);
            } else {

                if (goldCounter > 0) {
                    try {
                        replaceSceneContent();
                        GameController.game.gameInitialize();
                        GameController.game.getBoard().initBoardNew();
                        GameController.game.gameStart();

                        // get a handle to the stage
                        Stage stage = (Stage) goldButton.getScene().getWindow();
                        // do what you have to do
                        stage.close();

                    } catch (IOException e1) {
                        e1.printStackTrace();
                        GameNotification.showAlertBoxErrorMessage("Error loading the game with current configuration");
                    }
                } else {
                    GameNotification.showAlertBoxErrorMessage("The gold must have a position defined");
                }
            }
        });

        coalButton.setOnAction(e -> {
            warningLabel.setVisible(false);

            int x, y;

            // Checks if width and height are valid for coal locations
            try {
                x = Integer.parseInt(coalWidth.getText()) - 1;
                y = Integer.parseInt(coalHeight.getText()) - 1;
            } catch (NumberFormatException nfe) {
                warningLabel.setText("Please input valid number");
                warningLabel.setVisible(true);
                return;
            }

            // Warning label is shown if locations are invalid
            if (y >= Board.gridMaxHeight || x >= Board.gridMaxWidth || x < 0 || y < 0) {
                warningLabel.setText("Location must be between 1,1 and " + Integer.parseInt(boardWidth.getText()) +
                        "," + Integer.parseInt(boardHeight.getText()));
                warningLabel.setVisible(true);
            } else {
                if (GameController.game.getBoard().getIsFilled() == null) {
                    int bWidth = Integer.parseInt(boardWidth.getText());
                    int bHeight = Integer.parseInt((boardHeight.getText()));

                    // Grid width and height is configured
                    GameController.game.getBoard().configureBoard(bWidth, bHeight);
                }

                // Coal is added
                String reply = GameController.game.getBoard().configureGoalPos("coal", x, y);
                if (reply.contains("Invalid")) {
                    warningLabel.setText(reply);
                    warningLabel.setVisible(true);
                } else {
                    coalCounter++;
                    numCoalLabel.setText(String.valueOf(coalCounter));
                }
            }
        });

        goldButton.setOnAction(e -> {
            warningLabel.setVisible(false);

            int x, y;

            // Checks if width and height are valid for gold locations
            try {
                x = Integer.parseInt(goldWidth.getText()) - 1;
                y = Integer.parseInt(goldHeight.getText()) - 1;
            } catch (NumberFormatException nfe) {
                warningLabel.setText("Please input valid number");
                warningLabel.setVisible(true);
                return;
            }

            if (y >= Board.gridMaxHeight || x >= Board.gridMaxWidth || x < 0 || y < 0) {
                warningLabel.setText("Location must be between 1,1 and " + Integer.parseInt(boardWidth.getText()) +
                        "," + Integer.parseInt(boardHeight.getText()));
                warningLabel.setVisible(true);
            } else {
                if (GameController.game.getBoard().getIsFilled() == null) {
                    int bWidth = Integer.parseInt(boardWidth.getText());
                    int bHeight = Integer.parseInt((boardHeight.getText()));

                    // Grid width and height is configured
                    GameController.game.getBoard().configureBoard(bWidth, bHeight);
                }

                // Gold is added
                String reply = GameController.game.getBoard().configureGoalPos("gold", x, y);
                if (reply.contains("Invalid")) {
                    warningLabel.setText(reply);
                    warningLabel.setVisible(true);
                } else {
                    goldCounter++;
                    numGoldLabel.setText(String.valueOf(goldCounter));
                }
            }
        });
    }

    // Method to change to main game scene
    private void replaceSceneContent() throws IOException {
        Stage stage = new Stage();

        // Load game layout
        Parent page = (Parent) FXMLLoader.load(getClass().getResource("/resources/gameLayoutNew.fxml"), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();

        if (scene == null) {
            scene = new Scene(page, 900, 900);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();

        // Show new game
        stage.show();

    }
}
