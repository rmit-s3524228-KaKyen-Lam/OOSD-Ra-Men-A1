package model;

import java.io.*;
import java.nio.charset.Charset;

public class Deck {
    private final int deckSize = 60;
    static Card cards [];


    public static void main (String args [])throws IOException{
        initialiseDeck();

        for (Card card : cards) {
            System.out.println(card.getId());
        }
    }
    public Deck(){
        cards = new Card[deckSize];
    }

    private void randomize(){
    }

    private static void initialiseDeck() throws IOException {
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
                    PathCard p = new PathCard(Integer.parseInt(tokens[1]), "resources/Shape_T.png", id.toString());
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
}
