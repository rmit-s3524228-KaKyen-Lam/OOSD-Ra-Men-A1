package controller;

import model.*;

/**
 * This class handles the higher level logic of the game.
 * <p>
 * For example, checking if a card is allowed to be placed at specific location.
 *
 * @author David Limantoro s3503728
 */
public class GameLogic {

    private Board board;
    private GameController gameCon;

    /**
     * Creates a game logic object
     *
     * @param board   the game board
     * @param gameCon the game controller object that communicates with the view class
     */
    public GameLogic(Board board, GameController gameCon) {
        this.board = board;
        this.gameCon = gameCon;
    }

    /**
     * @param x    x coordinate on the board where the card is about to be placed
     * @param y    y coordinate on the board where the card is about to be placed
     * @param card card to be placed in the board at location x,y
     */
    public void checkGoalCardNeighbor(int x, int y, PathCard card) {
        if (card.isWest()) {
            checkGoalCard(board.getGridAtLocation(x - 1, y));
        }

        if (card.isNorth()) {
            checkGoalCard(board.getGridAtLocation(x, y + 1));
        }

        if (card.isEast()) {
            checkGoalCard(board.getGridAtLocation(x + 1, y));
        }

        if (card.isSouth()) {
            checkGoalCard(board.getGridAtLocation(x, y - 1));
        }
    }

    /**
     * Checks if the grid contains goal card. If it does, reveal the card inside it.
     * Precondition: grid to be checked must not be a null object.
     *
     * @param gridToCheck grid to be checked.
     */
    private void checkGoalCard(Grid gridToCheck) {
        if (gridToCheck != null && gridToCheck.getCard() instanceof GoalCard) {
            ((GoalCard) gridToCheck.getCard()).setHidden(false);
            gameCon.redrawGridXY(gridToCheck.getX(), gridToCheck.getY());
        }
    }

    /**
     * Game logic that checks whether the path card is allowed to be placed at x,y location of the board or not
     * Precondition: cardToPlace must be a PathCard object. (this is already checked by previous method)
     *
     * @param x           x coordinate on the board where the card is about to be placed
     * @param y           y coordinate on the board where the card is about to be placed
     * @param cardToPlace card to be placed in the board at location x,y
     * @return true if card placement is valid and selectedCard is not null, otherwise false
     */
    private boolean cardCheck(int x, int y, Card cardToPlace) {
        return true; // set to always true for now, work on this for assignment part 2
        // TODO do extra check when a path connect to a null location (i.e. beyond the edge of the board)
    }

    /**
     * Method to place a path card on the board.
     * If a card is placed successfully, the specified location will be redrawn and the nextTurn() method is initiated.
     * <p>
     * precondition, selectedCard must be a path card
     *
     * @param x column number of the board
     * @param y row number of the board
     * @return true if card is placed on the board successfully, otherwise false
     */
    public boolean placeCard(int x, int y, Card selectedCard) {
        if (selectedCard != null && selectedCard instanceof PathCard) {
            if (cardCheck(x, y, selectedCard)) {
                board.placeCardOnLocation(x, y, selectedCard);
                if (((PathCard) selectedCard).isCentre()) {
                    ((PathCard) selectedCard).setValid(true);
                } else {
                    ((PathCard) selectedCard).setValid(false);
                }
                checkGoalCardNeighbor(x, y, (PathCard) selectedCard);
                gameCon.redrawGridXY(x, y);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
