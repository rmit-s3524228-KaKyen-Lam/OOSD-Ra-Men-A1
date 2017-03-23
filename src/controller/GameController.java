package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Board;
import model.GoalCard;
import model.Grid;
import model.PathCard;
import view.GridDraw;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private GridPane gridGameBoard;
    @FXML
    private GridPane gridPlayerDeck;

    private GridDraw[][] gd;
    private Grid[][] grid2d;

    @FXML
    protected void gridGameBoard_click(MouseEvent event) {
        System.out.println("Game board clicked at location: "); // + rowIndex + "," + colIndex);
        iv = new ImageView("resources/Cross.png");
        gridGameBoard.add(iv, 0, 0);
    }

    @FXML
    protected void gridPlayerDeck_click(MouseEvent event) {
        System.out.println("Player deck clicked at location: "); // + rowIndex + "," + colIndex);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Board board = new Board();
        grid2d = board.getGrid();
        gd = new GridDraw[model.Board.GRID_MAX_WIDTH][model.Board.GRID_MAX_HEIGHT];
    }

    public void redrawBoard() {
        for (int i = 0; i < model.Board.GRID_MAX_WIDTH; i++) {
            for (int j = 0; j < model.Board.GRID_MAX_HEIGHT; j++) {
                redrawGrid(i, j);
            }

        }
    }

    public void redrawGrid(int x, int y) {
        if (grid2d[x][y].getCard() == null) {
            //draw brown rectangle
        } else if (grid2d[x][y].getCard() instanceof PathCard) {
            //do switch case to find right type of patch card and right orientation
        } else if (grid2d[x][y].getCard() instanceof GoalCard) {
            //do switch case to find if it's hidden, or a revealed gold/coal card
        }
    }
}
