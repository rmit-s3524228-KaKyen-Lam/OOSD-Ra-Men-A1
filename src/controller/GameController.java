package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.*;
import view.GridDraw;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author David Limantoro s3503728
 */
public class GameController implements Initializable {

    @FXML
    private GridPane gridGameBoard;
    @FXML
    private GridPane gridPlayerDeck;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Game game = new Game();
        game.gameStart(this);
    }

    public void redrawGrid(Grid[][] grid2d) {
        for (int i = 0; i < model.Board.GRID_MAX_WIDTH; i++) {
            for (int j = 0; j < model.Board.GRID_MAX_HEIGHT; j++) {
                redrawGridXY(i, j, grid2d[i][j]);
            }
        }
    }

    public void redrawGridXY(int x, int y, Grid grid) {

        GridDraw gd = null;
        Card currentGridCard = grid.getCard();
        if (currentGridCard == null) {
            //TODO draw brown rectangle
            gd = new GridDraw("resources/Unexplored.png", x, y, "game");
        } else if (currentGridCard instanceof PathCard) {
            //TODO do switch case to find right type of patch card and right orientation
            gd = new GridDraw("resources/Unexplored.png", x, y, "game");
        } else if (currentGridCard instanceof GoalCard) {
            //TODO do switch case to find if it's hidden, or a revealed gold/coal card
            if (((GoalCard) currentGridCard).isHidden()) {
                gd = new GridDraw("resources/Goal.png", x, y, "game");
            } else if (((GoalCard) currentGridCard).isGold()) {
                gd = new GridDraw("resources/Gold.png", x, y, "game");
            } else {
                gd = new GridDraw("resources/Coal.png", x, y, "game");
            }
        }
        gridGameBoard.add(gd, x, y);
        if (gd != null) {
            gd.setOnMouseClicked(mouseEvent -> {
                System.out.printf("You clicked game board at %d,%d \n", x + 1, y + 1);
            });
        }
    }

    public void redrawDeck(Card[] currentPlayerHand) {
        for (int i = 0; i < currentPlayerHand.length; i++) {
            ImageView iv = new ImageView(card.getImage());
            gridPlayerDeck.add(iv, i, 0);
            iv.setOnMouseClicked(mouseEvent -> {
                System.out.printf("You clicked card number %d in player deck", i);
            });
        }
    }
}
