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
import model.Board;
import view.Notification;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by orlandok on 21/5/17.
 * *
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
            int width = Integer.parseInt(boardWidth.getText());
            int height = Integer.parseInt((boardHeight.getText()));

            if (width > 10 || height > 10) {
                warningLabel.setVisible(true);
            } else {

                try {
                    replaceSceneContent();
                    GameController.game.gameInitialize();
                    GameController.game.getBoard().initBoardNew();
                    GameController.game.gameStart();
                    System.out.println("changed scene");
                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.out.println("file not found");
                }
            }
        });

        coalButton.setOnAction(e -> {
            warningLabel.setVisible(false);
            int x = Integer.parseInt(coalWidth.getText());
            int y = Integer.parseInt(coalHeight.getText());

            if (x > 10 || y > 10) {
                warningLabel.setVisible(true);
            } else {
                if (GameController.game.getBoard().getIsFilled() == null) {
                    int bWidth = Integer.parseInt(boardWidth.getText());
                    int bHeight = Integer.parseInt((boardHeight.getText()));
                    GameController.game.getBoard().configureBoard(bWidth, bHeight);
                }
                String reply = GameController.game.getBoard().configureGoalPos("coal", x, y);
                if (reply.contains("Invalid")) {
                    Notification.showAlertBoxErrorMessage(reply);
                } else {
                    coalCounter++;
                    numCoalLabel.setText(String.valueOf(coalCounter));
                }
            }
        });

        goldButton.setOnAction(e -> {
            warningLabel.setVisible(false);
            int x = Integer.parseInt(goldWidth.getText());
            int y = Integer.parseInt(goldHeight.getText());

            if (x > (Board.MAX_ALLOWED_WIDTH - 1) || y > (Board.MAX_ALLOWED_HEIGHT - 1)) {
                warningLabel.setVisible(true);
            } else {
                if (GameController.game.getBoard().getIsFilled() == null) {
                    int bWidth = Integer.parseInt(boardWidth.getText());
                    int bHeight = Integer.parseInt((boardHeight.getText()));
                    GameController.game.getBoard().configureBoard(bWidth, bHeight);
                }
                String reply = GameController.game.getBoard().configureGoalPos("gold", x, y);
                if (reply.contains("Invalid")) {
                    Notification.showAlertBoxErrorMessage(reply);
                } else {
                    goldCounter++;
                    numGoldLabel.setText(String.valueOf(goldCounter));
                }
            }
        });
    }

    private void replaceSceneContent() throws IOException {
        Stage stage = new Stage();
        Parent page = (Parent) FXMLLoader.load(getClass().getResource("/resources/gameLayout.fxml"), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();

        if (scene == null) {
            scene = new Scene(page, 900, 900);
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        stage.show();

    }
}