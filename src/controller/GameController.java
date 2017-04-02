package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.*;

import java.net.URL;
import java.util.ArrayList;
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
        ImageView gd;
        Card currentGridCard = grid.getCard();

        if (currentGridCard instanceof GoalCard) {
            if (((GoalCard) currentGridCard).isHidden()) {
                gd = new ImageView(((GoalCard) currentGridCard).getConcealedImageResource());
            } else {
                gd = new ImageView(currentGridCard.getImageResource());
            }
        } else {
            gd = new ImageView(currentGridCard.getImageResource());
        }

        gridGameBoard.add(gd, x, y);
        gd.setOnMouseClicked(new GameBoardListener(x, y, game));
    }

    public void redrawDeck(ArrayList<Card> currentPlayerHand) {
        for (int i = 0; i < currentPlayerHand.size(); i++) {
            ImageView iv = new ImageView(currentPlayerHand.get(i).getImageResource());
            gridPlayerDeck.add(iv, i, 0);
            iv.setOnMouseClicked(new PlayerHandListener(i, game));
        }
    }
}
