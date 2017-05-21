package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
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

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }
}
