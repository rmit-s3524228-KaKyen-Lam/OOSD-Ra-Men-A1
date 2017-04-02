package model;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private final int DECK_SIZE = 60;
    private static Card cards [];
    private int pointer = 0;
    private ArrayList<Integer> goldDeck = new ArrayList<>();

    public Deck(){
        cards = new Card[DECK_SIZE];
        try {
            initialiseDeck();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Card [] draw(int numCards){
        Card cardsOut [] = new Card[numCards];

        System.arraycopy(cards, pointer, cardsOut, 0, numCards);
        pointer = pointer + numCards;

        return cardsOut;
    }

    private void randomise(){
        // TODO randomise function
    }

    private void initialiseGold(){
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
     *
     * @throws IOException if file specified (card configuration) is not valid
     */
    public void initialiseDeck() throws IOException {
        initialiseGold();
        String[] tokens;
        String line;

        InputStream fis = new FileInputStream("cardConfig.txt");
        InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
        BufferedReader br = new BufferedReader(isr);

        Integer id = 0;

        while ((line = br.readLine()) != null) {
            tokens = line.split(",");
            if (tokens[0].equals("T_SHAPE")) {
                for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                    PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_T.png",
                            id.toString());
                    cards[id] = p;
                    id++;
                }
            } else if (tokens[0].equals("T_SHAPE_DEAD")) {
                for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                    PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_T_Hole.png",
                            id.toString());
                    cards[id] = p;
                    id++;

                }
            } else if (tokens[0].equals("LINE_SHAPE")) {
                for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                    PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_Line.png",
                            id.toString());
                    cards[id] = p;
                    id++;
                }
            } else if (tokens[0].equals("LINE_SHAPE_DEAD")) {
                for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                    PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_Line_Hole.png",
                            id.toString());
                    cards[id] = p;
                    id++;
                }
            } else if (tokens[0].equals("CROSS_SHAPE")) {
                for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                    PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_Plus.png",
                            id.toString());
                    cards[id] = p;
                    id++;
                }
            } else if (tokens[0].equals("CROSS_SHAPE_DEAD")) {
                for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                    PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_Plus_Hole.png",
                            id.toString());
                    cards[id] = p;
                    id++;
                }
            } else if (tokens[0].equals("L_SHAPE")) {
                for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                    PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_L.png",
                            id.toString());
                    cards[id] = p;
                    id++;
                }
            } else if (tokens[0].equals("L_SHAPE_DEAD")) {
                for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                    PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_L_Hole.png",
                            id.toString());
                    cards[id] = p;
                    id++;
                }
            } else if (tokens[0].equals("DEAD")) {
                for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                    PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_Dead.png",
                            id.toString());
                    cards[id] = p;
                    id++;
                }
            } else if (tokens[0].equals("EMPTY")) {
                for (int i = 0; i < Integer.parseInt(tokens[2]); i++) {
                    PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Unexplored.png",
                            id.toString());
                    cards[id] = p;
                    id++;
                }
            }

        }
    }

    /**
     * Get a pool of gold cards from the deck
     *
     * @return an arraylist containing gold cards
     */
    public ArrayList<Integer> getGoldPool(int numOfCards) {
        // Take X amount of gold card from gold deck where X is numOfCards
        ArrayList<Integer> goldPool = new ArrayList<>();
        Random randomNum = new Random();

        for (int i = 0; i < (numOfCards); i++) {
            int temp = randomNum.nextInt(goldDeck.size());
            goldPool.add(goldDeck.get(temp));
            goldDeck.remove(temp);
        }
        return goldPool;
    }

    /**
     * Get the highest integer in the arraylist (precondition, array must has at least one element)
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

}
