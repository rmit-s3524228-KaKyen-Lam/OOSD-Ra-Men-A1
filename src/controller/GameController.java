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

    private Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        game = new Game();
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

        // This code could be better when Drawable superclass is fully working
        ImageView gd = null;
        Card currentGridCard = grid.getCard();
        if (currentGridCard == null) {
            gd = new ImageView("resources/Unexplored.png");
        } else if (currentGridCard instanceof PathCard) {
            //TODO do switch case to find right type of patch card and right orientation
            gd = new ImageView("resources/Unexplored.png"); //change this line
        } else if (currentGridCard instanceof GoalCard) {
            if (((GoalCard) currentGridCard).isHidden()) {
                gd = new ImageView("resources/Goal.png");
            } else if (((GoalCard) currentGridCard).isGold()) {
                gd = new ImageView("resources/Gold.png");
            } else {
                gd = new ImageView("resources/Coal.png");
            }
        }
        gridGameBoard.add(gd, x, y);
        if (gd != null) {
            gd.setOnMouseClicked(new gameBoardListener(x, y, game));
        }
    }

    public void redrawDeck(Card[] currentPlayerHand) {
        for (int i = 0; i < currentPlayerHand.length; i++) {
            ImageView iv = new ImageView(card.getImage());
            gridPlayerDeck.add(iv, i, 0);
            iv.setOnMouseClicked(new playerHandListener(i, game));
        }
    }
}
