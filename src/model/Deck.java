package model;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

// Memo from David:
// TODO draw method returns ArrayIndexOutOfBoundsException when deck ran out, make it return null instead.
// TODO add a method in deck that returns true if the deck is exhausted.

/**
 * Class for the deck which holds a static number of cards, also includes logic for making the initial deck of cards
 *
 * @author Fabio Monsalve s3585826
 */
public class Deck {
    private final int DECK_SIZE = 60;
    private Card cards[];
    private int pointer = 0;
    private ArrayList<Integer> goldDeck = new ArrayList<>();

    public Deck() {
        cards = new Card[DECK_SIZE];
        initialiseDeck();
    }

    /**
     * @param numCards used to keep track of next card to be drawn from the deck, the number increases deopending on the
     *                 number of cards drawn each time
     * @return array of cards requested
     */
    public Card[] draw(int numCards) {
        Card cardsOut[] = new Card[numCards];

        System.arraycopy(cards, pointer, cardsOut, 0, numCards);
        pointer = pointer + numCards;

        return cardsOut;
    }

    /**
     * Randomise order of cards in cards array
     */
    private void randomise() {
        // TODO randomise function
    }

    /**
     * Create gold deck with gold cards of different values
     */
    private void initialiseGold() {

        // Create gold deck
        int i;
        for (i = 0; i < 16; i++) {
            goldDeck.add(1);
        }
        for (i = 0; i < 8; i++) {
            goldDeck.add(2);
        }
        for (i = 0; i < 4; i++) {
            goldDeck.add(3);
        }
    }

    /**
     * Create initial collection of Cards for deck
     */
    public void initialiseDeck() {
        initialiseGold();

        /*
         * Each token is a part of a line from the path card configuration file "cardConfig.txt". Token 0 refers to the
         * card name, token 1 refers to the type of path card, token 2 refers to the number of cards of this
         * specific configuration are needed
         */
        String[] tokens;
        String line;

        try {
            // Create input for to read from file
            InputStream fis = new FileInputStream("cardConfig.txt");
            InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);

            Integer id = 0;

            while ((line = br.readLine()) != null) {
                tokens = line.split(",");
                switch (tokens[0]) {
                    case "T_SHAPE":
                        for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                            PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_T.png",
                                    id.toString());
                            cards[id] = p;
                            id++;
                        }
                        break;
                    case "T_SHAPE_DEAD":
                        for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                            PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_T_Hole.png",
                                    id.toString());
                            cards[id] = p;
                            id++;

                        }
                        break;
                    case "LINE_SHAPE":
                        for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                            PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_Line.png",
                                    id.toString());
                            cards[id] = p;
                            id++;
                        }
                        break;
                    case "LINE_SHAPE_DEAD":
                        for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                            PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_Line_Hole.png",
                                    id.toString());
                            cards[id] = p;
                            id++;
                        }
                        break;
                    case "CROSS_SHAPE":
                        for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                            PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_Plus.png",
                                    id.toString());
                            cards[id] = p;
                            id++;
                        }
                        break;
                    case "CROSS_SHAPE_DEAD":
                        for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                            PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_Plus_Hole.png",
                                    id.toString());
                            cards[id] = p;
                            id++;
                        }
                        break;
                    case "L_SHAPE":
                        for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                            PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_L.png",
                                    id.toString());
                            cards[id] = p;
                            id++;
                        }
                        break;
                    case "L_SHAPE_DEAD":
                        for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                            PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_L_Hole.png",
                                    id.toString());
                            cards[id] = p;
                            id++;
                        }
                        break;
                    case "DEAD":
                        for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                            PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_Dead.png",
                                    id.toString());
                            cards[id] = p;
                            id++;
                        }
                        break;
                    case "EMPTY":
                        for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                            PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Unexplored.png",
                                    id.toString());
                            cards[id] = p;
                            id++;
                        }
                        break;
                }

            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Get a pool of gold cards from the deck
     *
     * @return an arraylist containing gold cards
     */
    public ArrayList<Integer> getGoldPool(int numCards) {

        // Take X amount of gold card from gold deck where X is numOfCards
        ArrayList<Integer> goldPool = new ArrayList<>();
        Random randomNum = new Random();

        for (int i = 0; i < (numCards); i++) {
            int temp = randomNum.nextInt(goldDeck.size());
            goldPool.add(goldDeck.get(temp));
            goldDeck.remove(temp);
        }
        return goldPool;
    }

    /**
     * Get the highest integer in the arraylist (precondition, array must has at least one element) to give to the
     * winner of the round.
     *
     * @param intArray ArrayList of integer
     * @return the highest value in this array
     */
    private int getMax(ArrayList<Integer> intArray) {
        int max = 0;
        for (int i = 0; i < intArray.size(); i++) {
            if (max < intArray.get(i)) {
                max = intArray.get(i);
            }
        }
        return max;
    }

    public int getPointer() {
        return pointer;
    }

    public int getDECK_SIZE() {
        return DECK_SIZE;
    }

}
