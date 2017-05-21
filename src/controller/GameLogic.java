package controller;

import model.*;
import model.goalcard.GoalCard_Gold;
import model.pathcard.PathCard_Empty;

/**
 * This class handles the higher level logic of the gameLogic.
 * For example, checking if a card is allowed to be placed at a specific location.
 *
 * @author David Limantoro s3503728
 */
public class GameLogic {

    private Board board;
    private GameController gameCon;

    /**
     * Creates a gameLogic logic object
     *
     * @param board   the gameLogic board
     * @param gameCon the gameLogic controller object that communicates with the view class
     */
    public GameLogic(Board board, GameController gameCon) {
        this.board = board;
        this.gameCon = gameCon;
    }

    /**
     * This method checks the valid neighbors of a card if they are goal cards or not.
     * If they are, reveal them.
     *
     * @param x    x coordinate on the board where the card is about to be placed
     * @param y    y coordinate on the board where the card is about to be placed
     * @param card card to be placed in the board at location x,y
     */
    public void checkGoalCardNeighbor(int x, int y, PathCard card) {
        if (card.isWest()) {
            checkGoalCard(board.getGridAtLocation(x - 1, y));
        }

        if (card.isNorth()) {
            checkGoalCard(board.getGridAtLocation(x, y - 1));
        }

        if (card.isEast()) {
            checkGoalCard(board.getGridAtLocation(x + 1, y));
        }

        if (card.isSouth()) {
            checkGoalCard(board.getGridAtLocation(x, y + 1));
        }
    }

    /**
     * Checks if the grid contains goal card.
     * If it does, reveal the card inside it.
     *
     * @param gridToCheck grid to be checked.
     */
    private void checkGoalCard(Grid gridToCheck) {
        if (gridToCheck != null && gridToCheck.getCard() instanceof GoalCard) {
            if (gridToCheck.getCard() instanceof GoalCard_Gold) {
                gridToCheck.setCard(CardFlyweight.getCard("GOLD_REVEALED", 0));
            } else {
                gridToCheck.setCard(CardFlyweight.getCard("COAL_REVEALED", 0));
            }
            gameCon.redrawGridXY(gridToCheck.getX(), gridToCheck.getY());
        }
    }

    /**
     * Game logic that checks whether the path card is allowed to be placed at x,y location of the board or not
     *
     * @param x           x coordinate on the board where the card is about to be placed
     * @param y           y coordinate on the board where the card is about to be placed
     * @param cardToPlace card to be placed in the board at location x,y
     * @return true if card placement is valid and selectedCard is not null, otherwise false
     */
    public boolean cardPlacementCheck(int x, int y, PathCard cardToPlace) {
        boolean westConnectCheck = false;
        boolean northConnectCheck = false;
        boolean eastConnectCheck = false;
        boolean southConnectCheck = false;
        boolean atLeastOneValidPath = false;

        // Grid to the west of x,y
        Grid westGrid = board.getGridAtLocation(x - 1, y);
        if (westGrid != null) {
            Card westCard = westGrid.getCard();
            if (westCard != null && (westCard instanceof GoalCard || westCard instanceof PathCard_Empty)) {
                westConnectCheck = true;
            } else {
                if (cardToPlace.isWest()) {
                    if (((PathCard) westCard).isEast()) {
                        westConnectCheck = true;
                    }
                    if (westGrid.isConnectedToMain() && ((PathCard) westCard).isCentre()) {
                        atLeastOneValidPath = true;
                    }
                } else {
                    if (!((PathCard) westCard).isEast()) {
                        westConnectCheck = true;
                    }
                }
            }
        } else {
            westConnectCheck = true;
        }

        // Grid to the north of x,y
        Grid northGrid = board.getGridAtLocation(x, y - 1);
        if (northGrid != null) {
            Card northCard = northGrid.getCard();
            if (northCard != null && (northCard instanceof GoalCard || northCard instanceof PathCard_Empty)) {
                northConnectCheck = true;
            } else {
                if (cardToPlace.isNorth()) {
                    if (((PathCard) northCard).isSouth()) {
                        northConnectCheck = true;
                        atLeastOneValidPath = true;
                    }
                    if (northGrid.isConnectedToMain() && ((PathCard) northCard).isCentre()) {
                        atLeastOneValidPath = true;
                    }
                } else {
                    if (!((PathCard) northCard).isSouth()) {
                        northConnectCheck = true;
                    }
                }
            }
        } else {
            northConnectCheck = true;
        }

        // Grid to the east of x,y
        Grid eastGrid = board.getGridAtLocation(x + 1, y);
        if (eastGrid != null) {
            Card eastCard = eastGrid.getCard();
            if (eastCard != null && (eastCard instanceof GoalCard || eastCard instanceof PathCard_Empty)) {
                eastConnectCheck = true;
            } else {
                if (cardToPlace.isEast()) {
                    if (((PathCard) eastCard).isWest()) {
                        eastConnectCheck = true;
                    }
                    if (eastGrid.isConnectedToMain() && ((PathCard) eastCard).isCentre()) {
                        atLeastOneValidPath = true;
                    }
                } else {
                    if (!((PathCard) eastCard).isWest()) {
                        eastConnectCheck = true;
                    }
                }
            }
        } else {
            eastConnectCheck = true;
        }

        // Grid to the south of x,y
        Grid southGrid = board.getGridAtLocation(x, y + 1);
        if (southGrid != null) {
            Card southCard = southGrid.getCard();
            if (southCard != null && (southCard instanceof GoalCard || southCard instanceof PathCard_Empty)) {
                southConnectCheck = true;
            } else {
                if (cardToPlace.isSouth()) {
                    if (((PathCard) southCard).isNorth()) {
                        southConnectCheck = true;
                    }
                    if (southGrid.isConnectedToMain() && ((PathCard) southCard).isCentre()) {
                        atLeastOneValidPath = true;
                    }
                } else {
                    if (!((PathCard) southCard).isNorth()) {
                        southConnectCheck = true;
                    }
                }
            }
        } else {
            southConnectCheck = true;
        }

        return westConnectCheck && northConnectCheck && eastConnectCheck && southConnectCheck && atLeastOneValidPath;
    }

    /**
     * Method to place a path card on the board.
     * If a card is placed successfully, the specified location will be redrawn and the nextTurn() method is initiated.
     *
     * precondition, selectedCard must be a path card
     *
     * @param x column number of the board
     * @param y row number of the board
     * @return true if card is placed on the board successfully, otherwise false
     */
    public boolean placeCardOnBoard(int x, int y, Card selectedCard) {
        if (selectedCard != null && selectedCard instanceof PathCard) {
            if (cardPlacementCheck(x, y, (PathCard) selectedCard)) {
                board.placeCardOnLocation(x, y, selectedCard);
                if (((PathCard) selectedCard).isCentre()) {
//                    ((PathCard) selectedCard).setValid(true);
                } else {
//                    ((PathCard) selectedCard).setValid(false);
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
