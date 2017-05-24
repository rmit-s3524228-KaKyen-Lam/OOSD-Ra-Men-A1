package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Class for the deck which holds a static number of cards, also includes logic for making the initial deck of cards.
 * This class is also an example of the Creator principle because the deck contains and closely uses the Card class it
 * can create objects of Card.
 *
 * @author Fabio Monsalve s3585826
 */
class Deck implements Serializable {
    private ArrayList<Card> cards;
    private ArrayList<Integer> goldDeck = new ArrayList<>();

    Deck() {
        cards = new ArrayList<>();
        initialiseDeck();
    }

    /**
     * @param numCards used to keep track of next card to be drawn from the deck, the number increases deopending on the
     *                 number of cards drawn each time
     * @return array of cards requested
     */
    Card[] draw(int numCards) {
        Card cardsOut[] = new Card[numCards];

        for (int i = 0; i < numCards; i++) {
            cardsOut[i] = cards.get(0);
            cards.remove(0);
        }

        return cardsOut;
    }

    /**
     * Randomise order of cards in cards array
     */
    public void randomise() {
        Collections.shuffle(cards);
    }

    public void addCard(Card card) {
        cards.add(card);
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
    void initialiseDeck() {
        initialiseGold();

    /*
     * Each token is a part of a line from the path card configuration file "cardConfig.txt". Token 0 refers to the
     * card name, token 1 refers to the type of path card, token 2 refers to the number of cards of this
     * specific configuration are needed
     */
        String[] tokens;
        String line;

     /*
      * Create input for to read from file, "cardConfig.txt" an external representation as an example of the
      * Protected variations principle
      */
        try {
            InputStream fis = new FileInputStream("cardConfig.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            while ((line = br.readLine()) != null) {
                tokens = line.split(",");
                switch (tokens[0]) {
                    case "T_SHAPE":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "T_SHAPE_DEAD":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "LINE_SHAPE":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "LINE_SHAPE_DEAD":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "CROSS_SHAPE":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "CROSS_SHAPE_DEAD":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "L_SHAPE":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "L_SHAPE_DEAD":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "DEAD":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "EMPTY":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "ADD_PATH":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "CLEAN_PATH":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "CORRUPT_PATH":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "DESTROY_M_PATHS":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "DESTROY_PATH":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "REMOVE_PATH":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "ROTATE_PATH":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                    case "SWAP_PATH":
                        for (int i = 0; i < Integer.parseInt(tokens[1]); i++) {
                            cards.add(CardFlyweight.getCard(tokens[0], 0));
                        }
                        break;
                }
            }
            randomise();
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Get a pool of gold cards from the deck
     *
     * @return an arraylist containing gold cards
     */
    ArrayList<Integer> getGoldPool(int numCards) {

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

    public int getDeckSize() {
        return cards.size();
    }
}
