package controller;

import model.*;

/**
 * This class handles the higher level logic of the game.
 * For example, checking if a card is allowed to be placed at a specific location.
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
     * This method checks the valid neighbors of a card if they are goal cards or not.
     * If they are, reveal them.
     *
     * @param x    x coordinate on the board where the card is about to be placed
     * @param y    y coordinate on the board where the card is about to be placed
     * @param card card to be placed in the board at location x,y
     */
    private void checkGoalCardNeighbor(int x, int y, PathCard card) {
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
            ((GoalCard) gridToCheck.getCard()).setHidden(false);
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
    private boolean cardPlacementCheck(int x, int y, PathCard cardToPlace) {
        boolean westConnectCheck = false;
        boolean northConnectCheck = false;
        boolean eastConnectCheck = false;
        boolean southConnectCheck = false;
        boolean atLeastOneValidPath = false;

        // Grid to the west of x,y
        Grid westGrid = board.getGridAtLocation(x - 1, y);
        if (westGrid != null) {
            Card westCard = westGrid.getCard();
            if (westCard != null && (westCard instanceof GoalCard || westCard.getId().equals("empty"))) {
                westConnectCheck = true;
            } else {
                if (cardToPlace.isWest()) {
                    if (((PathCard) westCard).isEast() && ((PathCard) westCard).isValid()) {
                        westConnectCheck = true;
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
            if (northCard != null && (northCard instanceof GoalCard || northCard.getId().equals("empty"))) {
                northConnectCheck = true;
            } else {
                if (cardToPlace.isNorth()) {
                    if (((PathCard) northCard).isSouth() && ((PathCard) northCard).isValid()) {
                        northConnectCheck = true;
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
            if (eastCard != null && (eastCard instanceof GoalCard || eastCard.getId().equals("empty"))) {
                eastConnectCheck = true;
            } else {
                if (cardToPlace.isEast()) {
                    if (((PathCard) eastCard).isWest() && ((PathCard) eastCard).isValid()) {
                        eastConnectCheck = true;
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
            if (southCard != null && (southCard instanceof GoalCard || southCard.getId().equals("empty"))) {
                southConnectCheck = true;
            } else {
                if (cardToPlace.isSouth()) {
                    if (((PathCard) southCard).isNorth() && ((PathCard) southCard).isValid()) {
                        southConnectCheck = true;
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
     * <p>
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
