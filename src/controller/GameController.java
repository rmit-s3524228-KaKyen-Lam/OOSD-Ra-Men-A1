package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.*;
import view.GridDraw;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private GridPane gridGameBoard;
    @FXML
    private GridPane gridPlayerDeck;

    private GridDraw[][] gd;
    Grid[][] grid2d;
    private Game game;

    public GameController() {
    }

    @FXML
    protected void gridGameBoard_click(MouseEvent event) {
        System.out.println("Game board clicked at location: "); // + rowIndex + "," + colIndex);
    }

    @FXML
    protected void gridPlayerDeck_click(MouseEvent event) {
        System.out.println("Player deck clicked at location: "); // + rowIndex + "," + colIndex);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gd = new GridDraw[model.Board.GRID_MAX_WIDTH][model.Board.GRID_MAX_HEIGHT];
        game = new Game();

        game.resetBoard();
        redrawBoard();

        game.gameStart(this);

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
            //TODO draw brown rectangle
        } else if (grid2d[x][y].getCard() instanceof PathCard) {
            //TODO do switch case to find right type of patch card and right orientation
        } else if (grid2d[x][y].getCard() instanceof GoalCard) {
            //TODO do switch case to find if it's hidden, or a revealed gold/coal card
        }
        //grid2d[x][y].getCard().getImageSource();
    }

    // ACCESSOR

    public GridPane getGridGameBoard() {
        return gridGameBoard;
    }

    public GridPane getGridPlayerDeck() {
        return gridPlayerDeck;
    }

    public GridDraw[][] getGd() {
        return gd;
    }

    public Grid[][] getGrid2d() {
        return grid2d;
    }
}
