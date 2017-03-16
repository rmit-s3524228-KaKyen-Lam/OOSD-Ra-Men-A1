package controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class GameController {

    @FXML
    private GridPane gridGameBoard;
    @FXML
    private GridPane gridPlayerDeck;

    private ImageView iv;

    public GameController() {
        // Constructor just because
    }

    @FXML
    protected void gridGameBoard_click(MouseEvent event) {
//        Node source = (Node) event.getSource();
//        int colIndex = GridPane.getColumnIndex(source);
//        int rowIndex = GridPane.getRowIndex(source);
        System.out.println("Game board clicked at location: "); // + rowIndex + "," + colIndex);
        iv = new ImageView("resources/Cross.png");
        gridGameBoard.add(iv, 0, 0);
    }

    @FXML
    protected void gridPlayerDeck_click(MouseEvent event) {
//        Node source = (Node) event.getSource();
//        int colIndex = GridPane.getColumnIndex(source);
//        int rowIndex = GridPane.getRowIndex(source);
        System.out.println("Player deck clicked at location: "); // + rowIndex + "," + colIndex);
        gridGameBoard.getChildren().remove(iv);
    }

}
