package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by orlandok on 21/5/17.
 * *
 */
public class configurationController implements Initializable{
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

  private ArrayList<int []> goldPosList = new ArrayList<>();
  private ArrayList<int []> coalPosList = new ArrayList<>();
=
  @Override
  public void initialize(URL location, ResourceBundle resources) {

    submitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        int width = Integer.parseInt(boardWidth.getText());
        int height = Integer.parseInt((boardHeight.getText()));
        GameController.game.getBoard().configureBoard(width, height);
      }
    });

    coalButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        int [] array = new int[2];
        array[0] = Integer.parseInt(coalWidth.getText());
        array[1] = Integer.parseInt(coalHeight.getText());
        coalPosList.add(array);
      }
    });

    goldButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        int [] array = new int[2];
        array[0] = Integer.parseInt(goldWidth.getText());
        array[1] = Integer.parseInt(goldHeight.getText());
        goldPosList.add(array);
      }
    });
  }
}
