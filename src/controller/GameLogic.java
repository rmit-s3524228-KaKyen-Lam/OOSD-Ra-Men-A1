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
     * Checks if the grid contains goal card. If it does, reveal the card inside it
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
    public boolean cardCheck(int x, int y, Card cardToPlace) {
        return true; // set to always true for now, work on this for assignment part 2
        // TODO do extra check when a path connect to a null location (i.e. beyond the edge of the board)
        // Ignore these lines, this is WIP
//        PathCard currentPathCard = (PathCard) cardToPlace;
//
//        boolean westConnectCheck = false;
//        boolean northConnectCheck = false;
//        boolean eastConnectCheck = false;
//        boolean southConnectCheck = false;
//        boolean atLeastOneValidPath = false;
//
//        // WEST
//        Grid westGrid = board.getGridAtLocation(x - 1, y);
//        Card westCard = westGrid.getCard();
//        if (westCard != null && (westCard instanceof GoalCard || westCard.getId().equals("empty"))) {
//            westConnectCheck = true;
//        } else {
//            if (currentPathCard.isWest()) {
//                if (((PathCard) westCard).isEast() && ((PathCard) westCard).isValid()) {
//                    westConnectCheck = true;
//                    atLeastOneValidPath = true;
//                }
//            } else {
//                if (!((PathCard) westCard).isEast()) {
//                    westConnectCheck = true;
//                }
//            }
//        }
//
//        // NORTH
//        Card northCard = board.getGridAtLocation(x, y - 1).getCard();
//        if (northCard != null && (northCard instanceof GoalCard || northCard.getId().equals("empty"))) {
//            northConnectCheck = true;
//        } else {
//            if (currentPathCard.isNorth()) {
//                if (((PathCard) northCard).isSouth() && ((PathCard) northCard).isValid()) {
//                    westConnectCheck = true;
//                    atLeastOneValidPath = true;
//                }
//            } else {
//                if (!((PathCard) northCard).isSouth()) {
//                    westConnectCheck = true;
//                }
//            }
//
//        }
//
//        // EAST
//        Card eastCard = board.getGridAtLocation(x + 1, y).getCard();
//        if (eastCard != null && (eastCard instanceof GoalCard || eastCard.getId().equals("empty"))) {
//            eastConnectCheck = true;
//        } else {
//            if (currentPathCard.isEast()) {
//                if (((PathCard) eastCard).isWest() && ((PathCard) eastCard).isValid()) {
//                    westConnectCheck = true;
//                    atLeastOneValidPath = true;
//                }
//            } else {
//                if (!((PathCard) eastCard).isWest()) {
//                    westConnectCheck = true;
//                }
//            }
//        }
//
//        // SOUTH
//        Card southCard = board.getGridAtLocation(x, y + 1).getCard();
//        if (southCard != null && (southCard instanceof GoalCard || southCard.getId().equals("empty"))) {
//            southConnectCheck = true;
//        } else {
//            if (currentPathCard.isSouth()) {
//                if (((PathCard) southCard).isNorth() && ((PathCard) southCard).isValid()) {
//                    westConnectCheck = true;
//                    atLeastOneValidPath = true;
//                }
//            } else {
//                if (!((PathCard) southCard).isNorth()) {
//                    westConnectCheck = true;
//                }
//            }
//        }
//
//        if (westConnectCheck && northConnectCheck && eastConnectCheck && southConnectCheck && atLeastOneValidPath) {
//            return true;
//        } else {
//            return false;
//        }
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
                System.out.println(board.goldIsFound());
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
