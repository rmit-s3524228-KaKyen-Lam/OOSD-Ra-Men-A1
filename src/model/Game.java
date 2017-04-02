package model;

import controller.GameController;

import java.util.ArrayList;

/**
 * @author David Limantoro s3503728
 */
public class Game {
    private final int NUM_OF_PLAYER = 4;

    private GameController gameCon;
    private Board board = new Board();
    private Deck deck = new Deck();
    private Player[] players;

    private int gameTurnNumber = 0;
    private int playerTurnNumber = 0;
    private int numOfSaboteours = 0;

    private Card selectedCard = null;

    public Game() {
        resetBoard();
        players = new Player[NUM_OF_PLAYER];
        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(0, "miner", new ArrayList<>(), null);
        }
    }

    public void resetBoard() {
        board.initBoard();
        deck = new Deck();
    }

    /**
     * Start a new game
     *
     * @param gc gameController object that
     */
    public void gameStart(GameController gc) {
        gameCon = gc;
        resetBoard();
        deck.initialiseDeck();
        numOfSaboteours = 0;
        for (int i = 0; i < players.length; i++) {
            players[i].setHand(deck.draw(7));
            //TODO change their role here perhaps
            //TODO if someone is saboteurs, do numOfSaboteurs++;
        }
        gameCon.redrawGrid();
        gameCon.redrawDeck(players[playerTurnNumber].getHand());
        gameCon.changePlayerLabel(playerTurnNumber);
    }

    /**
     * Method to increase player's gold (this method is for when miner wins) (precondition, the player number must not be associated with saboteur)
     *
     * @param winnerPlayerNumber the player number that wins the game.
     */
    private void shareGold(int winnerPlayerNumber) {
        ArrayList<Integer> goldPool = deck.getGoldPool(NUM_OF_PLAYER - numOfSaboteours);
        //TODO finish this method
    }

    /**
     * Method to place a path card on the board
     * <p>
     * precondition, selectedCard must be a path card
     *
     * @param x column number of the board
     * @param y row number of the board
     * @return true if card is placed on the board successfully, otherwise false
     */
    public boolean placeCard(int x, int y) {
        if (selectedCard != null) {
            if (cardCheck(selectedCard, x, y)) {
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

    /**
     * @return Array containing 4 grids, where 0 is the west neighbor, 1 is the north neighbor,
     * 2 is the east neighbor and 3 is the south neightbor
     */
    private void checkGoalCardNeighbor(int x, int y, PathCard card) {
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
     * @param neighbourGrid
     */
    private void checkGoalCard(Grid neighbourGrid) {
        if (neighbourGrid != null && neighbourGrid.getCard() instanceof GoalCard) {
            ((GoalCard) neighbourGrid.getCard()).setHidden(false);
            gameCon.redrawGridXY(neighbourGrid.getX(), neighbourGrid.getY());
        }
    }

    /**
     * Game logic method that checks whether the path card is allowed to be placed at x,y or not
     *
     * @param cardToPlace card to be placed in the board at location x,y
     * @param x           column number of the board
     * @param y           row number of the board
     * @return true if card placement is valid and selectedCard is not null, otherwise false
     */
    private boolean cardCheck(Card cardToPlace, int x, int y) { //TODO stuff when west path connect to null
        return true; // set to always true for now
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
     * Handle action cards
     */
    public void handleActionCard(Object targetObject) {
        //TODO handle action card
    }

    /**
     * This method is called after a card is selected from a player's hand and then aimed at other player or placed on the board
     */
    public void nextTurn() {
        gameTurnNumber++;
        players[playerTurnNumber].addCard(deck.draw(1)[0]);

        playerTurnNumber++;
        if (playerTurnNumber >= NUM_OF_PLAYER) {
            playerTurnNumber %= NUM_OF_PLAYER;
        }

        gameCon.redrawDeck(players[playerTurnNumber].getHand());
        gameCon.changePlayerLabel(playerTurnNumber);
        selectedCard = null;
    }

    public Board getBoard() {
        return board;
    }

    public Deck getDeck() {
        return deck;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public int getGameTurnNumber() {
        return gameTurnNumber;
    }

    public int getPlayerTurnNumber() {
        return playerTurnNumber;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }
}
